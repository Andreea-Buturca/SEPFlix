package server.controller;

import javafx.fxml.Initializable;
import server.Main;
import server.controller.connectionSocket.ServerConnection;
import javafx.event.ActionEvent;

import java.awt.*;
import java.net.Socket;
import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {
    public Label labelStartingServer;
    public Label labelYesNo;
    public Label labelClients;
    public Label labelNoClients;
    public Label labelIpAddress;
    public Label labelIp;
    public Button buttonRefresh;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        if (Main.serverConnection != null) {
            labelYesNo.setText("Yes");
        } else labelYesNo.setText("No");
        labelNoClients.setText(Main.serverConnection.getClients().size() + "");
        for (Socket client : Main.serverConnection.getClients()) {
            labelIp.setText(client.getRemoteSocketAddress() + " ");
        }
    }

    public void refresh(ActionEvent actionEvent) {
        labelNoClients.setText(Main.serverConnection.getClients().size() + "");
        for (Socket client : Main.serverConnection.getClients()) {
            labelIp.setText(client.getRemoteSocketAddress()+"  ");
        }
    }
}
