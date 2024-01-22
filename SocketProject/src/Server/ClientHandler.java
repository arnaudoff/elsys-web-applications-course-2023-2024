package Server;

import Server.ChatServer;

import java.io.*;
import java.net.*;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

public class ClientHandler extends Thread {
    private Socket socket;
    private DataOutputStream out;
    private String clientName;
    private List<ClientHandler> clients;

    public ClientHandler(Socket socket, List<ClientHandler> clients) {
        this.socket = socket;
        this.clients = clients;
    }

    public String getTime() {
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat dateFormat = new SimpleDateFormat("h:mm a");
        return dateFormat.format(calendar.getTime());
    }

    @Override
    public void run() {
        try {
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new DataOutputStream(socket.getOutputStream());
            clientName = in.readLine();

            String clientMessage;
            while ((clientMessage = in.readLine()) != null) {
                if (clientMessage.startsWith("/nick ")) {
                    clientName = clientMessage.substring(6);
                } else if (clientMessage.startsWith("/msg ")) {
                    String message = clientMessage.substring(5);
                    ChatServer.broadcastMessage("[" + getTime() + "] " + clientName + ": " + message, this);
                } else if (clientMessage.startsWith("/quit")) {
                    System.out.println(clientName + " disconnected");
                    clients.remove(this);
                    ChatServer.broadcastMessage("[" + getTime() + "] " + clientName + " disconnected", this);
                    sendMessage("terminated");
                    break;
                } else if (clientMessage.startsWith("/terminate")) {
                    System.out.println(clientName + " disconnected");
                    break;
                }
            }
        } catch (Exception e) {
            System.out.println("Error handling client.");
        }
    }

    public void sendMessage(String message) throws IOException{
        out.writeBytes(message + "\n");
    }
}
