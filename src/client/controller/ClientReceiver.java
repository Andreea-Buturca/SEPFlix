package client.controller;

import client.Main;
import com.google.gson.Gson;
import com.google.gson.internal.StringMap;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.MenuBar;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import server.model.User;
import sun.nio.cs.ext.MacArabic;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.URL;
import java.util.ArrayList;

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
                StringMap<Object> response = new Gson().fromJson((String) object, StringMap.class);
                switch ((String) response.get("Action")) {
                    case "login":
                        if (response.get("Status").equals("success")) {
                            Main.loggedUser = new User(response, false);
                            Main.loginC.interupt(0);
                            // TODO: 17-May-17 password from server is hashed, i need method to unhash it.
                            // TODO: 17-May-17 bring model classes to client I guess

                        } else {
                            Main.loginC.interupt(1);
                        }
                        break;
                    case "LatestMovies":
                        ArrayList<StringMap<Object>> latestMovies = (ArrayList<StringMap<Object>>) response.get("LatestMovies");
                        Main.listMainC.interupt(latestMovies);
                        break;
                    case "MovieDetail":
                        Thread.sleep(500);
                        System.out.println("idem tu");
                        StringMap<Object> movie = (StringMap<Object>) response.get("MovieDetail");
                        System.out.println(movie);
                        // TODO: 23-May-17 for now im saving response not stringmap of movie :D
                        Main.movieInfoC.interupt(response);
                        break;
                    case "SearchMovie":
                        ArrayList<StringMap<Object>> movies = (ArrayList<StringMap<Object>>) response.get("SearchList");
                        Main.listMainC.interupt(movies);
                        break;
                    case "FavouriteMovies":
                        ArrayList<StringMap<Object>> favourites = (ArrayList<StringMap<Object>>) response.get("FavouriteMovies");
                        Main.facouritesC.interupt(favourites);
                }
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
