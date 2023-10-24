import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class TCPServer {
    private static List<Socket> connectedClients = new ArrayList<>();

    public static void main(String argv[]) throws Exception{
        ServerSocket welcomeSocket = new ServerSocket(5050);
        while(true){
            Socket clientSocket = welcomeSocket.accept();
            connectedClients.add(clientSocket);
            ClientHandler clientHandler = new ClientHandler(clientSocket, connectedClients);
            new Thread(clientHandler).start();
        }
    }
}
