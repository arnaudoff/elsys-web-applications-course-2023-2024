import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;


public class ClientHandler implements Runnable {
    static volatile List<ClientHandler> clients = new ArrayList<>();
    private String nickname;
    private final Socket clientSocket;
    private final BufferedReader in;
    private final PrintWriter out;
    private final BlockingQueue<String> messageQueue;

    public ClientHandler(Socket clientSocket) throws IOException {
        this.clientSocket = clientSocket;
        this.in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        this.out = new PrintWriter(clientSocket.getOutputStream(), true);
        this.messageQueue = new LinkedBlockingQueue<>();
    }

    private void broadcast(String message) {
        messageQueue.add(message);
    }

    @Override
    public void run() {
        try {
            out.println("Enter your nickname using /nick command: ");
            String inputLine;
            boolean connected = false; // To keep track of connection status
            while ((inputLine = in.readLine()) != null) {
                if (inputLine.startsWith("/nick ")) {
                    this.nickname = inputLine.substring(6).trim();
                    // out.println("Welcome, " + nickname + "!");
                    broadcast(nickname + " joined the chat!");
                    clients.add(this);
                } else if (inputLine.startsWith("/msg")) {
                    if (this.nickname == null) {
                        out.println("Please enter a nickname using /nick command: ");
                    } else {
                        String message = "[" + DateTimeFormatter.ofPattern("HH:mm") + "]: " + inputLine.substring(5).trim();
                        broadcast(message);
                    }
                } else if (inputLine.startsWith("/quit")) {
                    out.println("Bye, " + nickname + "!");
                    break;
                } else if (inputLine.startsWith("/connect")) {
                    if (!connected) {
                        String[] parts = inputLine.split(" ");
                        if (parts.length == 2) {
                            String[] address = parts[1].split(":");
                            if (address.length == 2) {
                                String ipAddress = address[0];
                                int port = Integer.parseInt(address[1]);
                                Socket newSocket = new Socket(ipAddress, port);
                                out.println("Connected to the server!");
                                out.flush(); // Ensure the message is sent immediately
                                // Start a new thread to handle the connection
                                ClientHandler newClientHandler = new ClientHandler(newSocket);
                                Thread newThread = new Thread(newClientHandler);
                                newThread.start();
                                connected = true;
                            } else {
                                out.println("Invalid command. Usage: /connect <ip>:<port>");
                            }
                        } else {
                            out.println("Invalid command. Usage: /connect <ip>:<port>");
                        }
                    } else {
                        out.println("Already connected to a server!");
                    }
                } else {
                    out.println("Unknown command: " + inputLine);
                }
            }
            clients.remove(this);
            clientSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public BlockingQueue<String> getMessageQueue() {
        return messageQueue;
    }

    public String getNickname() {
        for (ClientHandler client : clients) {
            if (client == this) {
                return client.nickname;
            }
        }
        return null;
    }

    public void sendMessage(String message) {
        out.println(message);
    }
}
