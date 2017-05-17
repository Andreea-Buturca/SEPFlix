package client.controller;

import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

/**
 * Created by Marek on 17-May-17.
 */
public class ShowprofileController {
    public TextField usernameField;
    public TextField nameField;
    public TextField surnameField;
    public TextField emailField;
    public Button editButton;
    public Button saveButton;
    public PasswordField passwordField;

    // TODO: 17-May-17 when this gui is initialized, data about current user is loaded into fields

    public void edit(ActionEvent actionEvent) {
        usernameField.setEditable(true);
        nameField.setEditable(true);
        surnameField.setEditable(true);
        emailField.setEditable(true);
        passwordField.setEditable(true);

    }

    public void save(ActionEvent actionEvent) {
        // TODO: 17-May-17 send edited data to server and change them

        usernameField.setEditable(false);
        nameField.setEditable(false);
        surnameField.setEditable(false);
        emailField.setEditable(false);
        passwordField.setEditable(false);
    }
}
