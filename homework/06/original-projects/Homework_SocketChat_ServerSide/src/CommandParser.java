import java.util.Arrays;
import java.util.List;

public class CommandParser{
    // Enum with commands
    public enum ParsedCommand{
        CMD_SHUTDOWN,
        CMD_BAN_CLIENT,
        RANDOM_INPUT
    }

    public CommandParser(){}

    // Method for parsing command from server terminal
    public ParsedCommand parseCommand(String input){
        List<String> substrings = Arrays.stream(input.split(" ")).toList();
        ParsedCommand command = ParsedCommand.RANDOM_INPUT;

        if(substrings.get(0).equals("/cmd")){
            switch (substrings.get(1)) {
                case "shutdown" -> command = ParsedCommand.CMD_SHUTDOWN;
                case "ban_client" -> command = ParsedCommand.CMD_BAN_CLIENT;
            }
        }

        return command;
    }
}
