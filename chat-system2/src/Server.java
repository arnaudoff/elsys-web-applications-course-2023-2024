import java.io.*;
import java.net.*;
import java.util.*;

public class Server {
    private ServerSocket serverSocket;
    private List<Room> rooms;

    public Server(int port) {
        try {
            serverSocket = new ServerSocket(port);
            rooms = new ArrayList<>();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void acceptConnections() {
    }

    public static void main(String[] args) {
        int port = Integer.parseInt(args[0]);
        Server server = new Server(port);
        server.acceptConnections();
    }
}
