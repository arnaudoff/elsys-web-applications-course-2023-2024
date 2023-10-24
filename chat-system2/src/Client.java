import java.io.*;
import java.net.*;
import java.util.*;

public class Client {
    private Socket socket;
    private String serverIP;
    private int serverPort;
    private MessageSender sender;
    private MessageReceiver receiver;

    public Client(String serverIP, int serverPort) {
        this.serverIP = serverIP;
        this.serverPort = serverPort;
    }

    public void start() {
    }

    public static void main(String[] args) {

    }
}
