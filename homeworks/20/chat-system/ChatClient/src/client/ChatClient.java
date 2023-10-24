package client;

import java.io.*;
import java.net.*;

public class ChatClient {
    public static void main(String[] args) {

        BufferedReader consoleReader = new BufferedReader(new InputStreamReader(System.in));
        Socket socket = null;
        PrintWriter out = null;
        IncomingMessagesHandler incomingMessagesHandler = null;


        while (true) {
            String[] command;
            try {
                 command = consoleReader.readLine().split(" ");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            switch (command[0]) {
                case "/connect" -> {
                    String[] address = command[1].split(":");
                    if (address.length != 2) {
                        System.out.println("Invalid address");
                        break;
                    }
                    String serverAddress = address[0];
                    int serverPort = Integer.parseInt(address[1]);
                    try {
                        socket = new Socket(serverAddress, serverPort);
                        out = new PrintWriter(socket.getOutputStream(), true);
                        incomingMessagesHandler = new IncomingMessagesHandler(socket);
                        new Thread(incomingMessagesHandler).start();
                    } catch (IOException e) {
                        System.out.println("Can't connect to server");
                    }
                }
                case "/nick", "/msg" -> {
                    assert out != null;
                    out.println(String.join(" ", command));
                }
                case "/quit" -> {
                    assert out != null;
                    out.println(command[0]);
                    try {
                        socket.close();
                        assert incomingMessagesHandler != null;
                        incomingMessagesHandler.stop();
                        out.close();
                    } catch (IOException e) {
                        System.out.println("Can't close socket");
                    }
                }
                default -> {
                }
            }
        }
    }
}
