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
        System.out.println("username: "+usernameField.getText());
        System.out.println("password: "+passwordField.getText());
    }
}
