package client;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Objects;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("Hello World");
        primaryStage.setScene(new Scene(root, 300, 275));
        primaryStage.show();


    }

    /*@Override
        public void start(Stage primaryStage) throws IOException {

            // loading FXML resources
            // note that we don't need PaneTwo in this class

            URL menuBarUrl = getClass().getResource("menubar.fxml");
            MenuBar bar = FXMLLoader.load( menuBarUrl );

            URL paneOneUrl = getClass().getResource("paneone.fxml");
            AnchorPane paneOne = FXMLLoader.load( paneOneUrl );


            // constructing our scene using the static root

            root.setTop(bar);
            root.setCenter(paneOne);

            Scene scene = new Scene(root, 350, 480);

            primaryStage.setScene(scene);
            primaryStage.show();

        }*/


    public static void main(String[] args) {
        //launch(args);
        String salt = "moj tajny kod";
        String pass = "Marek";
        String pass2 = "Marek";


        String hash = get_SHA_512_SecurePassword(pass, salt);
        System.out.println(hash);
        System.out.println(get_SHA_512_SecurePassword(pass2, salt));

        if (Objects.equals(hash, get_SHA_512_SecurePassword(pass2, salt))) {
            System.out.println("equal");
        } else {
            System.out.println("wrong pass");
        }
        salt = null;
        pass = null;
        pass2 = null;
        System.gc();

    }

    public static String get_SHA_512_SecurePassword(String passwordToHash, String salt) {
        String generatedPassword = null;
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-512");
            md.update(salt.getBytes("UTF-8"));
            byte[] bytes = md.digest(passwordToHash.getBytes("UTF-8"));
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < bytes.length; i++) {
                sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
            }
            generatedPassword = sb.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return generatedPassword;
    }
}
