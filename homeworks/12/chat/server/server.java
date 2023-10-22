import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.io.PrintStream;
// import java.lang.reflect.Array;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

// /connect localhost:9806

public class server {
    volatile static ArrayList<BufferedReader> inList = new ArrayList<BufferedReader>();
    volatile static ArrayList<PrintStream> outList = new ArrayList<PrintStream>();

    public static void main(String[] args) {
        try {
            System.out.println("Server started");
            try (ServerSocket serverSocket = new ServerSocket(9806)) {
                while (true) {
                    Socket clientSocket = serverSocket.accept();
                    System.out.println("New client connected: " + clientSocket.getInetAddress().getHostAddress());

                    inList.add(new BufferedReader(new InputStreamReader(clientSocket.getInputStream())));
                    outList.add(new PrintStream(clientSocket.getOutputStream()));

                    new Thread(() -> {
                        try {
                            while (true) {
                                BufferedReader in = inList.get(inList.size() - 1);

                                String clientMsg = in.readLine();
                                if (clientMsg == null) {
                                    break;
                                }

                                List<String> msg = new ArrayList<String>();
                                for (String retval : clientMsg.split(":", 2)) {
                                    msg.add(retval);
                                }

                                System.out.println("Received message from client " + msg.get(0) + "(" +clientSocket.getInetAddress().getHostAddress() + "): " + msg.get(1));
                                
                                for (PrintStream outStream : outList) {
                                    if(in != inList.get(outList.indexOf(outStream))){
                                        outStream.println(clientMsg);
                                    }
                                }
                            }

                            clientSocket.close();
                            System.out.println("Client disconnected: " + clientSocket.getInetAddress().getHostAddress());
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }).start();

                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}