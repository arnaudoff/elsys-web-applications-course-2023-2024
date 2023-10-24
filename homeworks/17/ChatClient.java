import java.io.*;
import java.net.*;

public class ChatClient {
    private static volatile boolean connected = false;
    private static Socket socket;
    private static DataInputStream in;
    private static DataOutputStream out;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        while (true) {
            String command = br.readLine();

            if (command.startsWith("/connect ")) {
                String[] parts = command.split(" ");
                if (parts.length != 2) {
                    System.out.println("Invalid command. Use: /connect <ip>:<port>");
                    continue;
                }
                String[] addressParts = parts[1].split(":");
                if (addressParts.length != 2) {
                    System.out.println("Invalid address. Use: <ip>:<port>");
                    continue;
                }

                String ip = addressParts[0];
                int port = Integer.parseInt(addressParts[1]);

                connectToServer(ip, port);

                Thread readThread = new Thread(() -> {
                    try {
                        while (connected) {
                            String message = in.readUTF();
                            System.out.println(message);
                        }
                    } catch (SocketException e) {
                        System.out.println("Connection lost or closed by server.");
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                });
                readThread.start();
            } else {
                if (connected) {
                    synchronized (socket) {
                        out.writeUTF(command);
                        out.flush();
                        if ("/quit".equals(command)) {
                            connected = false;
                            socket.close();
                            break;
                        }
                    }
                } else {
                    System.out.println("Not connected. Use /connect <ip>:<port> to connect.");
                }
            }
        }
    }

    private static void connectToServer(String ip, int port) {
        try {
            socket = new Socket(ip, port);
            in = new DataInputStream(socket.getInputStream());
            out = new DataOutputStream(socket.getOutputStream());
            connected = true;
            System.out.println("Connected to the server at " + ip + ":" + port);
        } catch (IOException e) {
            connected = false;
            System.out.println("Failed to connect to server: " + e.getMessage());
        }
    }
}

