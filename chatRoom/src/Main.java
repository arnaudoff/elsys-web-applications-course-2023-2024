import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        /*try {
            ServerSocket socket = new ServerSocket(port);
            Server server = new Server(socket);
            server.startSocket();
        } catch (Exception e) {
            e.printStackTrace();

        }
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter your username: ");
        String username = scanner.nextLine();

        while (true) {
            String message = scanner.nextLine();

            if (message.equals("/quit")) {
                System.out.println("Ending program.");
                break;
            } else if (message.startsWith("/connect")) {
                String[] tokens = message.split(" ");
                if (tokens.length != 2) {
                    System.out.println("you have write in this format: /connect <ip of chat server>:<port>\n");
                    continue;
                }
                String[] HostPort = tokens[1].split(":");
                if (HostPort.length != 2) {
                    System.out.println("you have write in this format: /connect <ip of chat server>:<port>\n");
                    continue;
                }
                String host = HostPort[0];
                int port = Integer.parseInt(HostPort[1]);

                try {
                    ServerSocket serverSocket = new ServerSocket(port);
                    Server server = new Server(serverSocket);
                    server.startSocket();
                    Socket socket = new Socket(host, port);
                    Client client = new Client(socket, username);
                    client.listenForMessage();
                    client.sendMessage();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else {
                System.out.println("Error try again\n");
            }
        }*/
    }
}