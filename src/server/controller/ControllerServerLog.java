package server.controller;

import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import server.Main;

/**
 * Created by andreea on 5/24/2017.
 */
public class ControllerServerLog {
    public TableView table;
    public TableColumn ipAddress;
    public TableColumn logIn;
    public TableColumn action;
    public TableColumn time;
    public Button saveLog;

    public void saveLog(ActionEvent actionEvent) {
        Main.serverLogger.saveLog();
    }
}
