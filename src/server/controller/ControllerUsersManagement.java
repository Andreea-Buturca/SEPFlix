package server.controller;

import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import server.Main;

/**
 * Created by andreea on 5/24/2017.
 */
public class ControllerUsersManagement {
    public Button buttonRemove;
    public TableView table;
    public TableColumn username;
    public TableColumn firstName;
    public TableColumn lastName;
    public TableColumn credit;
    public Button buttonAddCredit;
    public Button buttonHistory;

    public void removeUser(ActionEvent actionEvent) {
        if (Main.serverConnection != null) {

        }
    }

    public void addCredit(ActionEvent actionEvent) {
    }

    public void showHistory(ActionEvent actionEvent) {
    }
}
