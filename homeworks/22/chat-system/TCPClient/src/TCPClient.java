import java.io.*;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

class TCPClient {
    public static void main(String[] args) throws Exception {
        System.out.println("Create new connection to server via /connect <ip>:<port> command");

        BufferedReader inFromUser = new BufferedReader(new InputStreamReader(System.in));
        String connectionSentence = inFromUser.readLine();
        String serverIP;
        int serverPort;

        try{
            if (connectionSentence.contains("/connect")) {
                String[] connectionSentenceArray = connectionSentence
                        .replace("/connect ", "")
                        .trim()
                        .split(":");
                serverIP = connectionSentenceArray[0];
                serverPort = Integer.parseInt(connectionSentenceArray[1]);

                Socket clientSocket = new Socket(serverIP, serverPort);
                DataOutputStream outToServer = new DataOutputStream(clientSocket.getOutputStream());

                Thread thread = getThread(clientSocket);
                thread.start();

                System.out.println("Enter your nickname via /nick <nickname> command");

                String nickname = inFromUser.readLine();
                if (nickname.contains("/nick")) {
                    nickname = nickname.replace("/nick", "").trim();

                    System.out.println("Enter your message via /msg <message> command or /quit to exit");
                    while (!clientSocket.isClosed()) {
                        String message = inFromUser.readLine();
                        if (message.contains("/quit")) {
                            clientSocket.close();
                            break;
                        }
                        if (message.contains("/msg")) {
                            message = message.replace("/msg", "").trim();
                            outToServer.writeBytes("[" + currentTime() + "] " + nickname + ": " + message + '\n');
                        } else {
                            throw new RuntimeException("Invalid command");
                        }
                    }
                } else {
                    throw new RuntimeException("You first need to set a nickname");
                }
            } else {
                throw new RuntimeException("Invalid connection command");
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private static Thread getThread(Socket clientSocket) throws IOException {
        BufferedReader inFromServer = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

        return new Thread(() -> {
            try {
                String message;
                while ((message = inFromServer.readLine()) != null) {
                    System.out.println(message);
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
    }

    public static String currentTime() {
        SimpleDateFormat sdf = new SimpleDateFormat("h:mm a");
        sdf.setTimeZone(TimeZone.getTimeZone("Europe/Sofia"));
        return sdf.format(new Date());
    }
}