import java.net.ServerSocket;
import java.net.Socket;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MessengerServer extends Thread{
    protected ServerSocket server;
    protected CommandParser commandParser;
    List<MessengerClient> clients;

    public MessengerServer(ServerSocket serverSocket) throws Exception{
        if(serverSocket == null){
            throw new RuntimeException("<!!!\nGiven serverSocket object is null\n!!!>\n");
        }

        server = serverSocket;
        clients = new ArrayList<>();
        commandParser = new CommandParser();

        this.start();
    }

    public void run(){
        Scanner input = new Scanner(System.in);
        String data;

        while(true){
            // Receive input from server terminal
            data = input.nextLine();
            if(data == null){
                continue;
            }

            // Parse input and do according action
            switch(commandParser.parseCommand(data)){
                case CMD_SHUTDOWN:
                    closeConnections();
                    deleteClients();
                    System.exit(0);
                    break;
                case CMD_BAN_CLIENT:
                    String clientName = data.split(" ")[1];

                    if(clientName != null){
                        MessengerClient client = findClientByName(clientName);

                        if(client == null){
                            System.out.println("<!!!\nNo client found with name \"" + clientName + "\"\n!!!>\n");
                        }else{
                            try{
                                client.closeConnection();
                                clients.remove(client);
                                System.out.println("Client with name \"" + clientName + "\" was banned\n");
                            }catch(Exception e){
                                System.out.println(e);
                            }
                        }
                    }

                    break;
                default:
            }
        }
    }

    // Method for adding client connection to server
    public void addClient(Socket client) throws Exception{
        clients.add(new MessengerClient(client, this));
        System.out.println("New client connected (" + client.getInetAddress() + ")\n");
    }

    // Method for sending message to a given client
    public void sendMessageFromClient(String message, MessengerClient messengerClient){
        LocalDateTime timestamp = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("hh:mm am");
        String formattedTimestamp = timestamp.format(formatter);

        if(messengerClient == null || message == null){
            return;
        }

        System.out.println("[" + formattedTimestamp + "] " + messengerClient.getClientName() + "(" + messengerClient.getClientAddress() + "): " + message);

        for(int i = 0; i < clients.size(); i++){
            MessengerClient client = clients.get(i);

            if(!messengerClient.equals(client)){
                client.sendMessageToClient("[" + formattedTimestamp + "] " + messengerClient.getClientName() + "(" + messengerClient.getClientAddress() + "): " + message);
            }
        }
    }

    // Method for getting Client object by client name
    public MessengerClient findClientByName(String clientName){
        if(clientName == null){
            return null;
        }

        for(int i = 0; i < clients.size(); i++){
            String username = clients.get(i).getClientName();

            if(username != null && username.equals(clientName)){
                return clients.get(i);
            }
        }

        return null;
    }

    // Method for checking if given nickname is not used by another client
    public boolean clientNameAvailable(String clientName){
        for(int i = 0; i < clients.size(); i++){
            String username = clients.get(i).getClientName();

            if(username != null && username.equals(clientName)){
                return false;
            }
        }

        return true;
    }

    // Close client connections
    public void closeConnections(){
        clients.forEach(client -> {
            try{
                client.closeConnection();
            }catch(Exception e){
                System.out.println(e);
            }
        });
    }

    // Remove given client from server
    public void removeClient(MessengerClient client){
        clients.remove(client);
    }

    // Delete client connections
    public void deleteClients(){
        clients.forEach(client -> {
            clients.remove(client);
        });
    }
}
