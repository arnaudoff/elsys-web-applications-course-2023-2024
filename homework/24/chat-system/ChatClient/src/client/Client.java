package client;

import java.net.InetSocketAddress;
import java.net.Socket;

public class Client {
    private final Socket socket;
    private String username;
    private boolean connected;
    private Thread messageReaderThread;

    public Client() {
        this.socket = new Socket();
        this.username = "Anonymous";
        this.connected = false;
    }

    public void connect(String host, int port) {
        if (this.connected) {
            System.out.println("You are already connected to a server");
            return;
        }

        try {
            this.socket.connect(new InetSocketAddress(host, port));
            this.connected = true;
            System.out.println("Connected to server");

            MessageReader messageReader = new MessageReader(this.socket);
            this.messageReaderThread = new Thread(messageReader);
            this.messageReaderThread.start();
        } catch (Exception e) {
            System.out.println("Error connecting to server: " + e.getMessage());
        }

        directSend(username + " has connected\n");
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void sendMessage(String input) {
        if (!this.connected) {
            System.out.println("You are not connected to a server");
            return;
        }

        String message = "[" + java.time.LocalTime.now() + "] " + this.username + ": " + input + "\n";
        try {
            this.socket.getOutputStream().write(message.getBytes());
        } catch (Exception e) {
            System.out.println("Error sending message: " + e.getMessage());
        }
    }

    public void exit() {
        if (!this.connected) {
            System.out.println("You are not connected to a server");
            return;
        }

        try {
            directSend(username + " has disconnected\n");
            this.messageReaderThread.interrupt();
            this.socket.close();
            this.connected = false;
        } catch (Exception e) {
            System.out.println("Error disconnecting from server: " + e.getMessage());
        }
    }

    public void directSend(String message) {
        try {
            this.socket.getOutputStream().write(message.getBytes());
        } catch (Exception e) {
            System.out.println("Error sending message: " + e.getMessage());
        }
    }

    public Thread getMessageReaderThread() {
        return this.messageReaderThread;
    }
}
