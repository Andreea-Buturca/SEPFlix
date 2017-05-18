package client;

import client.controller.ClientConnection;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.MenuBar;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import server.model.User;

import java.io.IOException;
import java.net.URL;

public class Main extends Application {

    private static BorderPane root = new BorderPane();
    public static ClientConnection clientConnection;
    public static User loggedUser;
    public static Stage stage;
    /*@Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("Hello World");
        primaryStage.setScene(new Scene(root, 300, 275));
        primaryStage.show();


    }*/

    @Override
    public void start(Stage primaryStage) throws IOException {

        // loading FXML resources
        // note that we don't need PaneTwo in this class

        URL menuBarUrl = getClass().getResource("view/menubarGuest.fxml");
        MenuBar bar = FXMLLoader.load(menuBarUrl);

        URL paneOneUrl = getClass().getResource("view/homeScreen.fxml");
        AnchorPane paneOne = FXMLLoader.load(paneOneUrl);


        // constructing our scene using the static root

        root.setTop(bar);
        root.setCenter(paneOne);

        Scene scene = new Scene(root, 1000, 600);

        primaryStage.setScene(scene);
        primaryStage.setTitle("SEPFlix");
        primaryStage.show();
        stage = primaryStage;
    }


    public static void main(String[] args) {
        clientConnection = ClientConnection.getClientConnection();

        launch(args);
    }
}
