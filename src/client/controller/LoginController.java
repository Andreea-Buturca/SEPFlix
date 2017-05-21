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
    public int errorCode = -1;

    public LoginController() {
        Main.loginC=this;
    }

    public void logIn(ActionEvent actionEvent) throws IOException, InterruptedException {
        //todo validation
        Helper.addDataToRequest("Action", "login");
        Helper.addDataToRequest("Username", usernameField.getText());
        Helper.addDataToRequest("Password", Helper.get_SHA_512_SecurePassword(passwordField.getText()));
        Helper.sendRequest();
        Thread.sleep(1500);
        if (errorCode==1) loginView();
        else if (errorCode==2) loginError();
        // TODO: 21-May-17 find better way to do this
    }

    private void loginView() throws IOException {
        BorderPane root = new BorderPane();
        URL menuBarUrl = getClass().getResource("../view/menubarLogged.fxml");
        MenuBar bar = FXMLLoader.load(menuBarUrl);
        URL paneOneUrl = getClass().getResource("../view/listOfMovies.fxml");
        AnchorPane paneOne = FXMLLoader.load(paneOneUrl);
        root.setTop(bar);
        root.setCenter(paneOne);
        Scene scene = new Scene(root);
        Main.stage.setScene(scene);
        Main.stage.show();
    }

    private void loginError(){
        Helper.alertdisplay("Error", "Wrong username or password.");
    }
}
