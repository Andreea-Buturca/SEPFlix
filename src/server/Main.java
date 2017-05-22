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

public class Main extends Application {

    public static DatabaseConnection databaseConnection;
    public static ServerConnection serverConnection;
    public static ConnectionREST connectionREST;
    public static Gson gson;

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("./view/ServerHomeScreen.fxml"));
        primaryStage.setTitle("Server");
        primaryStage.setScene(new Scene(root, 1000, 600));
        primaryStage.show();
    }


    public static void main(String[] args) {
        gson = new Gson();
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

        launch(args);
    }
}
