package server.controller;

import client.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;

import java.io.IOException;
import java.net.URL;

/**
 * Created by andreea on 5/24/2017.
 */
public class MenuController {
    public MenuItem home;
    public MenuItem homescreen;
    public MenuItem serverLog;
    public MenuItem usersManag;

    public void changeServerMenu(ActionEvent actionEvent) throws IOException {
        BorderPane root = null;
        if ((actionEvent.getSource() == homescreen)) {
            root = new BorderPane();
            MenuBar bar = FXMLLoader.load(getClass().getResource("../view/menuServer.fxml"));
            root.setTop(bar);
            AnchorPane paneOne = FXMLLoader.load(getClass().getResource("../view/ServerHomeScreen.fxml"));
            root.setCenter(paneOne);
        } else if ((actionEvent.getSource() == serverLog)) {
            root = new BorderPane();
            MenuBar bar = FXMLLoader.load(getClass().getResource("../view/menuServer.fxml"));
            root.setTop(bar);
            AnchorPane paneOne = FXMLLoader.load(getClass().getResource("../view/ServerLog.fxml")); //!!!
            root.setCenter(paneOne);
        } else if ((actionEvent.getSource() == usersManag)) {
            root = new BorderPane();
            MenuBar bar = FXMLLoader.load(getClass().getResource("../view/menuServer.fxml"));
            root.setTop(bar);
            AnchorPane paneOne = FXMLLoader.load(getClass().getResource("../view/UsersManagement.fxml"));
            root.setCenter(paneOne);
        }
        if (root != null) {
            Scene scene = new Scene(root);
            Main.stage.setScene(scene);
            Main.stage.show();
        }
    }
}
