import java.io.*;
import java.net.*;
import java.util.*;

public class MessageReceiver implements Runnable{
    private Socket socket;
    private BufferedReader in;

    public MessageReceiver(BufferedReader in) {
        this.in = in;
    }

    public void startReceiving() {
    }

    public void run() {
    }


}
