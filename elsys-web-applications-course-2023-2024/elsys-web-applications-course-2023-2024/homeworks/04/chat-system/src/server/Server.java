import java.io.*;
import java.net.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Server {
    private static int PORT = 5000;
    private static String IP = "127.0.0.1";
    private static List<PrintWriter> clientWriters = new ArrayList<>();
    private static ServerSocket serverSocket;

    public static void main(String[] args) {
        startServer(IP, PORT);

        Thread commandThread = new Thread(Server::handleServerCommands);
        commandThread.start();

        while (true) {
            try {
                Socket clientSocket = serverSocket.accept();
                PrintWriter writer = new PrintWriter(clientSocket.getOutputStream());
                clientWriters.add(writer);

                Thread clientHandler = new Thread(new ClientHandler(clientSocket, writer));
                clientHandler.start();
                System.out.println("New client connected.");
            } catch (IOException e) {
                System.out.println("Connections terminated");
            }
        }
    }

    public static void startServer(String ip, int port) {
        IP = ip;
        PORT = port;

        try {
            serverSocket = new ServerSocket(PORT, 50, InetAddress.getByName(IP));
            System.out.println("Chat server is running on " + IP + ":" + PORT);
        } catch (IOException e) {

            System.out.println("Failed to start the server.");
        }
    }

    public static void changePort(int newPort) {
        try {
            serverSocket.close();
        } catch (IOException e) {
            System.out.println("Failed to close the server.");
        }
        startServer(IP, newPort);
    }

    public static void changeIP(String newIP) {
        try {
            serverSocket.close();
        } catch (IOException e) {
            System.out.println("Failed to close the server.");
        }
        startServer(newIP, PORT);
    }

    public static void closeServer() {
        try {
            if (serverSocket != null) {
                serverSocket.close();
            }
        } catch (IOException e) {
            System.out.println("Failed to close the server.");
        }
    }

    public static void handleServerCommands() {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.print("Server Command (/port, /ip, /close): ");
            String command = scanner.nextLine();

            if (command.startsWith("/port ")) {
                int newPort;
                try {
                    newPort = Integer.parseInt(command.substring(6));
                    changePort(newPort);
                } catch (NumberFormatException e) {
                    System.out.println("Invalid port number.");
                }
            } else if (command.startsWith("/ip ")) {
                String newIP = command.substring(4);
                changeIP(newIP);
            } else if (command.equals("/close")) {
                closeServer();
                System.exit(0);
            } else {
                System.out.println("Invalid command. Use /port, /ip, or /close.");
            }
        }
    }

    static synchronized void broadcastMessage(String message) {
        for (PrintWriter writer : clientWriters) {
            writer.println(message);
            writer.flush();
        }
    }

    public static void removeClient(PrintWriter writer) {
        clientWriters.remove(writer);
    }

}

class ClientHandler implements Runnable {
    private Socket clientSocket;
    private PrintWriter writer;
    private BufferedReader reader;

    public ClientHandler(Socket socket, PrintWriter writer) {
        this.clientSocket = socket;
        this.writer = writer;
    }

    @Override
    public void run() {
        String clientName = null;
        try {
            reader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

            while (true) {
                String message = reader.readLine();
                if (message == null) {
                    break;
                }

                if (message.startsWith("/nick")) {
                    clientName = message.substring(6);
                    Server.broadcastMessage(clientName + " joined the room.");
                } else if (message.startsWith("/quit")) {
                    Server.broadcastMessage(clientName + " left the room.");
                    break;
                } else {
                    Server.broadcastMessage(clientName + ": " + message);
                }
            }

        } catch (IOException e) {
            System.out.println("Failed to communicate with the client.");

        } finally {
            if (clientName != null) {
                Server.removeClient(writer);
            }
            try {
                clientSocket.close();
            } catch (IOException e) {

                System.out.println("Failed to close the client socket.");

            }
        }
    }

}
