package client.controller;

import client.Main;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by Marek on 17-May-17.
 */
public class ShowprofileController implements Initializable{
    public TextField usernameField;
    public TextField nameField;
    public TextField surnameField;
    public TextField emailField;
    public Button editButton;
    public Button saveButton;
    public PasswordField passwordField;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        if(Main.loggedUser!=null){
            usernameField.setText(Main.loggedUser.getUserName());
            nameField.setText(Main.loggedUser.getFirstName());
            surnameField.setText(Main.loggedUser.getLastName());
            emailField.setText(Main.loggedUser.getEmail());
        }
    }
    public void edit(ActionEvent actionEvent) {
        if (Main.loggedUser!=null) {
            nameField.setEditable(true);
            surnameField.setEditable(true);
            emailField.setEditable(true);
            passwordField.setEditable(true);
        }
    }

    public void save(ActionEvent actionEvent) {
        if (Main.loggedUser!=null) {
            nameField.setEditable(false);
            surnameField.setEditable(false);
            emailField.setEditable(false);
            passwordField.setEditable(false);
            Helper.addDataToRequest("Action", "editProfile");
            //todo validation
            // TODO: 17-May-17 the password is empty for now since password from server is hashed
            Helper.addDataToRequest("Username", usernameField.getText());
            if (!passwordField.getText().isEmpty()) {
                Helper.addDataToRequest("Password", Helper.get_SHA_512_SecurePassword(passwordField.getText()));
            }else {
                Helper.addDataToRequest("Password",Main.loggedUser.getPassword());
            }
            Helper.addDataToRequest("FirstName", nameField.getText());
            Helper.addDataToRequest("SecondName", surnameField.getText());
            Helper.addDataToRequest("Email", emailField.getText());
            Helper.sendRequest();
        }
    }

    public void revert(ActionEvent actionEvent) {
        if(Main.loggedUser!=null){
            usernameField.setText(Main.loggedUser.getUserName());
            nameField.setText(Main.loggedUser.getFirstName());
            surnameField.setText(Main.loggedUser.getLastName());
            emailField.setText(Main.loggedUser.getEmail());
        }
        nameField.setEditable(false);
        surnameField.setEditable(false);
        emailField.setEditable(false);
        passwordField.setEditable(false);

    }


}
