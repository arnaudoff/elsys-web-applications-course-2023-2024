import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    private static int port;
    private static String host;
    private Socket socket;
    private BufferedReader reader;
    private BufferedWriter writer;
    private String username;

    public Client(Socket socket, String username) {
        try{
            this.socket = socket;
            this.username = username;

            this.reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            this.writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
        } catch (Exception e){
            closeEverything(socket, reader, writer);
        }
    }


    public void sendMessage(){
        try {
            writer.write(username);
            writer.newLine();
            writer.flush();

            Scanner scanner = new Scanner(System.in);
            while (socket.isConnected()){
                String newMessage = scanner.nextLine();
                writer.write(username +":" + newMessage);
                writer.newLine();
                writer.flush();
            }
        } catch (Exception e) {
            closeEverything(socket, reader, writer);
        }
    }

    public void listenForMessage(){
        new Thread(new Runnable(){
            @Override
            public void run() {
                String message;
                while (socket.isConnected()){
                    try {
                        message = reader.readLine();
                        if (message.startsWith("/quit")) {
                            break;
                        }else if (message.startsWith("/msg")) {
                            String[] splitMessage = message.split(" ", 2);
                            message = splitMessage[1];
                            System.out.println(username + ": " + message);
                        }
                        else if(message.startsWith("/nick")) {
                            String[] splitMessage = message.split(" ", 2);
                            username = splitMessage[1];
                        }
                        else {
                            //System.out.println(message);
                        }
                    } catch (Exception e) {
                        closeEverything(socket, reader, writer);
                        break;
                    }
                }
            }
        }).start();
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

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter your username: ");
        String username = scanner.nextLine();
       /* System.out.println("Enter the host you want to connect to: ");
        host = scanner.nextLine();
        System.out.println("Enter the port you want to connect to: ");
        port = scanner.nextInt();
*/
        System.out.println("write in this format: /connect <ip of chat server>:<port>\n");

        String message = scanner.nextLine();
        if (message.startsWith("/connect")) {
            String[] tokens = message.split(" ");
            String[] HostPort = tokens[1].split(":");
            String host = HostPort[0];
            int port = Integer.parseInt(HostPort[1]);

            try {
            Socket socket = new Socket(host, port);
            Client client = new Client(socket, username);
            client.listenForMessage();
            client.sendMessage();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    }
}