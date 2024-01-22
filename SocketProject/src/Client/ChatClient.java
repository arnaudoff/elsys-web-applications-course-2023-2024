package Client;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.Socket;

public class ChatClient {
    public static void main(String argv[]) throws Exception {
        String input = "";

        BufferedReader inFromUser = new BufferedReader(new InputStreamReader(System.in));
        Socket clientSocket = null;

        while (input != null && !input.startsWith("/quit")) {
            while (!input.startsWith("/connect ")) {
                System.out.println("Please enter /connect <ip>:<port>");
                input = inFromUser.readLine();
            }
            String[] ipAndPort = input.substring(9).split(":");
            int port = Integer.parseInt(ipAndPort[1]);

            try {
                clientSocket = new Socket(ipAndPort[0], port);
                break;
            } catch (Exception e) {
                System.out.println("Error connecting to server. Please try again. You can enter /quit to exit.");
            }
            input = inFromUser.readLine();
        }

        DataOutputStream outToServer = new DataOutputStream(clientSocket.getOutputStream());

        BufferedReader inFromServer =
                new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        System.out.println("Connected to server.");
        System.out.println("Enter your name: ");
        String name = inFromUser.readLine();
        outToServer.writeBytes(name + '\n');
        System.out.println("You can use these commands: /quit, /nick <new nickname>, /msg <message>");

        boolean isClosed = false;

        Thread outPutThread = new Thread(() -> {
            while(true){
                try{
                    String msg = inFromServer.readLine();
                    if(msg == null || msg.startsWith("terminated"))
                        break;
                    System.out.println(msg);
                    if(msg.startsWith("Server is closed.")) {
                        outToServer.writeBytes("/terminate\n");
                        System.out.println("You can enter /quit to exit.");
                        break;
                    }
                }catch(Exception e){
                    System.out.println("Error reading a message from server. Connection terminated.");
                    break;
                }
            }
        });

        outPutThread.start();

        while (true) {
            input = inFromUser.readLine();
            try{
                outToServer.writeBytes(input + '\n');
            }catch(Exception e){
                if(input.startsWith("/quit")){
                    break;
                }
                System.out.println("Error sending message to server. Connection terminated.");
                break;
            }
            if(input.startsWith("/quit")){
                break;
            }
        }
        outPutThread.join();
        clientSocket.close();
    }
}
