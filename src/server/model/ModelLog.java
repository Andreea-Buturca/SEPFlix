package server.model;

import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;

import java.util.Date;

/**
 * Created by Stela on 25.5.2017.
 */
public class ModelLog {
    private SimpleStringProperty ip;
    private SimpleStringProperty action;
    private SimpleStringProperty message;
    private SimpleBooleanProperty loggedIn;
    private Date date;

    public ModelLog(String ip, String action, String message,Boolean loggedIn, Date date){
        this.ip = new SimpleStringProperty(ip);
        this.action = new SimpleStringProperty(action);
        this.message = new SimpleStringProperty(message);
        this.loggedIn = new SimpleBooleanProperty(loggedIn);
        this.date = date;
    }

    public ModelLog(String ip, String action, Boolean loggedIn, Date date){
        this.ip = new SimpleStringProperty(ip);
        this.action = new SimpleStringProperty(action);
        this.loggedIn = new SimpleBooleanProperty(loggedIn);
        this.date = date;
    }

    public String getIp(){
        return ip.get();
    }
    public String getAction(){
        return action.get();
    }
    public String getMessage(){
        return message.get();
    }
    public Boolean getLoggedIn(){
        return loggedIn.get();
    }
    public Date getDate(){
        return date;
    }

}
