import java.io.*;
import java.net.*;
import java.util.Scanner;

public class Client {
    private static Socket socket;
    private static PrintWriter serverOut;
    private static BufferedReader serverIn;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean isConnected = false;

        while (true) {
            if (!isConnected) {
                System.out.print("Enter a command (/connect, /close): ");
            } else {
                System.out.print("Enter a command (/msg, /nick, /quit): ");
            }

            String command = scanner.nextLine();

            if (!isConnected) {
                if (command.startsWith("/connect ")) {
                    if (socket != null && !socket.isClosed()) {
                        System.out.println("Already connected to a server. Use /quit to disconnect.");
                    } else {
                        if (connectToServer(command.substring(9))) {
                            isConnected = true;
                        }
                    }
                } else if (command.equals("/close")) {
                    System.out.println("Client is closed.");
                    break;
                } else {
                    System.out.println("Invalid command. Use /connect or /close.");
                }
            } else {
                if (command.startsWith("/msg ")) {
                    sendMessage(command.substring(5));
                } else if (command.startsWith("/nick ")) {
                    setNickname(command.substring(6));
                } else if (command.equals("/quit")) {
                    if (disconnectFromServer()) {
                        isConnected = false;
                    }
                } else {
                    System.out.println("Invalid command. Use /msg, /nick, or /quit.");
                }
            }
        }
    }

    private static boolean connectToServer(String connectionInfo) {
        String[] parts = connectionInfo.split(":");
        if (parts.length == 2) {
            String serverIP = parts[0];
            int serverPort = Integer.parseInt(parts[1]);

            try {
                socket = new Socket(serverIP, serverPort);
                serverOut = new PrintWriter(socket.getOutputStream(), true);
                serverIn = new BufferedReader(new InputStreamReader(socket.getInputStream()));

                System.out.print("Enter your nickname: ");
                String nickname = new Scanner(System.in).nextLine();
                serverOut.println("/nick " + nickname);

                Thread messageReceiver = new Thread(() -> {
                    try {
                        String serverResponse;
                        while ((serverResponse = serverIn.readLine()) != null) {
                            System.out.println(serverResponse);
                        }
                    } catch (IOException e) {
                        System.out.println("Connection terminated");
                    }
                });
                messageReceiver.start();
                System.out.println("Connected to the server.");
                return true;
            } catch (IOException e) {
                System.out.println("Failed to connect to the server.");
                return false;
            }
        } else {
            System.out.println("Invalid connection format. Use /connect IP:Port.");
            return false;
        }
    }

    private static void sendMessage(String message) {
        serverOut.println(message);
    }

    private static void setNickname(String newNickname) {
        serverOut.println("/nick " + newNickname);
    }

    private static boolean disconnectFromServer() {
        try {
            serverOut.println("/quit");
            closeSocket();
            System.out.println("Disconnected from the server.");
            return true;
        } catch (Exception e) {
            System.out.println("Failed to disconnect from the server.");
            return false;
        }
    }

    private static void closeSocket() {
        try {
            socket.close();
            socket = null;
            serverOut = null;
            serverIn = null;
        } catch (IOException e) {
            System.out.println("Failed to close the socket.");
        }
    }
}
