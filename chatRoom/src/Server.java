import java.net.ServerSocket;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Server {
    private ServerSocket socket;
    private static int port = 8080;

    public Server(ServerSocket socket) {
        this.socket = socket;
    }

    public void startSocket(){
        try {
            while (!socket.isClosed()){
                Socket client = socket.accept();
                System.out.println("Client connected: " + client.getInetAddress().getHostAddress());
                ClientHandler handler = new ClientHandler(client);

                Thread thread = new Thread(handler);
                thread.start();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void closeSocket(){
        try {
            if(socket != null)
            socket.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //private static String getCurrentTime() {
    //    return new SimpleDateFormat("hh:mm a").format(new Date());
    //}


    public static void main(String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket(port);
            Server server = new Server(serverSocket);
            server.startSocket();
        } catch (Exception e) {
            e.printStackTrace();

        }
    }
}
