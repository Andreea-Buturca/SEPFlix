package server.controller.connectionSocket;

import com.google.gson.internal.StringMap;
import server.Main;
import server.model.Movie;
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
                StringMap<Object> returnData = new StringMap<>();

                switch ((String) data.get("Action")) {
                    case "register":
                        User userRegister = new User(data, false);
                        Main.databaseConnection.registerUser(userRegister);
                        break;
                    case "login":
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
                        returnData.clear();
                        break;
                    case "editProfile":
                        System.out.println(data.toString());
                        User userEdit = new User(data, false);
                        if (data.get("NewPassword") != null) {
                            if (!(Main.databaseConnection.getUserByUserName((String) data.get("Username")).getPassword().equals(data.get("OldPassword")))) {
                                //todo send alert to client Martin
                                break;
                            } else {
                                Main.databaseConnection.changePassword(userEdit);
                            }
                        }
                        Main.databaseConnection.updateUserInformation(userEdit);
                        break;
                    case "LatestMovies":
                        returnData.put("Action", "LatestMovies");
                        returnData.put("LatestMovies", Main.connectionREST.getLatestMovies());
                        sendSmtToClient(Main.gson.toJson(returnData));
                        returnData.clear();
                        break;
                    case "MovieDetail":
                        returnData.put("Action", "MovieDetail");
                        Movie movie = Main.connectionREST.getMovie((Integer) data.get("id"));
                        returnData.putAll(movie.toStringMap());
                        sendSmtToClient(Main.gson.toJson(returnData));
                        returnData.clear();
                        break;
                    case "SearchMovie":
                        returnData.put("Action", "SearchMovie");
                        returnData.put("SearchList", Main.connectionREST.searchMovie((String) data.get("SearchField")));
                        sendSmtToClient(Main.gson.toJson(returnData));
                        returnData.clear();
                        break;
                    default:
                        returnData.put("Action", "Alert");
                        returnData.put("Message", "Wrong Action");
                        sendSmtToClient(Main.gson.toJson(returnData));
                        returnData.clear();
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
