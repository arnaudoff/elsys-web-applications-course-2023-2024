import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.BlockingQueue;

public class ChatServer {
    public static void main(String[] args) throws Exception {
        System.out.println("Hello, World!");

        try (ServerSocket serverSocket = new ServerSocket(1234)) {
            System.out.println("Server started. Waiting for clients to connect..."); 

            while (true) {
                Socket clientSocket = serverSocket.accept(); 
                System.out.println("Client connected!" + clientSocket); 

                ClientHandler clientHandler = new ClientHandler(clientSocket); 
                Thread thread = new Thread(clientHandler); 
                // messages implementation 
                thread.start(); 

                new Thread(() -> {
                    BlockingQueue<String> messageQueue = clientHandler.getMessageQueue(); 
                    while (true) {
                        try {
                            String message = messageQueue.take(); 
                            for (ClientHandler client : ClientHandler.clients) {
                                client.sendMessage(message);
                            }
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }).start(); 
            }
        }
    }
}