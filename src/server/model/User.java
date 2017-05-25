package server.model;

import com.google.gson.internal.StringMap;
import javafx.beans.property.SimpleStringProperty;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Objects;

/**
 * The Class represents a User
 * Created by andreea on 5/15/2017.
 */
public class User {

    private SimpleStringProperty password;
    private SimpleStringProperty userName;
    private SimpleStringProperty firstName;
    private SimpleStringProperty lastName;
    private SimpleStringProperty email;

    ArrayList<StringMap<Object>> favourites = new ArrayList<>();

    /**
     * Constructs a User object by taking a HashMap and a boolean as arguments
     *
     * @param data         creates a new map with the same mappings as the given map
     * @param hashPassword boolean for hashpassword
     */
    public User(StringMap<Object> data, boolean hashPassword) {
        this.userName = new SimpleStringProperty((String) data.get("Username"));
        setPassword((String) data.get("Password"), hashPassword);
        this.firstName = new SimpleStringProperty((String) data.get("FirstName"));
        this.lastName = new SimpleStringProperty((String) data.get("SecondName"));
        this.email = new SimpleStringProperty((String) data.get("Email"));
    }

    /**
     * Constructs a User object by taking a ResultSet and a boolean as arguments
     *
     * @param resultSet    defines a table of data representing a database result set, which
     *                     is usually generated by executing a statement that queries the database.
     * @param hashPassword boolean to be checked
     */
    public User(ResultSet resultSet, boolean hashPassword) {
        try {
            this.userName = new SimpleStringProperty(resultSet.getString("user_name"));
            setPassword(resultSet.getString("password"), hashPassword);
            this.firstName = new SimpleStringProperty(resultSet.getString("name"));
            this.lastName = new SimpleStringProperty(resultSet.getString("surname"));
            this.email = new SimpleStringProperty(resultSet.getString("email"));
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    /**
     * Constructs a new User object
     *
     * @param userName     the user's username
     * @param password     the password chosen by the user
     * @param firstName    the user's first name
     * @param lastName     the user's last name
     * @param email        the user's email
     * @param hashPassword the user's hashPassword
     */
    public User(String userName, String password, String firstName, String lastName, String email, boolean hashPassword) {
        this.userName = new SimpleStringProperty(userName);
        setPassword(password, hashPassword);
        this.firstName = new SimpleStringProperty(firstName);
        this.lastName = new SimpleStringProperty(lastName);
        this.email = new SimpleStringProperty(email);
    }

    public boolean hasFavourites() {
        return !favourites.isEmpty();
    }

    public void addFavourite(StringMap<Object> movie) {
        favourites.add(movie);
    }

    public void removeFavourite(Double id) {
        ArrayList<StringMap<Object>> toremove = new ArrayList<>();
        for (StringMap<Object> movie : favourites) {
            Double idM = (Double) movie.get("id");
            if (Objects.equals(idM, id)) {
                toremove.add(movie);
            }
        }
        favourites.removeAll(toremove);
    }

    public ArrayList<StringMap<Object>> getFavourites() {
        return favourites;
    }

    /**
     * returns the email
     *
     * @return the email
     */
    public String getEmail() {
        return email.get();
    }

    /**
     * returns first name of the user
     *
     * @return first name
     */
    public String getFirstName() {
        return firstName.get();
    }

    /**
     * returns the last name of the user
     *
     * @return last name
     */
    public String getLastName() {
        return lastName.get();
    }

    /**
     * returns the user's password
     *
     * @return the password
     */
    public String getPassword() {
        return password.get();
    }

    /**
     * returns user name
     *
     * @return user name
     */
    public String getUserName() {
        return userName.get();
    }

    /**
     * sets up the email with the specified email
     *
     * @param email the email to be set
     */
    public void setEmail(String email) {
        this.email.set(email);
    }

    /**
     * sets up the first name with the given string
     *
     * @param firstName the first name to be set up
     */
    public void setFirstName(String firstName) {
        this.firstName.set(firstName);
    }

    /**
     * sets up the user's last name with the given argument
     *
     * @param lastName the user's last name to be set up
     */
    public void setLastName(String lastName) {
        this.lastName.set(lastName);
    }

    /**
     * sets up the user's password
     *
     * @param password     the user's password to be set up
     * @param hashPassword boolean for setting the password
     */
    public void setPassword(String password, boolean hashPassword) {
        if (hashPassword) {
            this.password = new SimpleStringProperty(this.get_SHA_512_SecurePassword(password));
        } else {
            this.password = new SimpleStringProperty(password);
        }
    }

    /**
     * sets up the user name
     *
     * @param userName the user name to be set up
     */
    public void setUserName(String userName) {
        this.userName.set(userName);
    }

    /**
     * Returns a secured password by converting the password given as argument into one encoded
     *
     * @param passwordToHash the password to be encoded
     * @return a string of the new encoded password
     */
    private String get_SHA_512_SecurePassword(String passwordToHash) {
        String salt = "myTopSecredSalt";
        String generatedPassword = null;
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-512");
            md.update(salt.getBytes("UTF-8"));
            byte[] bytes = md.digest(passwordToHash.getBytes("UTF-8"));
            StringBuilder sb = new StringBuilder();
            for (byte aByte : bytes) {
                sb.append(Integer.toString((aByte & 0xff) + 0x100, 16).substring(1));
            }
            generatedPassword = sb.toString();
        } catch (NoSuchAlgorithmException | UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return generatedPassword;
    }

    /**
     * returns a hashmap object by associating the specified values for username, password, first name, second name, and email with the specified keys in this map.
     *
     * @param addPassword boolean to be checked
     * @return data of type HashMap
     */
    public StringMap<Object> toMap(boolean addPassword) {
        StringMap<Object> data = new StringMap<>();
        data.put("Username", getUserName());
        if (addPassword) {
            data.put("Password", getPassword());
        }
        data.put("FirstName", getFirstName());
        data.put("SecondName", getLastName());
        data.put("Email", getEmail());

        return data;
    }

    /**
     * returns a string representation of user returning only the user name
     *
     * @return String representation of user
     */
    public String toString() {
        return "User name: " + this.userName.get();
    }

    /**
     * returns a string representation of the user, in a complete format
     *
     * @return String representation of user
     */
    public String toStringFull() {
        return "User name: " + this.userName.get() + ", first name: " + this.firstName.get() + ", last name: " + this.lastName.get() + ", email: " + this.email.get();
    }
}
