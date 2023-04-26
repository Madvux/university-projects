package pl.projekt.zpwj.controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import org.springframework.stereotype.Controller;
import pl.projekt.zpwj.utils.SceneController;

@Controller
public class WelcomeController implements Initializable {

    @FXML
    private Button gotosignin;
    @FXML
    private Button gotosignup;
    @FXML
    private Button exitButton;
    @FXML
    private ImageView welcomeimage;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Image image = new Image("felix-mooneeram-evlkOfkQ5rE-unsplash.jpg");
        welcomeimage.setImage(image);

        gotosignup.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    SceneController.newWindow(event, "Zarejestruj", "/fxml/sign-up.fxml");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        gotosignin.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    SceneController.newWindow(event, "Zaloguj", "/fxml/sign-in.fxml");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        exitButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                Stage stage = (Stage) exitButton.getScene().getWindow();
                stage.close();
            }
        });
    }
}
