package Server;

import ClientHandler.ClientHandler;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    private final ServerSocket serverSocket;

    public Server(int port) {
        try {
            serverSocket = new ServerSocket(port);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void start() {
        System.out.println("Server started on port " + serverSocket.getLocalPort() + ".");

        try {
            while (!serverSocket.isClosed()) {
                Socket socket = serverSocket.accept();
                ClientHandler clientHandler = new ClientHandler(socket);
                new Thread(clientHandler).start();
            }
        } catch (Exception e) {
            stop();
            throw new RuntimeException(e);
        }
    }

    public void stop() {
        try {
            serverSocket.close();
            System.out.println("Server stopped.");
        } catch (IOException e) {
            System.exit(1);
        }
    }
}