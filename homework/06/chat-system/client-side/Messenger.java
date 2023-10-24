import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Messenger extends Thread{
    Socket messenger;
    PrintWriter sender;
    BufferedReader receiver;
    String clientName;
    boolean nickname_msg_resp, nickname_available;
    boolean msg_received_ack;

    public Messenger(Socket socket) throws RuntimeException{
        if(socket == null){
            throw new RuntimeException("Given socket object is null");
        }else if(!socket.isConnected()){
            throw new RuntimeException("Socket is not connected");
        }

        messenger = socket;
        try{
            sender = new PrintWriter(messenger.getOutputStream());
            receiver = new BufferedReader(new InputStreamReader(messenger.getInputStream()));
        }catch(IOException e){
            System.out.println(e + "<!!!\n(Could not open socket output/input stream)\nExiting program\n!!!>");
            System.exit(-1);
        }

        this.start();
    }

    public void run(){
        String data;

        while(true){
            try{
                // Receive message from socket server
                data = receiver.readLine();
                if(data == null){
                    continue;
                }

                // Check if input is an acknowledgement for received user message
                if(data.equals("/msg_received_ack")){
                    msg_received_ack = true;
                    continue;
                }else if(data.equals("/quit_chat_ack")){
                    try{
                        messenger.close();
                    }catch(Exception e){
                        System.out.println("Error during closing socket connection to chat server\n" + e);
                    }

                    this.interrupt();
                }

                // Classify input from server and do the according action
                switch (data.substring(0, 5)) {
                    // Response for nickname request
                    case "/nick" -> {
                        nickname_msg_resp = true;
                        parseNickname(data.split(" ")[1]);
                        nickname_available = (clientName != null);
                    }
                    // Message from another user
                    case "/msg " -> System.out.println(data.substring(5));
                    default -> System.out.println("Parsing error: " + data);
                }
            }catch(Exception e){
                if(e.getMessage().equals("Connection reset")){
                    System.out.println("The connection to the chat server has been turned off\nExiting program\n");
                    System.exit(-1);
                }else{
                    System.out.println("<!!!\nException in reception thread -\\/\n" + e + "!!!>");
                }
            }
        }
    }

    // Method for sending a nickname request
    public void giveNickname(String nickname){
        // Send request
        sender.println("/nick " + nickname);
        sender.flush();
        clientName = nickname;

        // Wait 100ms for response from server
        try{
            Thread.sleep(1000);
        }catch(InterruptedException e){
            System.out.println(e);
            return;
        }

        // Check if response was received
        if(nickname_msg_resp){
            // Check if the nickname is available
            if(!nickname_available){
                nickname_msg_resp = false;
                System.out.println("<!!!\nNickname not available\n!!!>");
            }else{
                nickname_available = false;
            }
        }else{
            System.out.println("<!!!\nDid not receive response for nickname request\n!!!>");
        }
    }

    // Parse nickname request response string and check if the nickname is available
    private void parseNickname(String acknowledge){
        if(acknowledge.equals("NICKNAME_NOT_AVAILABLE")){
            clientName = null;
        }else if(!acknowledge.equals("ACKNOWLEDGE")) {
            throw new RuntimeException("<!!!\nIncorrect response format from server\n!!!>");
        }
    }

    // Send a request to server for quiting chat
    public void quitChat() throws RuntimeException{
        sender.println("/quit " + this.clientName);
        sender.flush();
    }

    // Send a message to chat
    public void sendMessage(String message) throws RuntimeException{
        int counter = 30;

        while(true){
            // Send message and decrement counter
            // If counter is down to 0, throw an exception
            counter = counter - 1;
            if(counter == 0){
                throw new RuntimeException("Could not send message. TRIES: 30");
            }
            sender.println("/msg " + message);
            sender.flush();

            // Wait in order to receive message acknowledgement
            try{
                Thread.sleep(150);
            }catch(InterruptedException e){
                throw new RuntimeException(e);
            }

            // If acknowledgment is received, break from loop
            if(msg_received_ack){
                msg_received_ack = false;
                break;
            }
        }
    }
}
