package client.controller;

import client.Main;
import com.google.gson.internal.StringMap;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.ContextMenuEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.TilePane;


import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

/**
 * Created by Marek on 20-May-17.
 */
public class ListMainController implements Initializable {
    public Button searchButton;
    public TextField searchField;
    public TilePane tilepane;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Helper.addDataToRequest("Action", "LatestMovies");
        Helper.sendRequest();

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // TODO: 21-May-17 find better solution for this, same as in login

        addMovies(Main.latestMovies);
    }

    public ListMainController() {
        Main.listMain = this;
    }

    public void showSearchBar(ActionEvent actionEvent) {
        searchField.setVisible(true);
        searchButton.setVisible(false);

        Helper.addDataToRequest("Action", "LatestMovies");
        Helper.sendRequest();
    }

    public void searchMovie(ActionEvent actionEvent) {
        System.out.println(searchField.getText());
        searchField.setVisible(false);
        searchButton.setVisible(true);
    }

    public void addMovies(ArrayList<StringMap<Object>> latestMovies) {
        for (int i=0;i<latestMovies.size();i++){
            String title = (String) latestMovies.get(i).get("title");
            String year = (String) latestMovies.get(i).get("release_date");
            String[] token = year.split("-");
            year = token[0];
            String url = (String) latestMovies.get(i).get("poster_path");
            Double idnum = (Double) latestMovies.get(i).get("id");
            String id = Double.toString(idnum);
            try {
                addTile(title,year,url, id);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    public void addTile(String movieTitle, String prodYear, String url, String movieID) throws IOException {
        URL paneOneUrl = getClass().getResource("../view/tile.fxml");
        AnchorPane tile = FXMLLoader.load(paneOneUrl);

        ImageView imageview = (ImageView) tile.getChildren().get(0);
        Label title = (Label) tile.getChildren().get(1);
        Label year = (Label) tile.getChildren().get(2);
        Label id = (Label) tile.getChildren().get(3);

        // TODO: 21-May-17 Solve issue when title is too long
        title.setText(movieTitle);
        year.setText(prodYear);
        String imageURL = "https://image.tmdb.org/t/p/w320"+url;
        imageview.setImage(new Image(imageURL));
        id.setText(movieID);

        tilepane.getChildren().add(tile);
    }

}
