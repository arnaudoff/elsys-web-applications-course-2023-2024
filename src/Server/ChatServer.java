package Server;

import java.io.*;
import java.net.*;
import java.util.*;


public class ChatServer {
    private static List<ClientHandler> clients = new ArrayList<>();

    public static void main(String[] args) {
        int port = 5000; // Change to your desired port

        Thread consoleThread = null;

        try (ServerSocket serverSocket = new ServerSocket(port)) {
            System.out.println("Server is running on port " + port);

            consoleThread = new Thread(() -> {
                Scanner scanner = new Scanner(System.in);
                System.out.println("Entering inputs twice from now on will close the server");
                scanner.nextLine();
                System.out.println("Enter YES to close the server.");
                while(!scanner.nextLine().equals("YES")) {

                }
                System.out.println("Closing server...");
                try {
                    broadcastMessage("Server is closed.", null);
                    for(ClientHandler client : clients) {
                        client.join();
                    }
                    serverSocket.close();
                } catch (Exception e) {
                    System.out.println("Error closing server. This is bad. Please terminate manually (Ctr+C)");
                }
            });
            consoleThread.start();

            while (!serverSocket.isClosed()) {
                Socket clientSocket = serverSocket.accept();
                System.out.println("New user connected");
                ClientHandler client = new ClientHandler(clientSocket, clients);
                clients.add(client);
                client.start();
            }
        } catch (IOException e) {
            System.out.println("Server has been closed.");
            try {
                if (consoleThread != null)
                    consoleThread.join();

            } catch (InterruptedException interruptedException) {
                System.out.println("Error closing thread. Please terminate manually (Ctr+C)");
            }
        }
    }

    static void broadcastMessage(String message, ClientHandler sender) throws IOException {
        for (ClientHandler client : clients) {
            if (client != sender) {
                client.sendMessage(message);
            }
        }
    }
}