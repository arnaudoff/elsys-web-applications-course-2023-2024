import Command.Command;
import Command.CommandType;

import java.util.ArrayList;
import java.util.Scanner;

public class ConsoleManager {
    private final Scanner scanner;

    public ConsoleManager() {
        scanner = new Scanner(System.in);
    }

    public Command readCommand() {
        System.out.print("> ");
        String line = scanner.nextLine();
        String[] tokens = line.split(" ", 2);

        CommandType type = CommandType.fromString(tokens[0]);

        if (type == null) {
            throw new IllegalArgumentException("Unknown command type: " + tokens[0]);
        }

        ArrayList<String> args = new ArrayList<>();

        switch (type) {
            case CONNECT -> {
                if (tokens.length != 2) {
                    throw new IllegalArgumentException("No arguments provided for command " + type);
                }

                String[] arguments = tokens[1].split(":", 2);

                if (arguments.length != 2) {
                    throw new IllegalArgumentException("Invalid arguments format for command " + type);
                }

                args.add(arguments[0]);
                args.add(arguments[1]);
            }

            case NICK, MSG -> {
                if (tokens.length != 2) {
                    throw new IllegalArgumentException("No arguments provided for command " + type);
                }

                args.add(tokens[1]);
            }
        }

        return new Command(type, args.toArray(new String[0]));
    }

    public void printWelcomeMessage() {
        System.out.println("---------Welcome to the chat!---------");
        System.out.println("Type " + CommandType.HELP + " to see the list of commands");
    }

    public void printHelpMenu() {
        System.out.println("---------List of commands---------");
        System.out.println(CommandType.CONNECT + " <host:port> - connect to the server");
        System.out.println(CommandType.NICK + " <nickname> - set your username");
        System.out.println(CommandType.MSG + " <message> - send a message");
        System.out.println(CommandType.QUIT + " - disconnect from the server");
        System.out.println(CommandType.EXIT + " - exit the program");
        System.out.println(CommandType.HELP + " - see the list of commands");
    }

    public void close() {
        scanner.close();
    }
}
