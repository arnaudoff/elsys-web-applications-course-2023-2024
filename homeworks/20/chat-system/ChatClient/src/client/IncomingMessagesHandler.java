package client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class IncomingMessagesHandler implements Runnable{
    private final Socket socket;
    private final BufferedReader in;
    private int working;

    public IncomingMessagesHandler(Socket socket) throws IOException {
        this.socket = socket;
        this.in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        this.working = 1;
    }

    @Override
    public void run() {
        while (working == 1) {
            try {
                System.out.println(in.readLine());
            } catch (IOException e) {
                System.out.println("Server disconnected");
                break;
            }
        }
    }

    public void stop() {
        this.working = 0;
        try {
            socket.close();
            in.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
