package server.controller.connectionREST;

import com.google.gson.internal.StringMap;
import server.Main;
import server.model.Movie;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

/**
 * Created by aykon on 10-May-17.
 */
public class ConnectionREST {

    private final String baseUrl = "https://api.themoviedb.org/3/";
    private final String apiKey = "?api_key=b97edb3572a6a9f660d0b90dc10453b6";


    public ArrayList<StringMap<Object>> getLatestMovies() {
        String urlAddress = "movie/popular";
        String output = this.getRequest(urlAddress, "");
        StringMap<Object> response = Main.gson.fromJson(output, StringMap.class);
        ArrayList<StringMap<Object>> fullLatestMoviesList = (ArrayList) response.get("results");
        //cleaning unnecessary data
        ArrayList<StringMap<Object>> latestMoviesList = new ArrayList<>();
        for (StringMap<Object> movie : fullLatestMoviesList) {
            Movie movieObject = new Movie(movie);
            latestMoviesList.add(movieObject.toStringMap());
        }
        return latestMoviesList;
    }

    public Movie getMovie(int id) {
        String urlAddress = "movie/" + id;
        String output = this.getRequest(urlAddress, "");
        StringMap<Object> response = Main.gson.fromJson(output, StringMap.class);
        Movie movie = new Movie(response);
        return movie;
    }


    public ArrayList<StringMap<Object>> searchMovie(String s) {


        return new ArrayList<StringMap<Object>>();
    }

    private String getRequest(String urlAddress, String getParameters) {
        String output = null;
        try {
            URL url = new URL(baseUrl + urlAddress + apiKey + getParameters);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Accept", "application/json");

            if (conn.getResponseCode() != 200) {
                throw new RuntimeException("Failed : HTTP error code : "
                        + conn.getResponseCode());
            }

            BufferedReader br = new BufferedReader(new InputStreamReader(
                    (conn.getInputStream())));

            output = br.readLine();
            System.out.println("Output from external Server .... \n");
            System.out.println(output);

            conn.disconnect();

        } catch (IOException e) {

            e.printStackTrace();

        }
        return output;
    }
}

