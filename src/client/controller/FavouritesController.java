package client.controller;

import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by Marek on 22-May-17.
 */
public class FavouritesController implements Initializable {
    public Label usernameLabel;
    public Button removeButton;
    public ListView favouritesListView;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void removeFromFavourites(ActionEvent actionEvent) {
    }


}
