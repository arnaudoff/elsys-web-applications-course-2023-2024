package com.chatSystem.client;

import java.util.Scanner;

public class Main {
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
