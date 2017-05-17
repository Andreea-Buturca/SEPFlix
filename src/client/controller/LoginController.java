package client.controller;

import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

/**
 * Created by Marek on 17-May-17.
 */
public class LoginController {
    public TextField usernameField;
    public PasswordField passwordField;
    public Button loginButton;

    public void logIn(ActionEvent actionEvent) {
        //todo validation
        Helper.addDataToRequest("Action", "login");
        Helper.addDataToRequest("Username", usernameField.getText());
        Helper.addDataToRequest("Password", Helper.get_SHA_512_SecurePassword(passwordField.getText()));
        Helper.sendRequest();
    }
}
