package pl.projekt.zpwj.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import pl.projekt.zpwj.LoggedInUser;
import pl.projekt.zpwj.model.Reservation;
import pl.projekt.zpwj.model.User;
import pl.projekt.zpwj.repository.ReservationRepository;
import pl.projekt.zpwj.utils.SceneController;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

@Controller
public class ReservationStatusController implements Initializable {

    @Autowired
    ReservationRepository reservationRepository;

    @FXML
    private ListView<Reservation> reservationListView;

    @FXML private Button goBack;

    @FXML
    private Label reservationItemLabel;

    Reservation picked;

    private ObservableList<Reservation> reservationObservableList;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        List<Reservation> reservationList =
                (LoggedInUser.getInstance().getRole() == User.Type.ADMIN)
                        ? reservationRepository.findAll()
                        : reservationRepository.findByUserId(LoggedInUser.getInstance().getId());

        reservationObservableList = FXCollections.observableArrayList();
        reservationObservableList.addAll(reservationList);
        reservationListView.setItems(reservationObservableList);
        reservationListView.setCellFactory(reservationListView -> new ReservationListViewCell());

        goBack.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    SceneController.newWindow(event, "Strona główna", "/fxml/userPage.fxml",LoggedInUser.getInstance());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private class ReservationListViewCell extends ListCell<Reservation> {

        @FXML
        private AnchorPane anchorPane;

        @FXML
        private Label titleLabel;
        @FXML
        private Label yearLabel;
        @FXML
        private Label userLabel;
        @FXML
        private Label dateLabel;
        @FXML
        private Label timeLabel;
        @FXML
        private Label statusLabel;
        @FXML
        private ImageView posterImageView;
        @FXML
        private Button changeStatus;

        private FXMLLoader mLLoader;

        @Override
        protected void updateItem(Reservation reservation, boolean empty) {
            super.updateItem(reservation, empty);

            if (empty || reservation == null) {
                setText(null);
                setGraphic(null);
            } else {
                if (mLLoader == null) {
                    mLLoader = new FXMLLoader(getClass().getResource("/fxml/reservationListViewCell.fxml"));
                    mLLoader.setController(this);
                    try {
                        mLLoader.load();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

                titleLabel.setText(reservation.getMovie().getTitle());
                yearLabel.setText(reservation.getMovie().getYear());
                userLabel.setText(reservation.getUser().getUsername());
                dateLabel.setText(reservation.getDate().toString());
                Image image;
                try {
                    image = new Image(reservation.getMovie().getPosterLink());
                } catch (Exception e) {
                    image = new Image("image-not-found.png");
                }

                posterImageView.setImage(image);
                switch (reservation.getTime()){
                    case RANO -> timeLabel.setText("12:00");
                    case POLUDNIE -> timeLabel.setText("16:00");
                    case WIECZOR -> timeLabel.setText("20:00");
                }
                if (reservation.isStatus()) {
                    statusLabel.setTextFill(Color.GREEN);
                    statusLabel.setText("Zarezerwowano");
                }
                else {
                    statusLabel.setTextFill(Color.YELLOW);
                    statusLabel.setText("Oczekuje");
                }

                changeStatus.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        reservation.setStatus(!reservation.isStatus());
                        reservationRepository.save(reservation);

                        if (reservation.isStatus()) {
                            statusLabel.setTextFill(Color.GREEN);
                            statusLabel.setText("Zarezerwowano");
                        }
                        else {
                            statusLabel.setTextFill(Color.YELLOW);
                            statusLabel.setText("Oczekuje");
                        }
                    }
                });

                if (LoggedInUser.getInstance().getRole() != User.Type.ADMIN){
                    changeStatus.setVisible(false);
                }

                setGraphic(anchorPane);

            }
        }
    }
}
