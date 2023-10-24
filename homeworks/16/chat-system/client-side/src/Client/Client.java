package Client;

import Command.CommandType;

import java.io.*;
import java.net.Socket;

public class Client {
    private Socket socket;
    private BufferedReader reader;
    private BufferedWriter writer;
    private String username;

    public Client() {
    }

    public boolean isConnected() {
        return socket != null && !socket.isClosed();
    }

    public void connect(String host, int port) throws IOException {
        if (isConnected()) {
            throw new RuntimeException("Already connected to a server.");
        }

        try {
            this.socket = new Socket(host, port);
            this.reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            this.writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));

            listenForServerMessages();

            System.out.println("Connected to " + host + ":" + port + ".");
            System.out.println("Type " + CommandType.NICK + " <username> to set your username.");
        } catch (IOException e) {
            cleanResources();
            throw e;
        }
    }

    public void setUsername(String username) {
        if (!isConnected()) {
            throw new RuntimeException("Please connect to a server first.");
        }

        if (this.username != null) {
            throw new RuntimeException("Username already set.");
        }

        this.username = username;
        sendMessage(username);

        System.out.println("Username set to " + username + ".");
    }

    public void sendMessage(String message) {
        if (!isConnected()) {
            throw new RuntimeException("Please connect to a server first.");
        }

        if (username == null) {
            throw new RuntimeException("Please set a username first.");
        }

        try {
            writer.write(message);
            writer.newLine();
            writer.flush();
        } catch (IOException e) {
            cleanResources();
        }
    }

    private void listenForServerMessages() {
        new Thread(() -> {
            try {
                String message;

                while ((message = reader.readLine()) != null) {
                    System.out.println(message);
                    System.out.print("> ");
                }
            } catch (IOException ignored) {
            } finally {
                if (isConnected()) {
                    cleanResources();
                }
            }
        }).start();
    }

    public void quit() {
        if (!isConnected()) {
            throw new RuntimeException("Please connect to a server first.");
        }

        cleanResources();
    }

    public void cleanResources() {
        try {
            if (socket != null) {
                socket.close();
                System.out.println("Disconnected from server.");
            }

            if (reader != null) {
                reader.close();
            }

            if (writer != null) {
                writer.close();
            }

            username = null;
        } catch (IOException e) {
            System.exit(1);
        }
    }
}
