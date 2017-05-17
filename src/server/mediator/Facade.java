package server.mediator;

import server.controller.connectionDB.DatabaseConnection;
import server.model.User;

/**
 * Created by Stela on 17.5.2017.
 */
public class Facade {

    public static User getUserByUsername(String username){
        return DatabaseConnection.getDatabaseConnection().getUserByUserName(username);
    }
}
