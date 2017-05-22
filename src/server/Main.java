package server;

import com.google.gson.Gson;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import server.controller.connectionDB.DatabaseConnection;
import server.controller.connectionREST.ConnectionREST;
import server.controller.connectionSocket.ServerConnection;

import java.net.Socket;

import static java.lang.Thread.sleep;

public class Main extends Application {

    public static DatabaseConnection databaseConnection;
    public static ServerConnection serverConnection;
    public static ConnectionREST connectionREST;
    public static Gson gson;

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("./view/sample.fxml"));
        primaryStage.setTitle("Hello World");
        primaryStage.setScene(new Scene(root, 300, 275));
        primaryStage.show();
    }


    public static void main(String[] args) {
        gson = new Gson();
        serverConnection = new ServerConnection();
        new Thread(serverConnection).start();
        databaseConnection = DatabaseConnection.getDatabaseConnection();
        connectionREST = new ConnectionREST();
        //System.out.println(connectionREST.getLatestMovies());
        System.out.println(connectionREST.getMovie(283995));
        //connectionREST.getRequest("search/movie", "&query=Pe");
        System.out.println("som tu");


        //todo Andrea
        //Wait for running client to show examples (No need later)
        try {
            sleep(7000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //I made methods for you to get all connected clients you call it in your controller like: Main.serverConnection. ....
        //here is example
        System.out.println(serverConnection.getClients().size());
        //print IP of clients
        for (Socket client : serverConnection.getClients()) {
            System.out.println(client.getRemoteSocketAddress());
        }
        launch(args);
    }
}
