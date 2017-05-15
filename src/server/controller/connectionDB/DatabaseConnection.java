package server.controller.connectionDB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Created by martin on 15/05/2017.
 */
public class DatabaseConnection {

    //Default for localhost
    //final String URL = "localhost:5432/";
    private final String URL = "postgresql.websupport.sk/";
    private final String USER = "movieSepDB";
    private final String PASSWORD = "movieSepDB";
    private final String DATABASE = "movieSepDB";
    Connection connection = null;

    public DatabaseConnection() {
        try {
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection(
                    "jdbc:postgresql://" + URL + DATABASE
                    , USER
                    , PASSWORD);
            connection.setSchema("public");
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }
}
