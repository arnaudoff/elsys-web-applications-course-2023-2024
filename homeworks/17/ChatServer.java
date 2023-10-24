import java.io.*;
import java.net.*;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.*;

public class ChatServer {
    private static final int PORT = 8080;
    private static Map<String, ClientHandler> clients = new ConcurrentHashMap<>();

    public static void main(String[] args) {
        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            System.out.println("Server started...");
            while (true) {
                Socket clientSocket = serverSocket.accept();
                ClientHandler client = new ClientHandler(clientSocket);
                new Thread(client).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static void broadcast(String message, String excludeUser) {
        for (Map.Entry<String, ClientHandler> entry : clients.entrySet()) {
            if (!entry.getKey().equals(excludeUser)) {
                entry.getValue().sendMessage(message);
            }
        }
    }

    static class ClientHandler implements Runnable {
        private final Socket socket;
        private final DataInputStream in;
        private final DataOutputStream out;
        private String nickname;

        public ClientHandler(Socket socket) throws IOException {
            this.socket = socket;
            this.in = new DataInputStream(socket.getInputStream());
            this.out = new DataOutputStream(socket.getOutputStream());
        }

        public void sendMessage(String message) {
            try {
                out.writeUTF(message);
                out.flush();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void run() {
            try {
                sendMessage("Please set your nickname using /nick <nickname>");

                while (true) {
                    String input = in.readUTF();

                    if (nickname == null) {
                        if (input.startsWith("/nick ")) {
                            System.out.println("Received nickname command: " + input);
                            String proposedNickname = input.split(" ", 2)[1];
                            if (clients.containsKey(proposedNickname)) {
                                sendMessage("Nickname already taken. Choose another one.");
                                System.out.println("Nickname " + proposedNickname + " already exists.");
                                nickname = null;
                            } else {
                                nickname = proposedNickname;
                                clients.put(nickname, this);
                                sendMessage("Nickname set to " + nickname);
                                System.out.println("Nickname set to: " + nickname);
                            }
                        } else {
                            sendMessage("You must set a nickname first using /nick <nickname>");
                        }
                    }

                    if (input.startsWith("/msg ")) {
                        if (nickname == null) {
                            continue;
                        }
                        String message = input.split(" ", 2)[1];
                        System.out.println("Received message: " + message + " from " + nickname);
                        SimpleDateFormat sdf = new SimpleDateFormat("hh:mm a");
                        String timestamp = sdf.format(new Date());
                        String formattedMsg = "[" + timestamp + "] " + nickname + ": " + message;
                        broadcast(formattedMsg, nickname);
                    } else if (input.equals("/quit")) {
                        clients.remove(nickname);
                        sendMessage("Goodbye!");
                        socket.close();
                        break;
                    }
                }
            } catch (SocketException e) {
                System.out.println("Client (" + nickname + ") disconnected unexpectedly.");
                clients.remove(nickname);
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    if (socket != null && !socket.isClosed()) {
                        socket.close();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
