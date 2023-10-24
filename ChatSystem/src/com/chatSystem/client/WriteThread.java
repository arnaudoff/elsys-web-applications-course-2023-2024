package com.chatSystem.client;

import java.io.*;
import java.net.*;
import java.util.Scanner;

/**
 * This thread is responsible for reading user's input and send it
 * to the server.
 * It runs in an infinite loop until the user types 'bye' to quit.
 *
 * @author www.codejava.net
 */
public class WriteThread extends Thread {
    private PrintWriter writer;
    private Socket socket;
    private Client client;

    public WriteThread(Socket socket, Client client) {
        this.socket = socket;
        this.client = client;

        try {
            OutputStream output = socket.getOutputStream();
            writer = new PrintWriter(output, true);
        } catch (IOException ex) {
            System.out.println("Error getting output stream: " + ex.getMessage());
            ex.printStackTrace();
        }
    }

    public void run() {

        Scanner console = new Scanner(System.in);

        while (true) {
            System.out.println("\nEnter your name with /nick <username>");
            String[] userName = console.nextLine().split(" ", 2);
            if(userName[0].equals("/nick")){
                client.setUserName(userName[1]);
                writer.println(userName[1]);
                break;
            }
        }

        String text;
        String[] tokenizedText;

        do {
            text = console.nextLine();
            tokenizedText = text.split(" ", 2);
            if(tokenizedText[0].equals("/msg")){
                writer.println(tokenizedText[1]);
            }


        } while (!text.equals("/quit"));

        try {
            socket.close();
        } catch (IOException ex) {

            System.out.println("Error writing to server: " + ex.getMessage());
        }
    }
}