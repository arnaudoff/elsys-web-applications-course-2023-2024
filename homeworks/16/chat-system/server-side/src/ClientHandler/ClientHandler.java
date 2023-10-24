package ClientHandler;

import Server.Server;

import java.io.*;
import java.net.Socket;
import java.util.Objects;

public class ClientHandler implements Runnable {
    private Server server;
    private Socket socket;
    private BufferedReader reader;
    private BufferedWriter writer;
    private Client client;

    public ClientHandler(Server server, Socket socket) {
        try {
            this.server = server;

            this.socket = socket;
            this.reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            this.writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));

            String username = reader.readLine();
            this.client = new Client(Objects.requireNonNullElse(username, "Anonymous"));

            server.addClientHandler(this);
        } catch (IOException e) {
            cleanResources();
        }
    }

    @Override
    public void run() {
        try {
            String message;

            while ((message = reader.readLine()) != null) {
                server.broadcast(client.name(), message);
            }
        } catch (IOException ignored) {
        } finally {
            cleanResources();
        }
    }

    public void sendMessage(String message) {
        try {
            writer.write(message);
            writer.newLine();
            writer.flush();
        } catch (IOException e) {
            cleanResources();
        }
    }

    public void cleanResources() {
        server.removeClientHandler(this);

        try {
            if (socket != null) {
                socket.close();
            }

            if (reader != null) {
                reader.close();
            }

            if (writer != null) {
                writer.close();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public Client getClient() {
        return client;
    }
}