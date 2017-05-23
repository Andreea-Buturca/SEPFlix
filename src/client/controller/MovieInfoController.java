package client.controller;

import client.Main;
import com.google.gson.internal.StringMap;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Text;
import server.mediator.Facade;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by Marek on 20-May-17.
 */
public class MovieInfoController implements Initializable {

    public Label titleLabel;
    public ImageView moviePosterImageView;
    public Label yearLabel;
    public Label genreLabel;
    public Label imbdLabel;
    public Label sepflixLabel;
    public Button addToListButton;
    public Button rateButton;
    public Text overviewText;

    private Thread controllerThread;
    private StringMap<Object> movieInfo;

    public MovieInfoController() {
        Main.movieInfoC = this;
        controllerThread = Thread.currentThread();
    }

    @Override
    public synchronized void initialize(URL location, ResourceBundle resources) {
        boolean interupted = false;
        try {
            wait(15000);
        } catch (InterruptedException e) {
            interupted = true;
        }
        if (interupted){
            loadMovieInfo();
        }else Helper.alertdisplay("Timeout Error", "Server not responding");
    }

    private void loadMovieInfo() {
        System.out.println(movieInfo.get("id"));
        System.out.println(movieInfo.get("title"));
        System.out.println(movieInfo.get("overview"));
        System.out.println(movieInfo.get("release_date"));
        System.out.println(movieInfo.get("vote_average"));
        System.out.println(movieInfo.get("genres"));

        String posterpath = (String) movieInfo.get("poster_path");
        String imageURL = "https://image.tmdb.org/t/p/w320" + posterpath;
        moviePosterImageView.setImage(new Image(imageURL));

        titleLabel.setText((String) movieInfo.get("title"));
        String year = (String) movieInfo.get("release_date");
        String[] token = year.split("-");
        year = token[0];
        yearLabel.setText(year);
        genreLabel.setText((String) movieInfo.get("genres"));
        Double voteimbd = (Double) movieInfo.get("vote_average");
        imbdLabel.setText("Imbd: "+voteimbd.toString());
        overviewText.setText((String)movieInfo.get("overview"));
    }

    public void interupt(StringMap<Object> movieInfo) {
        this.movieInfo = movieInfo;
        controllerThread.interrupt();
    }

    public void addToMyList(ActionEvent actionEvent) throws IOException {
        if (Main.loggedUser==null) {
            BorderPane root = new BorderPane();
            URL menuBarUrl = getClass().getResource("../view/menubarGuest.fxml");
            MenuBar bar = FXMLLoader.load(menuBarUrl);
            URL paneOneUrl = getClass().getResource("../view/login.fxml");
            AnchorPane paneOne = FXMLLoader.load(paneOneUrl);
            root.setTop(bar);
            root.setCenter(paneOne);
            Scene scene = new Scene(root);
            Main.stage.setScene(scene);
            Main.stage.show();
        }else{
            Facade.addMovieToFavourites(Main.loggedUser, movieInfo);
            Helper.successdisplay("Added", "Movie was added to you favourites");
            Helper.addDataToRequest("Action", "AddFavouriteMovie");
            Helper.addDataToRequest("id", movieInfo.get("id"));
            Helper.addDataToRequest("Username", Facade.getUsername(Main.loggedUser));
            Helper.sendRequest();
        }
    }

    public void rate(ActionEvent actionEvent) {

    }


}
