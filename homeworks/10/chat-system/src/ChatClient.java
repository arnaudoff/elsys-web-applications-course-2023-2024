import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class ChatClient {
    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.println("Enter the server ip and port in this format: /connect ip:port");
            String input = scanner.nextLine();
            String[] inputArray = input.split(" ");

            if (inputArray.length == 2 && "/connect".equals(inputArray[0])) {
                System.out.println("Connecting to the server...");

                String[] ipAndPort = inputArray[1].split(":");
                String SERVER_IP = ipAndPort[0];
                int SERVER_PORT = Integer.parseInt(ipAndPort[1]);

                try (Socket socket = new Socket(SERVER_IP, SERVER_PORT);
                     PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
                     Scanner in = new Scanner(socket.getInputStream())) {

                    new Thread(() -> {
                        while (in.hasNextLine()) {
                            System.out.println(in.nextLine());
                        }
                    }).start();

                    while (true) {
                        String message = scanner.nextLine();
                        out.println(message);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else {
                System.out.println("Invalid input format. Please use: /connect ip:port");
            }
        }
    }
}
