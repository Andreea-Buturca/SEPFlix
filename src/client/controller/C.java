package client.controller;

import client.Main;
import com.google.gson.Gson;
import com.google.gson.internal.StringMap;
import javafx.event.ActionEvent;
import javafx.scene.control.TextField;
import javafx.scene.web.WebView;

import java.io.*;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.util.ArrayList;

/**
 * Created by Marek on 23-May-17.
 */
public class C {
    public WebView ytView;
    public TextField searchField;

    public void Play(ActionEvent actionEvent) throws IOException {
        Helper.addDataToRequest("Action", "GetTrailer");
        Helper.addDataToRequest("MovieName", searchField.getText());
        String id = getIDfromYT(URLEncoder.encode(searchField.getText(), "UTF-8"));
        ytView.getEngine().load(
                "https://www.youtube.com/embed/" + id
        );
        ytView.setPrefSize(600, 400);
        Main.stage.show();
    }

    private String getIDfromYT(String title) throws IOException {
        String json = readJsonFromUrl(
                "https://www.googleapis.com/youtube/v3/search?part=snippet&q=" + title + "+trailer&type=video&key=AIzaSyDuTI4P28XHLbygh3-50h5TIhPlt3ahAys");
        Gson gson = new Gson();
        StringMap<Object> results = gson.fromJson(json, StringMap.class);
        ArrayList<StringMap<Object>> items = (ArrayList<StringMap<Object>>) results.get("items");
        StringMap<Object> iditem = (StringMap<Object>) items.get(0).get("id");
        return (String) iditem.get("videoId");
    }

    private static String readAll(Reader rd) throws IOException {
        StringBuilder sb = new StringBuilder();
        int cp;
        while ((cp = rd.read()) != -1) {
            sb.append((char) cp);
        }
        return sb.toString();
    }

    public static String readJsonFromUrl(String url) throws IOException {
        InputStream is = new URL(url).openStream();
        try {
            BufferedReader rd = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));
            String jsonText = readAll(rd);
            return jsonText;
        } finally {
            is.close();
        }
    }


}
