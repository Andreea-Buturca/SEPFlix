package server.controller.connectionDB;

import server.model.User;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Created by martin on 15/05/2017.
 */
public class DatabaseConnection {

    private final String URL = DBConnectionParameters.getURL();
    private final String USER = DBConnectionParameters.getUSER();
    private final String PASSWORD = DBConnectionParameters.getPASSWORD();
    private final String DATABASE = DBConnectionParameters.getDATABASE();
    private static DatabaseConnection databaseConnection;
    Connection connection = null;

    private DatabaseConnection() {
        try {
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection(
                    "jdbc:postgresql://" + URL + DATABASE
                    , USER
                    , PASSWORD);
            connection.setSchema("public");
            //connection.prepareStatement();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    public static DatabaseConnection getDatabaseConnection() {
        if (databaseConnection == null) {
            databaseConnection = new DatabaseConnection();
        }
        return databaseConnection;
    }

    public void registerUser(User user) {
        try {
            PreparedStatement statement = connection.prepareStatement(
                    "INSERT INTO users " +
                            "(user_name, name, sure_name, email, password)" +
                            "VALUES (?,?,?,?,?)"
            );
            statement.setString(1, user.getUserName());
            statement.setString(2, user.getFirstName());
            statement.setString(3, user.getLastName());
            statement.setString(4, user.getEmail());
            statement.setString(5, user.getPassword());
            statement.execute();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
