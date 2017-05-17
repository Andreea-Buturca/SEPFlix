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
                //todo handle status if something invalid
                String json = (String) inFromClient.readObject();
                //todo access to model through something
                System.out.println(json);
                HashMap<String, String> data = new Gson().fromJson(json, HashMap.class);

                switch (data.get("Action")) {
                    case "register":
                        User userRegister = new User(data, false);
                        Main.databaseConnection.registerUser(userRegister);
                        break;
                    case "login":
                        HashMap<String, String> returnData = new HashMap<>();
                        returnData.put("Action", "login");
                        User userLogin = Main.databaseConnection.getUserByUserName(data.get("Username"));
                        if (userLogin != null) {
                            if (userLogin.getPassword().equals(data.get("Password"))) {
                                returnData.putAll(userLogin.toHashMap());
                                returnData.put("Status", "success");
                            } else {
                                returnData.put("Status", "error");
                            }
                        } else {
                            returnData.put("Status", "error");
                        }
                        sendSmtToClient(new Gson().toJson(returnData));
                        break;
                    case "editProfile":
                        User userEdit = new User(data, false);
                        Main.databaseConnection.updateUserInformations(userEdit);
                        break;
                }

            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void sendSmtToClient(String json) {
        try {
            System.out.println(json);
            outToClient.writeObject(json);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
