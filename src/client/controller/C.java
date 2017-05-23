package client.controller;
import client.Main;
import javafx.event.ActionEvent;
import javafx.scene.control.TextField;
import javafx.scene.web.WebView;
import java.io.IOException;

/**
 * Created by Marek on 23-May-17.
 */
public class C {
    public WebView ytView;
    public TextField searchField;

    public void Play(ActionEvent actionEvent) throws IOException {
        ytView.getEngine().load(
                "https://www.youtube.com/results?search_query="+searchField.getText()+" trailer"
        );
        ytView.setPrefSize(600, 400);
        Main.stage.show();
    }


}
