package Server;

import ClientHandler.ClientHandler;
import ClientHandler.Client;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.HashSet;

public class Server {
    private final ServerSocket serverSocket;
    private final HashSet<ClientHandler> clientHandlers = new HashSet<>();

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
                ClientHandler clientHandler = new ClientHandler(this, socket);
                new Thread(clientHandler).start();
            }
        } catch (IOException e) {
            stop();
            throw new RuntimeException(e);
        }
    }

    public void addClientHandler(ClientHandler clientHandler) {
        synchronized (clientHandlers) {
            clientHandlers.add(clientHandler);
            System.out.println(clientHandler.getClient().name() + " has joined the chat.");

            Client client = clientHandler.getClient();
            broadcast("Server", client.name() + " has joined the chat.");
        }
    }

    public void removeClientHandler(ClientHandler clientHandler) {
        synchronized (clientHandlers) {
            if (clientHandlers.contains(clientHandler)) {
                clientHandlers.remove(clientHandler);
                System.out.println(clientHandler.getClient().name() + " has left the chat.");

                Client client = clientHandler.getClient();
                broadcast("Server", client.name() + " has left the chat.");
            }
        }
    }

    public void broadcast(String sender, String message) {
        synchronized (clientHandlers) {
            for (ClientHandler clientHandler : clientHandlers) {
                String formattedMessage = String.format("[%s] %s: %s", getFormattedTime(), sender, message);
                clientHandler.sendMessage(formattedMessage);
            }
        }
    }

    private String getFormattedTime() {
        LocalTime currentTime = LocalTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("h:mm a");
        return currentTime.format(formatter);
    }

    public void stop() {
        try {
            clientHandlers.forEach(ClientHandler::cleanResources);
            serverSocket.close();
            System.out.println("Server stopped.");
        } catch (IOException e) {
            System.exit(1);
        }
    }
}