/*
package server.controller.connectionREST;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;

*/
/**
 * Created by aykon on 10-May-17.
 *//*

public class ConnectionREST implements Runnable {

    private final String baseUrl = "https://api.themoviedb.org/3/";
    private final String apiKey = "?api_key=b97edb3572a6a9f660d0b90dc10453b6";

    public synchronized String getRequest(String urlAdress, String getParameters) {
        try {
            URL url = new URL(baseUrl + urlAdress + apiKey + getParameters);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Accept", "application/json");

            if (conn.getResponseCode() != 200) {
                throw new RuntimeException("Failed : HTTP error code : "
                        + conn.getResponseCode());
            }

            BufferedReader br = new BufferedReader(new InputStreamReader(
                    (conn.getInputStream())));

            String output = br.readLine();
            System.out.println("Output from Server .... \n");
            System.out.println(output);
            */
/*while ((output = br.readLine()) != null) {
                System.out.println(output);
            }*//*


            conn.disconnect();

        } catch (IOException e) {

            e.printStackTrace();

        }


        return "";
    }

    public String postRequest(String urlAdress, HashMap parameters) {
        try {
            URL url = new URL(baseUrl + urlAdress);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setDoOutput(true);
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/json");

            String input = new Gson().toJson(parameters);
            System.out.println(input);
            OutputStream os = conn.getOutputStream();
            os.write(input.getBytes());
            os.flush();

            if (conn.getResponseCode() != HttpURLConnection.HTTP_CREATED) {
                throw new RuntimeException("Failed : HTTP error code : "
                        + conn.getResponseCode());
            }

            BufferedReader br = new BufferedReader(new InputStreamReader(
                    (conn.getInputStream())));

            String output = br.readLine();
            System.out.println("Output from Server .... \n");
            System.out.println(output);
           */
/* while ((output = br.readLine()) != null) {
                System.out.println(output);
            }*//*


            conn.disconnect();

        } catch (IOException e) {

            e.printStackTrace();

        }
        return "Ahoj";
    }

    @Override
    public void run() {

    }
}
*/
