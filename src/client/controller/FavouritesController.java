package client.controller;

import client.Main;
import com.google.gson.internal.StringMap;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuBar;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import server.mediator.Facade;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

/**
 * Created by Marek on 22-May-17.
 */
public class FavouritesController implements Initializable {
    public Label usernameLabel;
    public Button removeButton;
    public ListView favouritesListView;
    private ArrayList<StringMap<Object>> favourites;
    private Thread controllerThread;

    public FavouritesController() {
        Main.facouritesC = this;
        controllerThread = Thread.currentThread();
    }

    @Override
    public synchronized void initialize(URL location, ResourceBundle resources) {
        Helper.addDataToRequest("Action", "FavouriteMovies");
        Helper.addDataToRequest("Username", Facade.getUsername(Main.loggedUser));
        Helper.sendRequest();
        boolean interupted = false;
        try {
            wait(15000);
        } catch (InterruptedException e) {
            interupted = true;
        }
        if (interupted) {
            ObservableList<FavouriteMovie> movies = FXCollections.observableArrayList();
            for (StringMap<Object> info : favourites) {
                movies.add(new FavouriteMovie(info));
            }
            favouritesListView.setItems(movies);
        }else Helper.alertdisplay("Timeout Error", "Server is not responding");
    }

    public void interupt(ArrayList<StringMap<Object>> favourites){
        this.favourites = favourites;
        controllerThread.interrupt();
    }

    public void removeFromFavourites(ActionEvent actionEvent) throws IOException {
        ObservableList<FavouriteMovie> movies = favouritesListView.getSelectionModel().getSelectedItems();
        for (FavouriteMovie m : movies) {
            StringMap<Object> movie = m.getInfo();
            Helper.addDataToRequest("Action", "RemoveFavouriteMovie");
            Helper.addDataToRequest("id", movie.get("id"));
            Helper.addDataToRequest("Username", Facade.getUsername(Main.loggedUser));
            Helper.sendRequest();
            Facade.removeFromFavourites(Main.loggedUser, (Double) movie.get("id"));
        }
        Helper.successdisplay("Removed", "Selected movies have been deleted from your favourites");
        //Main.stage.show();
        /*if (!Facade.hasFavourites(Main.loggedUser)) {
            BorderPane root = new BorderPane();
            URL menuBarUrl = getClass().getResource("../view/menubarLogged.fxml");
            MenuBar bar = FXMLLoader.load(menuBarUrl);
            URL paneOneUrl = getClass().getResource("../view/listOfMovies.fxml");
            AnchorPane paneOne = FXMLLoader.load(paneOneUrl);
            root.setTop(bar);
            root.setCenter(paneOne);
            Scene scene = new Scene(root);
            Main.stage.setScene(scene);
            Main.stage.show();
        }*/
    }

    /**
     * Created by Marek on 23-May-17.
     */
    public class FavouriteMovie {

        private StringMap<Object> info;

        public FavouriteMovie(StringMap<Object> info) {
            this.info = info;
        }

        public StringMap<Object> getInfo() {
            return info;
        }

        @Override
        public String toString() {
            String resullt = "";
            resullt += (String) info.get("title") + ", ";

            String year = (String) info.get("release_date");
            String[] token = year.split("-");
            year = token[0];
            resullt += year;
            return resullt;
        }
    }
}
