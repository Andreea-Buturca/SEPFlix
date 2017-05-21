package client.controller;

import javafx.event.ActionEvent;
import javafx.scene.control.Label;
import javafx.scene.input.ContextMenuEvent;

/**
 * Created by Marek on 21-May-17.
 */
public class TileController {

    public Label idLabel;


    public void showMovieInfo(ActionEvent actionEvent) {
        Helper.addDataToRequest("Action", "MovieDetail");
        System.out.println(idLabel.getText());
        Double id = Double.parseDouble(idLabel.getText());
        System.out.println(id);
        Helper.addDataToRequest("id", id);
        Helper.sendRequest();


    }
}
