import java.net.Socket;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Main{
    public static void main(String[] args){
        Socket socket = null;
        Messenger messenger = null;

        Scanner scanner = new Scanner(System.in);
        String user_input;

        while(true){
            List<String> substrings;

            // Receive input from user
            user_input = scanner.nextLine();
            substrings = Arrays.stream(user_input.split(" ")).toList();

            if(user_input.equals("/quit")){
                // Send a request to the server that you're quiting the chat
                if(messenger != null){
                    messenger.quitChat();
                }

                continue;
            }else if(user_input.equals("/exit")){
                // Exit program
                if(messenger != null){
                    // Close connection to server if it wasn't, before exiting program
                    if(socket != null && socket.isConnected()){
                        messenger.quitChat();

                        try{
                            socket.close();
                        }catch(Exception e){
                            System.out.println("<!!!\nCould not close socket connection during exit procedure\n!!!>\n");
                            break;
                        }
                    }
                }

                // Exit program
                System.out.println("Exiting program");
                System.exit(0);
            }else if(substrings.size() < 2){
                continue;
            }

            // Classify input and do action according to the first argument
            switch (substrings.get(0)) {
                // Try to connect to server
                case "/connect" -> {
                    String host, port;
                    List<String> hostParts = Arrays.stream(substrings.get(1).split(":")).toList();

                    // Break from switch statement if there aren't two sub-arguments (host and port)
                    if (hostParts.size() != 2) {
                        break;
                    } else {
                        host = hostParts.get(0);
                        port = hostParts.get(1);
                    }

                    // Try to make socket connection with server at given host and port
                    try {
                        socket = new Socket(host, Integer.parseInt(port));
                        messenger = new Messenger(socket);   //Messenger extends Thread class and starts itself
                    } catch (Exception e) {
                        System.out.println("<!!!\nSomething went wrong during connect procedure\n!!!>\n" + e);
                        messenger = null;
                    }
                }
                case "/nick" -> {
                    // Send a request to the server in order to acknowledge an inputted nickname
                    if (messenger != null) {
                        messenger.giveNickname(substrings.get(1));
                    }
                }
                case "/msg" -> {
                    // Send a message in chat
                    if (messenger != null) {
                        messenger.sendMessage(user_input.substring(5));
                    }
                }
                default -> {
                }
            }
        }
    }
}