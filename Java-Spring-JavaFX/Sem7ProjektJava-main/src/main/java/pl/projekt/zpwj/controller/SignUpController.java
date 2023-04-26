package pl.projekt.zpwj.controller;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import pl.projekt.zpwj.model.User;
import pl.projekt.zpwj.repository.UserRepository;
import pl.projekt.zpwj.utils.PasswordHash;
import pl.projekt.zpwj.utils.SceneController;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.regex.Pattern;

@Controller
public class SignUpController implements Initializable {

    @Autowired
    public UserRepository userRepository;

    @FXML
    private TextField signuplogin;
    @FXML
    private TextField signupemail;
    @FXML
    private PasswordField signuppasswordPasswordField;
    @FXML
    private PasswordField confirmPasswordPasswordField;
    @FXML
    private Label errorLabel;

    @FXML
    private Button cancelButton;


    @FXML
    void register(ActionEvent event) {
        User u = new User();
        u.setUsername(signuplogin.getText());
        u.setEmail(signupemail.getText());
        u.setPassword(signuppasswordPasswordField.getText());
        u.setRole(User.Type.USER);
        if (validate(u)) {
            String hashedPassword = PasswordHash.encrypt(u.getPassword(),"java");
            u.setPassword(hashedPassword);
            userRepository.save(u);
            errorLabel.setTextFill(Color.GREEN);
            errorLabel.setText("Rejestracja przebiegła pomyślnie. Możesz się zalogować");
        }
    }

    public boolean validate(User u) {
        errorLabel.setTextFill(Color.RED);
        User userInDatabase = userRepository.findByUsername(u.getUsername());
        if (userInDatabase != null && (userInDatabase.getUsername().equals(u.getUsername()) || userInDatabase.getEmail().equals(u.getEmail()))) {
            errorLabel.setText("Taki użytkownik jest już zarejestrowany");
            return false;
        }
        if (u.getUsername().length() < 3 || u.getUsername().length() > 30) {
            errorLabel.setText("Nazwa użytkownika musi mieć między 3 a 30 znaków");
            return false;
        }
        if (u.getPassword().length()<8 || u.getPassword().length()>30){
            errorLabel.setText("Hasło musi mieć między 8 a 30 znaków");
            return false;
        }
        if (!Pattern.matches("[A-Za-z0-9/.]+([/@])[A-Za-z0-9]+[/.][A-Za-z/.]+",u.getEmail())){
            errorLabel.setText("Niepoprawny format email");
            return false;
        }
        if (signuppasswordPasswordField.getText() == null || confirmPasswordPasswordField.getText() ==null || u.getPassword() == null){
            errorLabel.setText("Wpisz hasło");
            return false;
        }
        if (!signuppasswordPasswordField.getText().equals(confirmPasswordPasswordField.getText())){
            errorLabel.setText("Hasła nie są takie same");
            return false;
        }
            return true;
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        cancelButton.setOnAction(new EventHandler<ActionEvent>() {
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
