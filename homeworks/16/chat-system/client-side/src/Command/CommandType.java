package Command;

public enum CommandType {
    CONNECT("/connect"),
    NICK("/nick"),
    MSG("/msg"),
    QUIT("/quit"),
    EXIT("/exit"),
    HELP("/help");

    private final String type;

    CommandType(String type) {
        this.type = type;
    }

    public static CommandType fromString(String text) {
        for (CommandType commandType : CommandType.values()) {
            if (commandType.type.equalsIgnoreCase(text)) {
                return commandType;
            }
        }
        return null;
    }

    @Override
    public String toString() {
        return type;
    }
}
