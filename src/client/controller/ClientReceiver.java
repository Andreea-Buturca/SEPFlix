package client.controller;

import client.Main;
import com.google.gson.Gson;
import server.model.User;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.HashMap;

/**
 * Created by martin on 15/05/2017.
 */
public class ClientReceiver implements Runnable {
    private ObjectInputStream inFromServer;

    public ClientReceiver(ObjectInputStream inFromServer) {
        this.inFromServer = inFromServer;
    }


    @Override
    public void run() {
        try {
            while (true) {
                Object object = inFromServer.readObject();
                System.out.println(object);
                HashMap<String,String> response = new Gson().fromJson((String) object, HashMap.class);
                if (response.get("Action").equals("login")&&response.get("Status").equals("success")){
                    Main.loggedUser = new User(response,false);
                    // TODO: 17-May-17 password from server is hashed, i need method to unhash it.
                    // TODO: 17-May-17 bring model classes to client I guess
                }
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
