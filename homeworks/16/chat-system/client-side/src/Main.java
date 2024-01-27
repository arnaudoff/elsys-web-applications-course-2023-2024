import Client.Client;
import Command.Command;

public class Main {
    public static void main(String[] args) {
        ConsoleManager consoleManager = new ConsoleManager();
        Client client = new Client();

        consoleManager.printWelcomeMessage();

        while (true) {
            try {
                Command command = consoleManager.readCommand();

                switch (command.type()) {
                    case CONNECT -> {
                        String host = command.args()[0];
                        int port = Integer.parseInt(command.args()[1]);
                        client.connect(host, port);
                    }

                    case NICK -> {
                        String username = command.args()[0];
                        client.setUsername(username);
                    }

                    case MSG -> {
                        String message = command.args()[0];
                        client.sendMessage(message);
                    }

                    case QUIT -> client.quit();
                    case HELP -> consoleManager.printHelpMenu();

                    case EXIT -> {
                        if (client.isConnected()) {
                            client.cleanResources();
                        }
                        consoleManager.close();
                        return;
                    }
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
