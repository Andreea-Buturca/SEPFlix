package server.controller.connectionDB;

/**
 * Created by martin on 17/05/2017.
 */
public class DBConnectionParameters {

    private static final String URL = "localhost:5432/";
    private static final String USER = "mydb";
    private static final String PASSWORD = "mydb";
    private static final String DATABASE = "mydb";

    public static String getURL() {
        return URL;
    }

    public static String getUSER() {
        return USER;
    }

    public static String getPASSWORD() {
        return PASSWORD;
    }

    public static String getDATABASE() {
        return DATABASE;
    }
}
