import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class ClientHandler implements Runnable {
    private Socket clientSocket;
    private List<Socket> connectedClients = new ArrayList<>();

    public ClientHandler(Socket clientSocket, List<Socket> connectedClients) {
        this.clientSocket = clientSocket;
        this.connectedClients = connectedClients;
    }

    @Override
    public void run(){
        try {
            BufferedReader inFromClient = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            DataOutputStream outToClient = new DataOutputStream(clientSocket.getOutputStream());
            String message;

            while ((message = inFromClient.readLine()) != null) {
                if (message.equals("/quit")) {
                    removeClientFromServer();
                    break;
                }

                broadcast(message);
            }

        }catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void removeClientFromServer() {
        connectedClients.remove(clientSocket);
        System.out.println("connected clients: " + connectedClients);
    }

    public void broadcast(String message){
        System.out.println("Sending msg: " + message);
        for (Socket connectedClient : connectedClients) {
            if (connectedClient != clientSocket) {
                try {
                    DataOutputStream outToClient = new DataOutputStream(connectedClient.getOutputStream());
                    outToClient.writeBytes(message + '\n');
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }
}
