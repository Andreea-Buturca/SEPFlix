package server.controller.connectionSocket;

import com.google.gson.Gson;
import server.Main;
import server.model.User;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.HashMap;

/**
 * Created by martin on 15/05/2017.
 */
public class ServerCommunication implements Runnable {
    private ObjectInputStream inFromClient;
    private ObjectOutputStream outToClient;

    public ServerCommunication(Socket clientSocket) throws IOException {
        outToClient = new ObjectOutputStream(clientSocket.getOutputStream());
        inFromClient = new ObjectInputStream(clientSocket.getInputStream());
    }

    public void run() {
        try {
            while (true) {
                String json = (String) inFromClient.readObject();
                //todo access to model through something
                System.out.println(json);
                HashMap<String, String> data = new Gson().fromJson(json, HashMap.class);

                switch (data.get("Action")) {
                    case "register":
                        User user = new User(data.get("Username"), data.get("Password"), data.get("FirstName"), data.get("SecondName"), data.get("Email"));
                        Main.databaseConnection.registerUser(user);
                }

            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void sendSmtToClient(String json) {
        try {
            outToClient.writeObject(json);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
