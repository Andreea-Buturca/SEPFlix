package client.controller;

import java.io.IOException;
import java.io.ObjectInputStream;

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
                System.out.println(inFromServer.readObject());
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
