import java.io.*;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class ClientHandler implements Runnable {
    private static final List<ClientHandler> clients = new ArrayList<>();
    private final Socket clientSocket;
    private final DataOutputStream outToClient;

    public ClientHandler(Socket clientSocket) throws IOException {
        this.clientSocket = clientSocket;
        this.outToClient = new DataOutputStream(clientSocket.getOutputStream());
        clients.add(this);
    }

    @Override
    public void run() {
        try (BufferedReader inFromUser = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()))) {
            String message;

            while ((message = inFromUser.readLine()) != null) {
                chat(message);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void chat(String message) {
        for (ClientHandler clientHandler : clients) {
            try {
                clientHandler.outToClient.writeBytes(message + '\n');
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
