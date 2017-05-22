package server.controller.connectionDB;

import server.model.User;

import java.sql.*;

/**
 * Created by martin on 15/05/2017.
 */
public class DatabaseConnection {

    private final String URL = DBParameters.getURL();
    private final String USER = DBParameters.getUSER();
    private final String PASSWORD = DBParameters.getPASSWORD();
    private final String DATABASE = DBParameters.getDATABASE();
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
                            "(user_name, name, surname, email, password)" +
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

    public User getUserByUserName(String userName) {
        User user = null;
        try {
            PreparedStatement statement = connection.prepareStatement(
                    "SELECT * FROM users WHERE user_name = ? LIMIT 1;"
            );
            statement.setString(1, userName);

            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                user = new User(resultSet, false);
            }
            resultSet.close();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

    public void updateUserInformation(User user) {
        try {
            PreparedStatement statement = connection.prepareStatement(
                    "UPDATE users " +
                            "SET name = ?, surname = ?, email = ?" +
                            "WHERE user_name = ?;"
            );
            statement.setString(1, user.getFirstName());
            statement.setString(2, user.getLastName());
            statement.setString(3, user.getEmail());
            statement.setString(4, user.getUserName());
            statement.execute();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void changePassword(User user) {
        try {
            PreparedStatement statement = connection.prepareStatement(
                    "UPDATE users " +
                            "SET  password = ?" +
                            "WHERE user_name = ?;"
            );
            statement.setString(1, user.getPassword());
            statement.setString(2, user.getUserName());
            statement.execute();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
