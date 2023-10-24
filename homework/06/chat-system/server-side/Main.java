import java.net.ServerSocket;
import java.net.Socket;

public class Main{
    public static void main(String[] args){
        final int port = 8080;
        ServerSocket socketServer;
        MessengerServer server;

        try{
            // Create a socket server and a chat server
            socketServer = new ServerSocket(port);
            server = new MessengerServer(socketServer);

            while(!server.isInterrupted()){
                try {
                    // Accept new clients and add them to chat server
                    Socket client = socketServer.accept();
                    System.out.print("New client connected -> ");
                    server.addClient(client);
                } catch (Exception e) {
                    System.out.println(e);
                }
            }
        }catch(Exception e){
            System.out.println("<!!!\nCould not startup server -\\/\n" + e + "\n!!!>\n");
        }
    }
}