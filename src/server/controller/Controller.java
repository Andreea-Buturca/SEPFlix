package server.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.BorderPane;
import server.Main;

import java.net.Socket;
import java.net.URL;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;
import java.util.ResourceBundle;

public class Controller implements Initializable, Observer {
    public Label labelStartingServer;
    public Label labelYesNo;
    public Label labelClients;
    public Label labelNoClients;
    public Label labelIpAddress;
    public Button buttonRefresh;
    public ListView listView;

    public Controller() {
        Main.serverConnection.addObserver(this);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

          BorderPane root = new BorderPane();
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
        ObservableList<String> ipaddressList= FXCollections.observableArrayList();
        ipaddressList.addAll(result);
        listView.setItems(ipaddressList);
    }



    public void refresh(ActionEvent actionEvent) {
        labelNoClients.setText(Main.serverConnection.getClients().size() + "");
        ArrayList<Socket> clients = new ArrayList<Socket>();
        clients = Main.serverConnection.getClients();
        String result = "";
        for (int i = 0; i < clients.size(); i++) {
            result += (i+1)+": "+ clients.get(i).getRemoteSocketAddress() + "  \n";
        }
        ObservableList<String> ipaddressList= FXCollections.observableArrayList();
        ipaddressList.addAll(result);
        listView.setItems(ipaddressList);
    }

    @Override
    public void update(Observable o, Object arg) {
        ArrayList<Socket> clients = (ArrayList<Socket>) arg;
        //other implementation
    }
}