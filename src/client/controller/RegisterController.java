package client.controller;

import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

/**
 * Created by Marek on 15-May-17.
 */
public class RegisterController {
    public Button registerButton;
    public TextField firstNameField;
    public TextField secondNameField;
    public TextField usernameField;
    public TextField emailField;
    public PasswordField passwordField;

    public void registerUser(ActionEvent actionEvent) {
        //todo validation

        Helper.addDataToRequest("Action", "register");
        Helper.addDataToRequest("Username", usernameField.getText());
        Helper.addDataToRequest("Password", Helper.get_SHA_512_SecurePassword(passwordField.getText()));
        Helper.addDataToRequest("FirstName", firstNameField.getText());
        Helper.addDataToRequest("SecondName", secondNameField.getText());
        Helper.addDataToRequest("Email", emailField.getText());
        Helper.sendRequest();
        Helper.successdisplay("Registered", "You are now user of SEPFlix");
    }
}
