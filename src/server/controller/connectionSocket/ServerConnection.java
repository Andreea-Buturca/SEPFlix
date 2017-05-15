package server.controller.connectionSocket;

import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by martin on 15/05/2017.
 */
public class ServerConnection implements Runnable {
    private static final int PORT = 6666;


    @Override
    public void run() {
        int count = 1;
        try {
            System.out.println("Starting Server...");

            ServerSocket welcomeSocket = new ServerSocket(PORT);
            while (true) {
                System.out.println("Waiting for a client...");
                Socket connectionSocket = welcomeSocket.accept();
                ServerCommunication serverCommunication = new ServerCommunication(connectionSocket);
                new Thread(serverCommunication, "Communication #" + count).start();
                count++;
            }
        } catch (Exception e) {
            System.out.println("Exception in connection to server: "
                    + e.getMessage());
            e.printStackTrace();
        }
    }
}
