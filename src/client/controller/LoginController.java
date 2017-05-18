package client.controller;

import client.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.MenuBar;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;

import java.io.IOException;
import java.net.URL;

/**
 * Created by Marek on 17-May-17.
 */
public class LoginController {
    public TextField usernameField;
    public PasswordField passwordField;
    public Button loginButton;

    public void logIn(ActionEvent actionEvent) throws IOException {
        //todo validation
        Helper.addDataToRequest("Action", "login");
        Helper.addDataToRequest("Username", usernameField.getText());
        Helper.addDataToRequest("Password", Helper.get_SHA_512_SecurePassword(passwordField.getText()));
        Helper.sendRequest();

        BorderPane root = new BorderPane();
        URL menuBarUrl = getClass().getResource("../view/menubarLogged.fxml");
        MenuBar bar = FXMLLoader.load(menuBarUrl);
        URL paneOneUrl = getClass().getResource("../view/homeScreen.fxml");
        AnchorPane paneOne = FXMLLoader.load(paneOneUrl);
        root.setTop(bar);
        root.setCenter(paneOne);
        Scene scene = new Scene(root);
        Main.stage.setScene(scene);
        Main.stage.show();
    }
}
