package Server;

import java.io.*;
import java.net.*;
import java.util.*;

public class Server {
    private static final List<Socket> clients = Collections.synchronizedList(new ArrayList<>());

    public static void main(String[] args) {
        try (ServerSocket serverSocket = new ServerSocket(8080)) {
            while (true) {
                Socket socket = serverSocket.accept();
                clients.add(socket);
                new Thread(new ClientHandler(socket)).start();
            }
        } catch (IOException e) {
            System.out.println("Error with server: " + e.getMessage());
        }
    }

    private static class ClientHandler implements Runnable {
        private final Socket socket;
        private String username;

        public ClientHandler(Socket socket) {
            this.socket = socket;
        }

        @Override
        public void run() {
            try {
                BufferedReader inFromUser = new BufferedReader(new InputStreamReader(socket.getInputStream()));

                username = inFromUser.readLine().substring(6);

                while(true) {
                    String message = inFromUser.readLine();
                    if (message.equals("/quit")) {
                        clients.remove(socket);
                        socket.close();
                        break;
                    }

                    if (message.startsWith("/nick ")) {
                        clients.remove(socket);
                        username = message.substring(6);
                        clients.add(socket);
                    } else {
                        broadcastMessage(message);
                    }
                }
            } catch (IOException e) {
                System.out.println("Error with client: " + e.getMessage());
            }
        }

        private void broadcastMessage(String message) {
            for (Socket clientSocket : clients) {
                try {
                    String time = new java.text.SimpleDateFormat("[HH:mm] ").format(new java.util.Date());

                    DataOutputStream outToUser = new DataOutputStream(clientSocket.getOutputStream());
                    outToUser.writeBytes(time + username + ": " + message + "\n");
                } catch (IOException e) {
                    System.out.println("Error with client: " + e.getMessage());
                }
            }
        }
    }
}