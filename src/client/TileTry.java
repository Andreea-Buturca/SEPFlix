package client;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.TilePane;


import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;

/**
 * Created by Marek on 21-May-17.
 */
public class TileTry {
    public TilePane tilepane;
    public Button b;

    public void addtile(ActionEvent actionEvent) throws IOException {
        URL paneOneUrl = getClass().getResource("view/sampleanchor.fxml");
        AnchorPane paneOne = FXMLLoader.load(paneOneUrl);
        System.out.println(tilepane.getChildren());
        System.out.println(tilepane.getChildren().get(1));
        AnchorPane anchor = (AnchorPane)tilepane.getChildren().get(1);
        ImageView bb =new ImageView("https://amazingslider.com/wp-content/uploads/2012/12/dandelion.jpg");
        bb.setFitHeight(100);
        bb.setFitWidth(100);
        anchor.getChildren().add(bb);

        /*
        AnchorPane a =(AnchorPane) tilepane.getChildren().get(3);
        ImageView image = (ImageView) a.getChildren().get(0);
        Image im = new Image("view/logo.png");
        image.setImage(im);*/
    }
}
