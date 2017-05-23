package server.mediator;

import com.google.gson.internal.StringMap;
import server.Main;
import server.controller.connectionDB.DatabaseConnection;
import server.model.User;

import java.util.ArrayList;

/**
 * Created by Stela on 17.5.2017.
 */
public class Facade {

    public static User getUserByUsername(String username){
        return DatabaseConnection.getDatabaseConnection().getUserByUserName(username);
    }

    public static ArrayList<StringMap<Object>> getUserFavourite(User user){
        return user.getFavourites();
    }

    public static void addMovieToFavourites(User user, StringMap<Object> movie){
        user.addFavourite(movie);
    }

    public static String getUsername(User user){
        return user.getUserName();
    }

    public static void removeFromFavourites(User loggedUser, Double id) {
        loggedUser.removeFavourite(id);
    }

    public static boolean hasFavourites(User user){
        return user.hasFavourites();
    }
}
