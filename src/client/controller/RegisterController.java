package client.controller;

import client.Main;
import com.google.gson.Gson;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;

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
        /*User user = new User(
                usernameField.getText(),
                passwordField.getText(),
                firstNameField.getText(),
                secondNameField.getText(),
                emailField.getText()

        );*/

        Map<String, String> data = new HashMap<>();
        data.put("Action", "register");
        data.put("Username", usernameField.getText());
        data.put("Password", passwordField.getText());
        data.put("FirstName", firstNameField.getText());
        data.put("SecondName", secondNameField.getText());
        data.put("Email", emailField.getText());
        String json = new Gson().toJson(data);
        Main.clientConnection.sendSmtToServer(json);
    }


    //todo add it to some helper and hash password
    private static String get_SHA_512_SecurePassword(String passwordToHash) {
        String salt = "myTopSecredSalt";
        String generatedPassword = null;
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-512");
            md.update(salt.getBytes("UTF-8"));
            byte[] bytes = md.digest(passwordToHash.getBytes("UTF-8"));
            StringBuilder sb = new StringBuilder();
            for (byte aByte : bytes) {
                sb.append(Integer.toString((aByte & 0xff) + 0x100, 16).substring(1));
            }
            generatedPassword = sb.toString();
        } catch (NoSuchAlgorithmException | UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return generatedPassword;
    }
}
