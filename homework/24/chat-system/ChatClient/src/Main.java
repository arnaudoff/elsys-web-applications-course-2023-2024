import client.Client;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Client client = new Client();

        menu();
        while (true) {
            String input = scanner.nextLine();
            String[] parts = input.split(" ", 2);

            switch (parts[0]) {
                case "/connect":
                    String[] hostAndPort = parts[1].split(":");
                    client.connect(hostAndPort[0], Integer.parseInt(hostAndPort[1]));
                    break;
                case "/username":
                    client.setUsername(parts[1]);
                    break;
                case "/msg":
                    if(!client.getMessageReaderThread().isAlive()) {
                        System.out.println("You are not connected to a server");
                        System.exit(0);
                    }
                    client.sendMessage(parts[1]);
                    break;
                case "/quit":
                    client.exit();
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid command");
                    break;
            }
        }
    }

    public static void menu() {
        System.out.println("This client supports the following commands:");
        System.out.println("/connect <host>:<port> - connects to a server");
        System.out.println("/username <username> - sets your username");
        System.out.println("/msg <message> - sends a message to the server");
        System.out.println("/quit - exits the program");
    }
}