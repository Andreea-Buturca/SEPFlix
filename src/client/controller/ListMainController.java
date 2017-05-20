package client.controller;

import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

/**
 * Created by Marek on 20-May-17.
 */
public class ListMainController {
    public GridPane movieGrid;
    public Button searchButton;
    public TextField searchField;

    public void showSearchBar(ActionEvent actionEvent) {
        searchField.setVisible(true);
        searchButton.setVisible(false);
    }

    public void searchMovie(ActionEvent actionEvent) {
        System.out.println(searchField.getText());
        searchField.setVisible(false);
        searchButton.setVisible(true);
    }
}
