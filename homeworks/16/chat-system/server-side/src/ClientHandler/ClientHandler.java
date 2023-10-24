package ClientHandler;

import java.io.*;
import java.net.Socket;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Objects;

public class ClientHandler implements Runnable {
    private static final ArrayList<ClientHandler> clientHandlers = new ArrayList<>();
    private Socket socket;
    private BufferedReader reader;
    private BufferedWriter writer;
    private Client client;

    public ClientHandler(Socket socket) {
        try {
            this.socket = socket;
            this.reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            this.writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));

            String username = reader.readLine();
            this.client = new Client(Objects.requireNonNullElse(username, "Anonymous"));

            synchronized (clientHandlers) {
                clientHandlers.add(this);
            }

            System.out.println(client.name() + " has joined the chat.");
            broadcast("Server", client.name() + " has joined the chat.");
        } catch (IOException e) {
            cleanResources();
        }
    }

    @Override
    public void run() {
        try {
            String message;

            while ((message = reader.readLine()) != null) {
                broadcast(client.name(), message);
            }
        } catch (IOException ignored) {
        } finally {
            cleanResources();
        }
    }

    private void broadcast(String sender, String message) {
        synchronized (clientHandlers) {
            for (ClientHandler clientHandler : clientHandlers) {
                try {
                    if (!clientHandler.client.name().equals(client.name())) {
                        String formattedMessage = String.format("[%s] %s: %s", getFormattedTime(), sender, message);

                        clientHandler.writer.write(formattedMessage);
                        clientHandler.writer.newLine();
                        clientHandler.writer.flush();
                    }
                } catch (IOException e) {
                    cleanResources();
                }
            }
        }
    }

    private String getFormattedTime() {
        LocalTime currentTime = LocalTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("h:mm a");
        return currentTime.format(formatter);
    }

    private void removeClientHandler() {
        synchronized (clientHandlers) {
            if (clientHandlers.contains(this)) {
                clientHandlers.remove(this);
                System.out.println(client.name() + " has left the chat.");
                broadcast("Server", client.name() + " has left the chat.");
            }
        }
    }

    private void cleanResources() {
        removeClientHandler();

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
}