import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class ChatServer {
    private static final int PORT = 420;
    private static final Map<String, PrintWriter> clients = new HashMap<>();

    public static void main(String[] args) {
        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            System.out.println("Chat Server is running on port " + PORT);

            while (true) {
                Socket clientSocket = serverSocket.accept();
                new Thread(new ClientHandler(clientSocket)).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static class ClientHandler implements Runnable {
        private Socket clientSocket;
        private PrintWriter out;
        private Scanner in;
        private String nickname;

        public ClientHandler(Socket socket) {
            this.clientSocket = socket;
        }

        @Override
        public void run() {
            try {
                out = new PrintWriter(clientSocket.getOutputStream(), true);
                in = new Scanner(clientSocket.getInputStream());
                out.println("Connected to the chat server. Use /nick <nickname> to set your nickname.");

                while (true) {
                    if (in.hasNextLine()) {
                        String message = in.nextLine();
                        processMessage(message);
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                handleDisconnect();
            }
        }

        private void processMessage(String message) {
            if (message.startsWith("/nick")) {
                handleNickname(message);
            } else if (message.startsWith("/msg")) {
                broadcastMessage(message);
            } else if (message.startsWith("/quit")) {
                handleDisconnect();
            }
        }

        private void handleNickname(String message) {
            String[] parts = message.split(" ");
            if (parts.length == 2 && parts[0].equals("/nick")) {
                String newNickname = parts[1];
                if (!clients.containsKey(newNickname)) {
                    clients.remove(nickname);
                    nickname = newNickname;
                    clients.put(nickname, out);
                    out.println("Nickname set to " + nickname);
                    System.out.println("Nickname set to " + nickname);
                } else {
                    out.println("Nickname is already in use.");
                }
            } else {
                out.println("Invalid command for nickname.");
            }
        }

        private void broadcastMessage(String message) {
            String actualMessage = message.substring(5);
            Date date = new Date();
            System.out.println("[" + date + "]: " + nickname + ":" + actualMessage);
            for (PrintWriter clientWriter : clients.values()) {
                clientWriter.println("[" + date + "]: " + nickname + ":" + actualMessage);
            }
        }

        private void handleDisconnect() {
            clients.remove(nickname);
            try {
                clientSocket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            System.out.println(nickname + " has left the chat.");
        }
    }
}
