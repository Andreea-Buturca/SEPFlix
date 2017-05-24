package server.controller;

import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
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

    public Controller() {
        Main.serverConnection.addObserver(this);
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
        //Main.serverLogger.saveLog();
    }

    //todo after implementation delete method getClients() Martin
    @Override
    public void update(Observable o, Object arg) {
        ArrayList<Socket> clients = (ArrayList<Socket>) arg;
        //other implementation
        System.out.println("observer is working!!!!!!!!");
    }
}