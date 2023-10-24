import java.util.Arrays;
import java.util.List;

public class ClientInputParser{
    // Enum with client request commands
    public enum ClientInput{
        RANDOM_INPUT,
        NICKNAME_REQUEST,
        MESSAGE_RECEIVED,
        QUIT_CHAT
    }

    public ClientInputParser(){}

    // Method for parsing client request commands
    public ClientInput parseClientInput(String input){
        List<String> substrings = Arrays.stream(input.split(" ")).toList();
        ClientInput result = ClientInput.RANDOM_INPUT;

        switch (substrings.get(0)) {
            case "/nick" -> result = ClientInput.NICKNAME_REQUEST;
            case "/quit" -> result = ClientInput.QUIT_CHAT;
            case "/msg" -> result = ClientInput.MESSAGE_RECEIVED;
            default -> {
            }
        }

        return result;
    }
}
