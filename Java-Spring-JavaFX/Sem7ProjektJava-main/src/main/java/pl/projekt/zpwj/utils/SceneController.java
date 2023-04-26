package pl.projekt.zpwj.utils;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import pl.projekt.zpwj.LoggedInUser;
import pl.projekt.zpwj.controller.LoggedInControler;
import pl.projekt.zpwj.model.User;

import java.io.IOException;

import static pl.projekt.zpwj.ZpwjApplication.springContext;

public class SceneController {
    private static FXMLLoader fxmlLoader;

    public static void newWindow(ActionEvent event, String title, String fxmlFile, User u) throws IOException {
        fxmlLoader = new FXMLLoader();
        fxmlLoader.setControllerFactory(springContext::getBean);
        fxmlLoader.setLocation(SceneController.class.getResource(fxmlFile));
        Parent root = fxmlLoader.load();

        LoggedInControler loggedInControler = fxmlLoader.getController();
        loggedInControler.setUser(u);

        LoggedInUser.setLoggedInUser(u);
        Scene scene = new Scene(root);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setTitle(title);
        window.setScene(scene);
    }

    public static void newWindow(ActionEvent event, String title, String fxmlFile) throws IOException {
        fxmlLoader = new FXMLLoader();
        fxmlLoader.setControllerFactory(springContext::getBean);
        fxmlLoader.setLocation(SceneController.class.getResource(fxmlFile));
        Parent root = fxmlLoader.load();
        Scene scene = new Scene(root);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setTitle(title);
        window.setScene(scene);
    }
}
