package client;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ChatClient {
    public static void main(String[] args) {
        try (BufferedReader consoleIn = new BufferedReader(new InputStreamReader(System.in))) {
            helpMessage();

            String serverIp = null;
            int port = 0;
            String username = null;

            Socket socket = null;
            PrintWriter out = null;
            BufferedReader in;
            Thread Reader = null;

            while (true) {
                String input = consoleIn.readLine();
                if (input.startsWith("/connect ") && serverIp == null && port == 0) {
                    String[] tokens = input.split(" ");
                    if (tokens.length == 2) {
                        String[] address = tokens[1].split(":");
                        if (address.length == 2) {
                            serverIp = address[0];
                            port = Integer.parseInt(address[1]);
                            System.out.println("Connecting to " + serverIp + ":" + port + "...");
                        } else {
                            System.out.println("Invalid address.");
                        }
                    } else {
                        System.out.println("Invalid command.");
                    }
                } else if (input.startsWith("/connect ") && (serverIp != null || port != 0)) {
                    System.out.println("You are already connected to a server.");
                }

                if (input.startsWith("/quit") && out != null) {
                    System.out.println("Disconnecting from " + serverIp + ":" + port + "...");
                    serverIp = null;
                    port = 0;
                    out.println(input);
                    socket.close();
                    socket = null;
                    out = null;
                    Reader.interrupt();
                    Reader = null;
                } else if (input.startsWith("/quit") && out == null) {
                    System.out.println("You are not connected to a server.");
                }

                if (input.startsWith("/nick ") && out != null) {
                    username = input.substring(6);
                    out.println(input);
                } else if (input.startsWith("/nick ") && out == null) {
                    System.out.println("You are not connected to a server.");
                }

                if (input.startsWith("/msg ") && out != null) {
                    out.println(input);
                } else if (input.startsWith("/msg ") && out == null) {
                    System.out.println("You are not connected to a server.");
                }

                if (serverIp != null && port != 0) {
                    try {
                        if (socket == null) {
                            socket = new Socket(serverIp, port);
                            out = new PrintWriter(socket.getOutputStream(), true);
                            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                            Reader = new ServerReader(in);
                            Reader.start();

                            if (username != null) {
                                out.println("/nick " + username);
                            } else {
                                System.out.println("Enter your username using /nick <username>.");
                            }
                        }
                    } catch (Exception e) {
                        System.out.println("Could not connect to " + serverIp + ":" + port + ".");
                        socket = null;
                        out = null;
                    }
                } else {
                    socket = null;
                    out = null;
                }
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private static class ServerReader extends Thread {
        private final BufferedReader in;

        public ServerReader(BufferedReader in) {
            this.in = in;
        }

        public void run() {
            try {
                String message;
                while ((message = in.readLine()) != null) {
                    System.out.println(message);
                }
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }

    private static void helpMessage() {
        System.out.println("Welcome to the chat client!");
        System.out.println("To connect to the server, use the following command:");
        System.out.println("/connect <server ip>:<server port>");
        System.out.println("To disconnect from the server, use the following command:");
        System.out.println("/quit");
        System.out.println("To send a message to the server, use the following command:");
        System.out.println("/msg <message>");
        System.out.println("To set your username, use the following command:");
        System.out.println("/nick <username>");
    }
}
