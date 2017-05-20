package server.controller.connectionREST;

import com.google.gson.Gson;
import com.google.gson.internal.StringMap;

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


    public ArrayList<StringMap<Object>> getLatestMovies(String urlAddress) {
        String output = this.getRequest(urlAddress, "");
        Gson gson = new Gson();
        StringMap<Object> test = gson.fromJson(output, StringMap.class);
        //ArrayList<HashMap<String, Object>> data = gson.fromJson((String) test.get("results"), ArrayList.class);
        System.out.println(test.toString());
        ArrayList<Object> test2 = (ArrayList) test.get("results");
        System.out.println(test2.get(1));
        StringMap<Object> test3 = (StringMap<Object>) test2.get(1);
        System.out.println(test2.get(1));
        System.out.println(test2.get(1));
        System.out.println(test3.get("poster_path"));
        //System.out.println(data.get(1).get("vote_average"));
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

