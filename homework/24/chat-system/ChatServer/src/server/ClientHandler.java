package server;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.Set;

public class ClientHandler implements Runnable {
    private final Socket socket;
    private final Set<ClientHandler> connectedClients;
    private final DataOutputStream outputStream;

    public ClientHandler(Socket socket, Set<ClientHandler> connectedClients) {
        this.socket = socket;
        this.connectedClients = connectedClients;
        try {
            this.outputStream = new DataOutputStream(this.socket.getOutputStream());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void run() {
        BufferedReader bufferedReader;
        try {
            bufferedReader = new BufferedReader(new InputStreamReader(this.socket.getInputStream()));
            String message;

            while ((message = bufferedReader.readLine()) != null) {
                System.out.println(message);
                for (ClientHandler clientHandler : this.connectedClients) {
                    if (clientHandler == this)
                        continue;
                    synchronized (clientHandler.outputStream) {
                        clientHandler.outputStream.writeBytes(message + "\n");
                    }
                }
            }
        } catch (Exception e) {
            System.out.println("Error reading message: " + e.getMessage());
        }

        try {
            this.socket.close();
        } catch (Exception e) {
            System.out.println("Error closing socket: " + e.getMessage());
        }

        synchronized (this.connectedClients) {
            this.connectedClients.remove(this);
        }
    }
}
