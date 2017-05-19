package server.model;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

/**
 * Created by andreea on 5/15/2017.
 */
public class User {
    private String password;
    private String userName;
    private String firstName;
    private String lastName;
    private String email;

    public User(HashMap<String, String> data, boolean hashPassword) {
        this.userName = data.get("Username");
        setPassword(data.get("Password"), hashPassword);
        this.firstName = data.get("FirstName");
        this.lastName = data.get("SecondName");
        this.email = data.get("Email");
    }

    public User(ResultSet resultSet, boolean hashPassword) {
        try {
            this.userName = resultSet.getString("user_name");
            setPassword(resultSet.getString("password"), hashPassword);
            this.firstName = resultSet.getString("name");
            this.lastName = resultSet.getString("surname");
            this.email = resultSet.getString("email");
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public User(String userName, String password, String firstName, String lastName, String email, boolean hashPassword) {
        this.userName = userName;
        setPassword(password, hashPassword);
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getPassword() {
        return password;
    }

    public String getUserName() {
        return userName;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setPassword(String password, boolean hashPassword) {
        if (hashPassword) {
            this.password = this.get_SHA_512_SecurePassword(password);
        } else {
            this.password = password;
        }
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

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

    public HashMap<String, String> toHashMap(boolean addPassword) {
        HashMap<String, String> data = new HashMap<>();
        data.put("Username", getUserName());
        if (addPassword) {
            data.put("Password", getPassword());
        }
        data.put("FirstName", getFirstName());
        data.put("SecondName", getLastName());
        data.put("Email", getEmail());

        return data;
    }

    public String toString() {
        return "User name: " + this.userName;
    }

    public String toStringFull() {
        return "User name: " + this.userName + ", first name: " + this.firstName + ", last name: " + this.lastName + ", email: " + this.email;
    }
}
