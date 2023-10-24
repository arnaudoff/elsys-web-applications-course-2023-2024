import java.io.*;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class TCPClient {
    private String nickname = "Anonymous";
    private Socket socket;
    private PrintWriter out;

    public TCPClient(String nickname) {
        this.nickname = nickname;
    }

    public void connect(String serverIp, int serverPort) {
        try {
            socket = new Socket(serverIp, serverPort);
            out = new PrintWriter(socket.getOutputStream(), true);
            System.out.println("Connected to the server.");


            Thread messageListener = new Thread(new MessageListener(socket));
            messageListener.start();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void setNickname(String newNickname) {
        this.nickname = newNickname;
        System.out.println("Nickname set to: " + nickname);
    }


    public void sendMessage(String message) {
        if (out != null) {
            SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
            String currentTime = sdf.format(new Date());
            String formattedMessage = "[" + currentTime + "] " + nickname + ": " + message;
            out.println(formattedMessage);

        }
    }

    public void quit() {
        try {
            out.println("/quit");
            socket.close();
            System.out.println("Connection closed.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    class MessageListener implements Runnable {
        private Socket socket;

        public MessageListener(Socket socket) {
            this.socket = socket;
        }

        @Override
        public void run() {
            try {
                BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                String message;
                while ((message = in.readLine()) != null) {
                    System.out.println(message);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] argv) {
        TCPClient client = new TCPClient("Anonymous");
        Scanner scanner = new Scanner(System.in);

        while (true) {
            String input = scanner.nextLine();

            if (input.startsWith("/connect")) {
                String[] parts = input.split(" ");
                if (parts.length == 2) {
                    String[] serverInfo = parts[1].split(":");
                    if (serverInfo.length == 2) {
                        String serverIp = serverInfo[0];
                        int serverPort = Integer.parseInt(serverInfo[1]);
                        client.connect(serverIp, serverPort);
                    }
                }
            } else if (input.startsWith("/nick")) {
                client.setNickname(input.substring(6));//maham purvite 5 za da ne pochva s "/nick "

            } else if (input.startsWith("/msg")) {
                client.sendMessage(input.substring(5));//pravq sushtoto za "/msg "
            } else if (input.equals("/quit")){
                client.quit();
            }else{
                System.out.println("There is no such command.");
            }
        }
    }
}
