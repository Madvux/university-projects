package pl.projekt.zpwj.controller;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;

import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.paint.Color;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import pl.projekt.zpwj.LoggedInUser;
import pl.projekt.zpwj.model.Movie;
import pl.projekt.zpwj.repository.MovieRepository;
import pl.projekt.zpwj.utils.SceneController;

import java.io.IOException;
import java.net.URL;
import java.util.*;

@Controller
public class AddMovieController implements Initializable {

    @FXML
    private TextField title;
    @FXML
    private TextField ogTitle;
    @FXML
    private TextField director;
    @FXML
    private TextField posterLink;
    @FXML
    private TextField year;
    @FXML
    private TextField rankingNumber;
    @FXML
    private ChoiceBox genres;
    @FXML
    private TextField rating;
    @FXML
    private Button goBack;
    @FXML
    private Label validation;
    @Autowired
    private MovieRepository movieRepository;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        goBack.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    SceneController.newWindow(event, "User page", "/fxml/userPage.fxml", LoggedInUser.getInstance());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        rating.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String oldValue, String newValue) {
                if (!newValue.matches("\\d*")) {
                    rating.setText(newValue.replaceAll("[^\\d]", ""));
                }
            }
        });
        List<Movie> movies = movieRepository.findAll();

        Set<String> genreSet = new HashSet<>() {
        };
        for (Movie m : movies) {
            genreSet.addAll(m.getGenres());
        }

        ObservableList<String> genreObservableList = FXCollections.observableArrayList();
        genreObservableList.addAll(genreSet);

        genres.setItems(genreObservableList);


    }

    @FXML
    public void addMovieOnClick(ActionEvent event) {

        Movie movie = new Movie();
        movie.setTitle(title.getText());
        movie.setOriginalTitle(ogTitle.getText());
        movie.setPosterLink(posterLink.getText());
        movie.setYear(year.getText());
        movie.setDirector(director.getText());
        movie.setGenres(genres.getItems());
        if (checkFields(movie)) {
        movie.setRankingNumber(Integer.parseInt(rankingNumber.getText()));
        movie.setRating(Double.parseDouble(rating.getText()));


            movieRepository.save(movie);
            try {
                SceneController.newWindow(event, "Strona główna", "/fxml/userPage.fxml", LoggedInUser.getInstance());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private boolean checkFields(Movie movie) {
        if (movie.getTitle().isEmpty()
                || movie.getYear().isEmpty()
                || movie.getDirector().isEmpty()
        || movie.getGenres().isEmpty()
                || String.valueOf(movie.getRankingNumber()).isEmpty()
                || String.valueOf(movie.getRating()).isEmpty()
                || rankingNumber.getText().isEmpty()
        || rating.getText().isEmpty()) {
            validation.setText("Pola nie powinny być puste");
            return false;
        }
        return true;
    }
}
