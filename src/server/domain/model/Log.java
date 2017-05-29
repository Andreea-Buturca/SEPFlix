package server.domain.model;

import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;

import java.util.Date;

/**
 * Created by Stela on 25.5.2017.
 */
public class Log {
    private final SimpleStringProperty ip;
    private final SimpleStringProperty action;
    private final SimpleStringProperty message;
    private final SimpleBooleanProperty loggedIn;
    private final Date date;

    public Log(String ip, String action, String message, Boolean loggedIn) {
        this.ip = new SimpleStringProperty(ip);
        this.action = new SimpleStringProperty(action);
        this.message = new SimpleStringProperty(message);
        this.loggedIn = new SimpleBooleanProperty(loggedIn);
        this.date = new Date();
    }

    public Log(String ip, String action, Boolean loggedIn) {
        this.ip = new SimpleStringProperty(ip);
        this.action = new SimpleStringProperty(action);
        this.loggedIn = new SimpleBooleanProperty(loggedIn);
        this.message = new SimpleStringProperty();
        this.date = new Date();
    }

    public String getIp() {
        return ip.get();
    }

    public String getAction() {
        return action.get();
    }

    public String getMessage() {
        return message.get();
    }

    public Boolean getLoggedIn() {
        return loggedIn.get();
    }

    public Date getDate() {
        return date;
    }

    public String toString() {
        return "Log{" +
                "ip=" + ip.get() +
                ", action=" + action.get() +
                ", message=" + message.get() +
                ", loggedIn=" + loggedIn.get() +
                ", date=" + date.toString() +
                '}';
    }
}
