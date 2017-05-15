package server.model;

/**
 * Created by andreea on 5/15/2017.
 */
public class User {
    private String password;
    private String userName;
    private String firstName;
    private String lastName;
    private String email;

    public User(String userName, String password, String firstName, String lastName, String email) {
        this.password = password;
        this.userName = userName;
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

    public void setPassword(String password) {
        this.password = password;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String toString() {
        return "User name: " + this.userName;
    }

    public String toStringFull() {
        return "User name: " + this.userName + ", first name: " + this.firstName + ", last name: " + this.lastName + ", email: " + this.email;
    }
}
