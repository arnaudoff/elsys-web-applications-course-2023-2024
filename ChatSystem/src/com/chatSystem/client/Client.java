package com.chatSystem.client;

import java.net.*;
import java.io.*;
import java.util.Scanner;

/**
 * This is the chat client program.
 * Type 'bye' to terminte the program.
 *
 * @author www.codejava.net
 */
public class Client {
    private String hostname;
    private int port;
    private String userName;

    public Client(String hostname, int port) {
        this.hostname = hostname;
        this.port = port;
    }

    public void execute() {
        try {
            Socket socket = new Socket(hostname, port);

            System.out.println("Connected to the chat server");

            new ReadThread(socket, this).start();
            new WriteThread(socket, this).start();

        } catch (UnknownHostException ex) {
            System.out.println("Server not found: " + ex.getMessage());
        } catch (IOException ex) {
            System.out.println("I/O Error: " + ex.getMessage());
        }

    }

    void setUserName(String userName) {
        this.userName = userName;
    }

    String getUserName() {
        return this.userName;
    }


    public static void main(String[] args) {
        String hostname = "localhost";
        int port = 8989;

        String text;
        String[] tokenizedText;
        Scanner console = new Scanner(System.in);

        while (true) {
            text = console.nextLine();
            tokenizedText = text.split(" ", 2);

            if (tokenizedText[0].equals("/connect")) {
                tokenizedText = tokenizedText[1].split(":", 2);
                hostname = tokenizedText[0];
                port = Integer.valueOf(tokenizedText[1]);
                break;
            }
        }

        Client client = new Client(hostname, port);
        client.execute();
    }
}