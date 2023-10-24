import java.io.*;
import java.net.*;
import java.util.*;


public class ClientHandler extends Thread {
    private Socket clientSocket;
    private User user;
    private String nickname;

    public ClientHandler(Socket clientSocket) {
        this.clientSocket = clientSocket;
    }

    public void run() {
    }




}
