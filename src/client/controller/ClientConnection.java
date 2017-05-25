package client.controller;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

/**
 * Created by martin on 15/05/2017.
 */
public class ClientConnection {

    private static ClientConnection clientConnection;
    private final int PORT = 6666;
    private String host = "localhost";
    private ObjectInputStream inFromServer;
    private ObjectOutputStream outToServer;
    private Socket socket;
    private Thread clientReceiverThread;

    private ClientConnection() {
        try {
            socket = new Socket(host, PORT);
            outToServer = new ObjectOutputStream(socket.getOutputStream());
            inFromServer = new ObjectInputStream(socket.getInputStream());
            ClientReceiver clientReceiver = new ClientReceiver(inFromServer);
            clientReceiverThread = new Thread(clientReceiver, "Reciever");
            clientReceiverThread.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static ClientConnection getClientConnection() {
        if (clientConnection == null) {
            clientConnection = new ClientConnection();
        }
        return clientConnection;
    }

    public ClientConnection(String host) {
        this.host = host;
        try {
            socket = new Socket(host, PORT);
            outToServer = new ObjectOutputStream(socket.getOutputStream());
            inFromServer = new ObjectInputStream(socket.getInputStream());
            ClientReceiver clientReceiver = new ClientReceiver(inFromServer);
            new Thread(clientReceiver, "Reciever").start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Socket getSocket() {
        return socket;
    }

    public Thread getClientReceiverThread() {
        return clientReceiverThread;
    }

    public void sendSmtToServer(String json) {
        try {
            outToServer.writeObject(json);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
