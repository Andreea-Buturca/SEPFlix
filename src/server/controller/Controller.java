package server.controller;

import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import server.Main;

import java.net.Socket;
import java.net.URL;
import java.util.ArrayList;
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
        ArrayList<Socket> clients = new ArrayList<Socket>();
        clients = Main.serverConnection.getClients();
        String result = "";
        for (int i = 0; i < clients.size(); i++) {
            result += (i+1)+": "+ clients.get(i).getRemoteSocketAddress() + "  \n";
        }
        labelIp.setText(result);
    }

    public void refresh(ActionEvent actionEvent) {
        labelNoClients.setText(Main.serverConnection.getClients().size() + "");
        ArrayList<Socket> clients = new ArrayList<Socket>();
        clients = Main.serverConnection.getClients();
        String result = "";
        for (int i = 0; i < clients.size(); i++) {
            result += (i+1)+": "+ clients.get(i).getRemoteSocketAddress() + "  \n";
        }
        labelIp.setText(result);
    }
}
