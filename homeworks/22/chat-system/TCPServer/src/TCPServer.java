import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class TCPServer {
    public static void main(String[] args) {
        int port = 6789;
        try(ServerSocket welcomeSocket = new ServerSocket(port)) {
            System.out.println("Server is running on port " + port);

            while(!welcomeSocket.isClosed()) {
                Socket connectionSocket = welcomeSocket.accept();

                ClientHandler clientHandler = new ClientHandler(connectionSocket);
                Thread thread = new Thread(clientHandler);
                thread.start();

                System.out.println("Client connected!");
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}