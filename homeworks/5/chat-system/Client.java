import java.io.*;
import java.net.*;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) {
        String serverAddress = "127.0.0.1";
        int serverPort = 80;

        try {
            Socket socket = new Socket(serverAddress, serverPort);
            System.out.println("Connected to the chat server.");

            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);

            Scanner scanner = new Scanner(System.in);

            String nickname = scanner.nextLine();
            out.println("/nick " + nickname);

            while (true) {
                String message = scanner.nextLine();
                if (message.equals("/quit")) {
                    out.println(message);
                    break;
                }
                out.println("/msg " + message);
            }

            socket.close();
            System.out.println("Disconnected from the chat server.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
