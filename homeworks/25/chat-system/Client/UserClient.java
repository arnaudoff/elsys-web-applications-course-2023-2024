package Client;

import java.io.*;
import java.net.*;

public class UserClient {
    public static void main(String[] args) throws Exception {
        BufferedReader inFromUser = new BufferedReader(new InputStreamReader(System.in));

        System.out.println("Use /connect <host>:<port> to connect to a server.");

        Socket clientSocket = null;
        while (clientSocket == null) {
            String userInput = inFromUser.readLine();
            if (!userInput.startsWith("/connect ")) {
                System.out.println("Invalid command. Please use /connect <host>:<port>.");
            } else {
                userInput = userInput.substring(9);
                String[] hostAndPort = userInput.split(":");

                if (hostAndPort.length != 2) {
                    System.out.println("Invalid syntax. Please use /connect <host>:<port>.");
                } else {
                    try {
                        clientSocket = new Socket(hostAndPort[0], Integer.parseInt(hostAndPort[1]));
                    } catch(Exception e) {
                        System.out.println("Error connecting to server: Wrong host or port.");
                    }
                }
            }
        }

        Thread readMessagesFromUsers = getReaderThread(clientSocket);
        readMessagesFromUsers.start();

        System.out.println("Please use /nick <username> to set your username.");

        String username = inFromUser.readLine();
        while (!username.startsWith("/nick ")) {
            if (username.equals("/quit")) {
                System.out.println("Disconnected");
                clientSocket.close();
                return;
            }

            System.out.println("Invalid command. Please use /nick <username> before sending messages.");
            username = inFromUser.readLine();
        }

        System.out.println("Nickname set. You can now send messages. Use /msg <message> to send a message to all users.");

        DataOutputStream outToServer = new DataOutputStream(clientSocket.getOutputStream());
        outToServer.writeBytes(username + "\n");

        String userInput;
        while((userInput = inFromUser.readLine()) != null) {
            if (userInput.equals("/quit")) {
                outToServer.writeBytes(userInput + "\n");
                break;
            } else if (userInput.startsWith("/nick ")) {
                outToServer.writeBytes(userInput + "\n");
            } else if (userInput.startsWith("/msg ")) {
                outToServer.writeBytes(userInput.substring(5) + "\n");
            } else {
                System.out.println("Invalid command. Please use /msg <message> or /quit");
            }
        }

        System.out.println("Disconnected");
        clientSocket.close();

    }

    private static Thread getReaderThread(Socket clientSocket) throws IOException {
        BufferedReader inFromServer = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

        return new Thread(() -> {
            while (true) {
                try {
                    String message = inFromServer.readLine();
                    if (message == null) {
                        break;
                    }
                    System.out.println(message);
                } catch (IOException e) {
                    break;
                }
            }
        });
    }
}