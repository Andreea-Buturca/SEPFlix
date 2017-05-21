package client.controller;

import javafx.scene.control.Label;
import javafx.scene.input.ContextMenuEvent;

/**
 * Created by Marek on 21-May-17.
 */
public class TileController {

    public void movieInfo(ContextMenuEvent contextMenuEvent) {
        System.out.println(contextMenuEvent.getSource().toString());
        Label label =(Label) contextMenuEvent.getSource();
        label.setText("clicked");
    }
}
