package listener;

import java.net.*;
import java.io.*;

public class Program {
    // the main entry point for this hot mess of an application
    public static void main(String[] args) {
        String host = "localhost";
        int portForClients = 8090;
        int portOfActualServer = 8080;

        try {
            // connect to the actual server
            Socket serverSocket = new Socket(host, portOfActualServer);
            ServerConnection serverConnection = new ServerConnection(serverSocket);
            Thread serverThread = new Thread(serverConnection);

            // listen for incoming connections and accept the first
            ServerSocket socketForClients = new ServerSocket(portForClients);
            Socket clientSocket = socketForClients.accept();
            ClientConnection clientConnection = new ClientConnection(clientSocket, serverConnection);
            Thread clientThread = new Thread(clientConnection);
            clientThread.start();
            serverThread.start();
        }
        catch (IOException e) {
            System.out.println("Well, something has gone wrong hasn't it");
            System.out.println(e.getMessage());
        }
    }
}
