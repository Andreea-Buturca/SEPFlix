package server;

import com.google.gson.Gson;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.MenuBar;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import server.controller.connectionDB.DatabaseConnection;
import server.controller.connectionREST.ConnectionREST;
import server.controller.connectionSocket.ServerConnection;
import server.mediator.ServerLogger;

import java.net.URL;
import java.util.ArrayList;

public class Main extends Application {

    public static DatabaseConnection databaseConnection;
    public static ServerConnection serverConnection;
    public static ConnectionREST connectionREST;
    public static ServerLogger serverLogger;
    public static Gson gson;
    private static BorderPane root = new BorderPane();
    public static Stage stage;

    @Override
    public void start(Stage primaryStage) throws Exception {
        URL menuBarUrl = getClass().getResource("view/menuServer.fxml");
        MenuBar bar = FXMLLoader.load(menuBarUrl);

        URL paneOneUrl = getClass().getResource("view/ServerHomeScreen.fxml");
        AnchorPane paneOne = FXMLLoader.load(paneOneUrl);

        root.setTop(bar);
        root.setCenter(paneOne);

        Scene scene = new Scene(root, 1000, 600);

        primaryStage.setScene(scene);
        primaryStage.setTitle("Server");
        primaryStage.show();
        stage = primaryStage;
    }


    public static void main(String[] args) {
        gson = new Gson();
        serverLogger = new ServerLogger();
        serverConnection = new ServerConnection();
        new Thread(serverConnection).start();
        databaseConnection = DatabaseConnection.getDatabaseConnection();
        connectionREST = new ConnectionREST();

        //System.out.println(connectionREST.getLatestMovies());
        //System.out.println(connectionREST.getMovie(283995));
        //Movie movie = connectionREST.getMovie(283995);
        //databaseConnection.addFavouriteMovie("testik", 283995);
        //System.out.println(databaseConnection.getMovieById(2839295));
        //connectionREST.getRequest("search/movie", "&query=Pe");
        //System.out.println(databaseConnection.getUsers());
//        for (StringMap<Object> testik: connectionREST.searchMovie("Man")
//             ) {
//            System.out.println(testik.toString());
//        }
        /*User user1 = new User("");
        User user1 = new User("");
        ArrayList<User> users = new ArrayList<User>();*/
        launch(args);
    }
}
