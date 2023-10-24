import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Arrays;
import java.util.List;

public class MessengerClient extends Thread{
    protected Socket clientSocket;
    protected PrintWriter sender;
    protected BufferedReader receiver;
    protected String clientName;
    protected MessengerServer server;
    protected ClientInputParser inputParser;

    public MessengerClient(Socket client, MessengerServer messengerServer) throws Exception{
        if(client == null){
            throw new RuntimeException("<!!!\nGiven client object is null\n!!!>\n");
        }else if(!client.isConnected()){
            throw new RuntimeException("<!!!\nClient socket is not connected\n!!!>\n");
        }else if(messengerServer == null){
            throw new RuntimeException("<!!!\nGiven messengerServer object is null\n!!!>\n");
        }

        clientSocket = client;
        clientName = null;
        receiver = new BufferedReader(new InputStreamReader(client.getInputStream()));
        sender = new PrintWriter(client.getOutputStream());
        server = messengerServer;

        inputParser = new ClientInputParser();

        this.start();
    }

    public void run(){
        String input;

        while(true){
            try{
                List<String> substrings;

                // Check if connection is lost
                // If it is, remove client from server
                if(!clientSocket.isConnected()){
                    System.out.println("Client (" + clientName + " -> " + clientSocket.getInetAddress() + ") has disconnected\n");
                    server.removeClient(this);
                    this.interrupt();
                    break;
                }

                // Receive input from client
                input = receiver.readLine();
                if(input == null){
                    continue;
                }

                substrings = Arrays.stream(input.split(" ")).toList();

                // If there are less than two arguments send a no_acknowledgment response
                // and continue with next loop cycle
                if(substrings.size() < 2){
                    sender.println("/no_acknowledge (received: " + input + ")");
                    sender.flush();
                    continue;
                }

                // Classify input from client and do the according action
                switch (inputParser.parseClientInput(input)) {
                    // Code for processing nickname request
                    case NICKNAME_REQUEST -> {
                        if (server.clientNameAvailable(substrings.get(1))) {
                            System.out.println("(" + clientSocket.getInetAddress() + ") from now on will be called " + substrings.get(1) + "\n");
                            clientName = substrings.get(1);
                            sender.println("/nick ACKNOWLEDGE");
                            sender.flush();
                        } else {
                            sender.println("/nick NICKNAME_NOT_AVAILABLE");
                            sender.flush();
                        }
                    }
                    // Code for processing client request for quiting chat
                    case QUIT_CHAT -> {
                        System.out.println(clientName + " (" + clientSocket.getInetAddress() + ") has left the chat\n");
                        this.closeConnection();
                        server.removeClient(this);
                        this.interrupt();
                        return;
                    }
                    // Code for processing message request
                    case MESSAGE_RECEIVED -> {
                        sender.println("/msg_received_ack");
                        sender.flush();
                        server.sendMessageFromClient(input.substring(5), this);
                    }
                    default -> {
                        System.out.println("<!!!\nIncorrect syntax\n!!!>");
                        sender.println("/no_acknowledge");
                        sender.flush();
                    }
                }
            }catch(Exception e){
                if(e.getMessage() != null){
                    if(e.getMessage().equals("Connection reset") || e.getMessage().equals("Socket closed")){
                        System.out.println(clientName + " (" + clientSocket.getInetAddress() + ") has left the chat\n");
                        server.removeClient(this);

                        try{
                            this.closeConnection();
                        }catch(Exception ex){
                            System.out.println(ex);
                        }

                        this.interrupt();
                    }else{
                        System.out.println(e);
                    }
                }
            }
        }
    }

    // Method for sending message from server to client
    public void sendMessageToClient(String message){
        sender.println("/msg " + message);
        sender.flush();
    }

    // Close connection with client
    public void closeConnection() throws Exception{
        this.interrupt();
        clientSocket.close();
    }

    // Get client name
    public String getClientName(){
        return clientName;
    }

    // Get client IP address
    public InetAddress getClientAddress(){
        return clientSocket.getInetAddress();
    }
}
