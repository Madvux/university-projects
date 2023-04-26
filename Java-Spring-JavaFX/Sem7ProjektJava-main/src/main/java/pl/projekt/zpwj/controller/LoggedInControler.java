package pl.projekt.zpwj.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import pl.projekt.zpwj.model.Movie;
import pl.projekt.zpwj.model.Reservation;
import pl.projekt.zpwj.model.User;
import pl.projekt.zpwj.repository.MovieRepository;
import pl.projekt.zpwj.repository.ReservationRepository;
import pl.projekt.zpwj.repository.UserRepository;
import pl.projekt.zpwj.utils.JSoupWebScrapper;
import pl.projekt.zpwj.utils.SceneController;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.HashSet;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Set;

@Controller
public class LoggedInControler implements Initializable {

    public User loggedInUser;

    @FXML
    private Button buttonLogout;
    @FXML
    private Button addMovie;
    @FXML
    private Button gotoReservationList;
    @FXML
    private Label usernameLabel;

    @FXML
    private DatePicker dateUI;
    @FXML
    private ChoiceBox timeUI;
    @FXML
    private Label reservationResult;
    @FXML
    private Label pickedMovieLabel;
    private Movie pickedMovie;
    @FXML
    private Button confirmReservation;

    @FXML
    private ListView<Movie> listView;

    private ObservableList<Movie> movieObservableList;
    private java.sql.Date sqlDate;
    @Autowired
    private ReservationRepository reservationRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private MovieRepository movieRepository;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        List<Movie> movies = movieRepository.findAll();
        if (movies.isEmpty()) movieRepository.saveAll(JSoupWebScrapper.initMoviesData());
        movies = movieRepository.findAll();
        movieObservableList = FXCollections.observableArrayList();
        movieObservableList.addAll(movies);

        listView.setItems(movieObservableList);
        listView.setCellFactory(movieListView -> new MovieListViewCell());

        buttonLogout.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    SceneController.newWindow(event, "Witamy w portalu", "/fxml/welcome.fxml");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        addMovie.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    SceneController.newWindow(event, "Dodaj film", "/fxml/addMovie.fxml");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        gotoReservationList.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    SceneController.newWindow(event, "Lista rezerwacji", "/fxml/reservationStatus.fxml");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        dateUI.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                java.util.Date date = java.util.Date.from(
                        dateUI.getValue()
                                .atStartOfDay(ZoneId.systemDefault())
                                .toInstant());
                sqlDate = new java.sql.Date(date.getTime());
                List<Reservation> res = reservationRepository.findByDateAndStatus(sqlDate, true);

                Set<Reservation.Time> taken_time = new HashSet<>();
                for (Reservation r : res) {
                    taken_time.add(r.getTime());
                }
                ObservableList<String> avaibleTimes = FXCollections.observableArrayList();
                for (Reservation.Time t : Reservation.Time.values()) {
                    if (!taken_time.contains(t)) {
                        String time = "";
                        switch (t) {
                            case RANO -> time = "12:00";
                            case POLUDNIE -> time = "16:00";
                            case WIECZOR -> time = "20:00";
                        }
                        avaibleTimes.add(time);
                    }
                }

                if (taken_time.size() == 3) {
                    timeUI.setDisable(true);
                    reservationResult.setText("W tym dniu nie ma już wolnych terminów");
                } else {
                    timeUI.setItems(avaibleTimes);
                    timeUI.setDisable(false);
                    reservationResult.setText("");
                }
            }
        });
        confirmReservation.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                //walidacja
                if (validate()) {
                    Reservation reservation = new Reservation();
                    reservation.setUser(loggedInUser);
                    reservation.setMovie(pickedMovie);
                    reservation.setDate(sqlDate);
                    switch (timeUI.getValue().toString()) {
                        case "12:00" -> reservation.setTime(Reservation.Time.RANO);
                        case "16:00" -> reservation.setTime(Reservation.Time.POLUDNIE);
                        case "20:00" -> reservation.setTime(Reservation.Time.WIECZOR);
                    }
                    try {
                        reservationRepository.save(reservation);
                        dateUI.setValue(null);
                        timeUI.setValue(null);
                    } catch (Exception exception) {
                        exception.printStackTrace();
                    }

                }
            }
        });
    }

    private boolean validate() {
        if (sqlDate == null){
            reservationResult.setText("Nie wybrano daty!");
            return false;
        }
        if (sqlDate.toLocalDate().isBefore(LocalDate.now(ZoneId.systemDefault()))) {
            reservationResult.setText("Data nie może być z przeszłości");
            return false;
        }
        if (sqlDate.toLocalDate().isAfter(
                LocalDate.now(ZoneId.systemDefault()).plusWeeks(2))) {
            reservationResult.setText("Nie można rezerwować daty starszej niż 14 dni od dnia dzisiejszego");
            return false;
        }
        if (pickedMovie == null) {
            reservationResult.setText("Nie wybrano filmu!");
            return false;
        }
        if (timeUI.getValue() == null){
            reservationResult.setText("Nie wybrano godziny!");
            return false;
        }
        reservationResult.setTextFill(Color.GREEN);
        reservationResult.setText("Rezerwacja przebiegła pomyślnie");
        return true;
    }


    public void setUser(User u) {
        loggedInUser = u;
        usernameLabel.setText("Witaj " + loggedInUser.getUsername());
        if (loggedInUser.getRole() != User.Type.ADMIN) {
            addMovie.setVisible(false);
        }
    }


    private class MovieListViewCell extends ListCell<Movie> {

        @FXML
        private Label titleLabel;
        @FXML
        private Label yearLabel;
        @FXML
        private ImageView posterImage;
        @FXML
        private AnchorPane anchorPane;
        @FXML
        private Button moviebutton;

        @FXML
        private Button deleteMovie;

        private FXMLLoader mLLoader;


        @Override
        protected void updateItem(Movie movie, boolean empty) {
            super.updateItem(movie, empty);

            if (empty || movie == null) {
                setText(null);
                setGraphic(null);
            } else {
                if (mLLoader == null) {
                    mLLoader = new FXMLLoader(getClass().getResource("/fxml/movieListViewCell.fxml"));
                    mLLoader.setController(this);
                    try {
                        mLLoader.load();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                moviebutton.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {

                        pickedMovieLabel.setText(movie.getTitle());
                        pickedMovie = movie;

                    }
                });

                deleteMovie.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Na pewno usunąć?", ButtonType.NO, ButtonType.YES);
                        alert.showAndWait();
                        if (alert.getResult() == ButtonType.YES) {
                            movieRepository.delete(movie);
                            movieObservableList.remove(movie);
                            System.out.println("deleted");
                        }
                    }
                });
                if (loggedInUser.getRole() != User.Type.ADMIN) deleteMovie.setVisible(false);

                titleLabel.setText(movie.getTitle());
                yearLabel.setText(String.valueOf(movie.getYear()));
                Image image;
                try {
                    image = new Image(movie.getPosterLink());
                } catch (Exception e) {
                    image = new Image("image-not-found.png");
                }

                posterImage.setImage(image);
                setGraphic(anchorPane);
            }

        }
    }
}
