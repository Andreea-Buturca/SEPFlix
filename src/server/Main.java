package server;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import server.controller.connectionDB.DatabaseConnection;
import server.controller.connectionREST.ConnectionREST;
import server.controller.connectionSocket.ServerConnection;

public class Main extends Application {

    public static DatabaseConnection databaseConnection;

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("./view/sample.fxml"));
        primaryStage.setTitle("Hello World");
        primaryStage.setScene(new Scene(root, 300, 275));
        primaryStage.show();
    }


    public static void main(String[] args) {
        //launch(args);

        ServerConnection serverConnection = new ServerConnection();
        new Thread(serverConnection).start();
        databaseConnection = new DatabaseConnection();
        ConnectionREST connectionREST = new ConnectionREST();
        connectionREST.getRequest("search/movie", "&query=Pe");
        System.out.println("som tu");

    }
}