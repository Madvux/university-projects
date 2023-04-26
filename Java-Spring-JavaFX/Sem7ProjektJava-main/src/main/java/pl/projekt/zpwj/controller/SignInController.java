package pl.projekt.zpwj.controller;


import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import pl.projekt.zpwj.model.User;
import pl.projekt.zpwj.repository.UserRepository;
import pl.projekt.zpwj.utils.PasswordHash;
import pl.projekt.zpwj.utils.SceneController;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

@Controller
public class SignInController implements Initializable {

    @FXML
    private TextField loginTextField;
    @FXML
    private PasswordField passwordPasswordField;
    @FXML
    private Button cancelButton;

    @FXML
    private Label errorLabel;


    @Autowired
    UserRepository userRepository;

    public void login(ActionEvent event){

        User user = userRepository.findByUsername(loginTextField.getText()); //to dziala
        if (user != null) {
            if (PasswordHash.decrypt(user.getPassword(),"java").equals(passwordPasswordField.getText())) {
                try {
                    SceneController.newWindow(event, "Strona główna", "/fxml/userPage.fxml", user);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else {
                errorLabel.setText("Niepoprawne dane logowania");
            }
        }
        else {
            errorLabel.setText("Taki użytkownik nie istnieje");
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        cancelButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    SceneController.newWindow(event, "Witamy w portalu", "/fxml/welcome.fxml");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
