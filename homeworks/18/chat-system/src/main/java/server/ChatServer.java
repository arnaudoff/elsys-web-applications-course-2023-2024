package server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class ChatServer {
    private static final Map<Socket, PrintWriter> clientWriters = new ConcurrentHashMap<>();

    public static void main(String[] args) {
        if (args.length != 1) {
            System.out.println("Usage: java ChatServer <port>");
            System.exit(1);
        }

        int port = Integer.parseInt(args[0]);
        System.out.println("The chat server is running on port " + port + ".");
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            while (true) {
                Socket socket = serverSocket.accept();
                System.out.println("Accepted a connection from " + socket + ".");
                PrintWriter writer = new PrintWriter(socket.getOutputStream());
                clientWriters.put(socket, writer);
                new Thread(new ClientHandler(socket)).start();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    private static class ClientHandler extends Thread {
        private Socket socket;
        private PrintWriter output;
        private String username;

        public ClientHandler(Socket socket) {
            this.socket = socket;
        }

        public void run() {
            try {
                output = new PrintWriter(socket.getOutputStream(), true);
                BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));

                clientWriters.put(socket, output);

                String message;
                while ((message = input.readLine()) != null) {
                    if (message.startsWith("/quit")) {
                        break;
                    } else if (message.startsWith("/nick ")) {
                        String[] tokens = message.split(" ");
                        if (tokens.length >= 2) {
                            username = tokens[1];
                            broadcast(username + " has joined the chat.");
                        }
                    } else if (message.startsWith("/msg ")) {
                        String[] tokens = message.split(" ");
                        if (tokens.length >= 2) {
                            StringBuilder msg = new StringBuilder();
                            for (int i = 1; i < tokens.length; i++) {
                                msg.append(tokens[i]).append(" ");
                            }
                            broadcast("[" + java.time.LocalTime.now().toString().substring(0, 5) + "] " + username
                                    + ": " + msg);
                        }
                    }
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            } finally {
                if (username != null) {
                    broadcast(username + " has left the chat.");
                }
                clientWriters.remove(socket);
                try {
                    socket.close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                broadcast(username + " has left the chat.");
            }
        }
    }

    private static void broadcast(String message) {
        for (PrintWriter writer : clientWriters.values()) {
            writer.println(message);
        }
    }
}
