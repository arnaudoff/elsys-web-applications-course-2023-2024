import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {
    public static void main(String[] args) {
        String serverIP = null;
        int serverPort = 0;

        BufferedReader userInput = new BufferedReader(new InputStreamReader(System.in));

        try {
            System.out.print("Enter the server IP: ");
            serverIP = userInput.readLine();

            System.out.print("Enter the server port: ");
            serverPort = Integer.parseInt(userInput.readLine());
        } catch (IOException | NumberFormatException e) {
            System.err.println("Invalid input.");
            System.exit(1);
        }

        try {
            Socket socket = new Socket(serverIP, serverPort);
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            String nickname = "User";
            Client client = new Client(serverIP, serverPort);
            client.start();
            String input;
            ExecutorService executorService = Executors.newSingleThreadExecutor();

            MessageSender messageSender = new MessageSender(out);

            MessageReceiver messageReceiver = new MessageReceiver(in);
            Thread receiverThread = new Thread(messageReceiver);
            receiverThread.start();

            while (true) {
                System.out.print("Write a command: ");
                input = userInput.readLine();
                if (input.equalsIgnoreCase("/quit")) {
                    messageSender.sendMessage("User has left the chat.");

                    try {
                        executorService.shutdown();
                        socket.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    System.out.println("Goodbye!");
                    System.exit(0);

                    break;
                } else if (input.startsWith("/nick ")) {
                    String newNickname = input.substring(6);
                    String message = "User has changed their nickname to " + newNickname;
                    messageSender.sendMessage(message);
                } else {
                    String formattedMessage = "[" + nickname + "] " + input;
                    messageSender.sendMessage(formattedMessage);
                }
            }
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}