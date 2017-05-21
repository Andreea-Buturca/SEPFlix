package client.controller;

import client.Main;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by Marek on 17-May-17.
 */
public class ShowprofileController implements Initializable {

    public TextField nameField;
    public TextField surnameField;
    public TextField emailField;
    public Button editButton;
    public Button saveButton;
    public Label usernameField;
    public Button changepassButton;
    public PasswordField oldPassField;
    public PasswordField newPassField;
    public PasswordField confirmPassField;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        changepassButton.setVisible(false);
        if (Main.loggedUser != null) {
            usernameField.setText(Main.loggedUser.getUserName());
            nameField.setText(Main.loggedUser.getFirstName());
            surnameField.setText(Main.loggedUser.getLastName());
            emailField.setText(Main.loggedUser.getEmail());
        }

    }

    public void edit(ActionEvent actionEvent) {
        if (Main.loggedUser != null) {
            changepassButton.setVisible(true);
            nameField.setEditable(true);
            surnameField.setEditable(true);
            emailField.setEditable(true);
        }
    }

    public void save(ActionEvent actionEvent) {
        if (Main.loggedUser != null) {
            nameField.setEditable(false);
            surnameField.setEditable(false);
            emailField.setEditable(false);
            Helper.addDataToRequest("Action", "editProfile");
            //todo validation
            // TODO: 17-May-17 the password is empty for now since password from server is hashed
            Helper.addDataToRequest("Username", usernameField.getText());
            if (newPassField.getText().equals(confirmPassField.getText())) {
                Helper.addDataToRequest("OldPassword", Helper.get_SHA_512_SecurePassword(oldPassField.getText()));
                Helper.addDataToRequest("NewPassword", Helper.get_SHA_512_SecurePassword(newPassField.getText()));
            }else{
                Helper.addDataToRequest("OldPassword", "");
                Helper.addDataToRequest("NewPassword", "");
            }
            Helper.addDataToRequest("FirstName", nameField.getText());
            Helper.addDataToRequest("SecondName", surnameField.getText());
            Helper.addDataToRequest("Email", emailField.getText());
            Helper.sendRequest();
            // TODO: 21-May-17 Save data on server
        }
    }

    public void revert(ActionEvent actionEvent) {
        if (Main.loggedUser != null) {
            usernameField.setText(Main.loggedUser.getUserName());
            nameField.setText(Main.loggedUser.getFirstName());
            surnameField.setText(Main.loggedUser.getLastName());
            emailField.setText(Main.loggedUser.getEmail());
        }
        nameField.setEditable(false);
        surnameField.setEditable(false);
        emailField.setEditable(false);

        oldPassField.setVisible(false);
        oldPassField.setText("");
        newPassField.setVisible(false);
        newPassField.setText("");
        confirmPassField.setVisible(false);
        confirmPassField.setText("");
        changepassButton.setVisible(false);

    }


    public void changePass(ActionEvent actionEvent) {
        oldPassField.setVisible(true);
        newPassField.setVisible(true);
        confirmPassField.setVisible(true);
        changepassButton.setVisible(false);

    }
}
