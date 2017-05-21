package server.controller.connectionSocket;

import com.google.gson.internal.StringMap;
import server.Main;
import server.model.User;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

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
                StringMap<Object> data = Main.gson.fromJson(json, StringMap.class);

                switch ((String) data.get("Action")) {
                    case "register":
                        User userRegister = new User(data, false);
                        Main.databaseConnection.registerUser(userRegister);
                        break;
                    case "login":
                        StringMap<Object> returnData = new StringMap<>();
                        returnData.put("Action", "login");
                        User userLogin = Main.databaseConnection.getUserByUserName((String) data.get("Username"));
                        if (userLogin != null) {
                            if (userLogin.getPassword().equals(data.get("Password"))) {
                                returnData.putAll(userLogin.toMap(false));
                                returnData.put("Status", "success");
                            } else {
                                returnData.put("Status", "error");
                            }
                        } else {
                            returnData.put("Status", "error");
                        }
                        sendSmtToClient(Main.gson.toJson(returnData));
                        break;
                    case "editProfile":
                        System.out.println(data.toString());
                        if (data.get("NewPassword") != null) {
                            if (!(Main.databaseConnection.getUserByUserName((String) data.get("Username")).getPassword().equals(data.get("OldPassword")))) {
                                //todo send alert to client
                                break;
                            }
                        }
                        User userEdit = new User(data, false);
                        Main.databaseConnection.updateUserInformations(userEdit);
                        break;
                    case "LatestMovies":
                        StringMap<Object> returnLatestMovies = new StringMap<>();
                        returnLatestMovies.put("Action", "LatestMovies");
                        returnLatestMovies.put("LatestMovies", Main.connectionREST.getLatestMovies());
                        sendSmtToClient(Main.gson.toJson(returnLatestMovies));
                        System.out.println(returnLatestMovies.toString());
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
