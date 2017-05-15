package server.controller.connectionSocket;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

/**
 * Created by martin on 15/05/2017.
 */
public class ServerCommunication implements Runnable {
    private ObjectInputStream inFromClient;
    private ObjectOutputStream outToClient;

    public ServerCommunication(Socket clientSocket) throws IOException {
        outToClient = new ObjectOutputStream(clientSocket.getOutputStream());
        inFromClient = new ObjectInputStream(clientSocket.getInputStream());
    }

    public void run() {
        //actions here
    }
}
