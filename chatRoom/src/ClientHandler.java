import java.io.*;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class ClientHandler implements Runnable{

    public static ArrayList<ClientHandler> clients = new ArrayList<>();
    private Socket socket;
    private BufferedReader reader;
    private BufferedWriter writer;
    private String username;


    public ClientHandler(Socket socket) {
        try{
            this.socket = socket;

            this.reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            this.writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            this.username = reader.readLine();
            clients.add(this);
            broadcastMassage("Server " + username + " has joined the chat");
        } catch (Exception e){
            closeEverything(socket, reader, writer);
        }
    }

    @Override
    public void run() {
        String message;

        while (socket.isConnected()){
            try {
                message = reader.readLine();
                if(message.startsWith("/quit")){
                    broadcastMassage("Server" + username + " has left the chat");
                    disconnect();
                    break;
                }
                else if(message.startsWith("/msg")) {
                    String[] splitMessage = message.split(" ", 2);
                    message = splitMessage[1];
                    broadcastMassage("[" + getCurrentTime() + "] " + message);
                }
                else if(message.startsWith("/nick")) {
                    String[] splitMessage = message.split(" ", 2);
                    username = splitMessage[1];
                }
                else {
                    broadcastMassage("[" + getCurrentTime() + "] " + message);
                }
            } catch (Exception e) {
                closeEverything(socket, reader, writer);
                break;
            }
        }
    }

    public void broadcastMassage(String message){
        for (ClientHandler client : clients){
            try {
                if(!client.username.equals(username)){
                    client.writer.write(message);
                    client.writer.newLine();
                    client.writer.flush();
                }
            } catch (IOException e) {
                closeEverything(socket, reader, writer);
            }
        }
    }

    public void disconnect(){
        clients.remove(this);
        broadcastMassage("Server" + username + " has left the chat");
    }

    private static String getCurrentTime() {
        return new SimpleDateFormat("hh:mm a").format(new Date());
    }

    public void closeEverything(Socket socket, BufferedReader reader, BufferedWriter writer){
        try {
            if(socket != null)
                socket.close();
            if(reader != null)
                reader.close();
            if(writer != null)
                writer.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
