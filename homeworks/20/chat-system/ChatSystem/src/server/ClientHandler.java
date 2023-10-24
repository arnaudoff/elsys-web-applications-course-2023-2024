package server;

import java.io.*;
import java.net.*;
import java.sql.Timestamp;
import java.util.List;


class ClientHandler extends Thread {
    private Socket socket;
    private PrintWriter out;
    private BufferedReader in;
    private String nickname;

    public ClientHandler(Socket socket) throws IOException {
        this.socket = socket;
        this.in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        this.out = new PrintWriter(socket.getOutputStream(), true);
        this.nickname = "Guest";
    }

    @Override
    public void run() {
        try {
            String inputLine;
            while ((inputLine = in.readLine()) != null) {
                if (inputLine.startsWith("/nick")) {
                    String nickname = inputLine.split(" ")[1];
                    Timestamp timestamp = new java.sql.Timestamp(System.currentTimeMillis());
                    String message = "[" + timestamp + "] " + this.nickname + " changed nickname to " + nickname;
                    ChatServer.broadcastMessage(message);
                    this.setNickname(nickname);
                } else if (inputLine.startsWith("/quit")) {
                    break;
                } else {
                    String[] line = inputLine.split(" ");
                    String message = String.join(" ", List.of(line).subList(1, line.length));
                    Timestamp timestamp = new java.sql.Timestamp(System.currentTimeMillis());
                    message = "[" + timestamp + "] " + this.nickname + ": " + message;
                    ChatServer.broadcastMessage(message);
                }
            }
        } catch (IOException e) {
            System.out.println("Exception caught when trying to listen on port "
                    + socket.getPort() + " or listening for a connection / client disconnected");
        } finally {
            try {
                socket.close();
                in.close();
                out.close();
                ChatServer.getClients().remove(this);
            } catch (IOException e) {
                System.out.println("Can't close socket");
            }
        }
    }

    public void sendMessage(String message) {
        out.println(message);
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }
}