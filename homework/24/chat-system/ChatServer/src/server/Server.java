package server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashSet;
import java.util.Set;

public class Server {
    private final ServerSocket serverSocket;
    private final Set<ClientHandler> connectedClients;

    public Server(int port) {
        this.connectedClients = new HashSet<>();
        try {
            this.serverSocket = new ServerSocket(port);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void start() {
        while(true) {
            Socket socket;

            try {
                socket = this.serverSocket.accept();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            System.out.println("Client connected [" + java.time.LocalTime.now() + "]: " +
                    socket.getInetAddress().toString() + ":" + socket.getPort());

            ClientHandler clientHandler = new ClientHandler(socket, connectedClients);

            synchronized (this.connectedClients) {
                this.connectedClients.add(clientHandler);
            }
            new Thread(clientHandler).start();
        }
    }
}