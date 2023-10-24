import java.util.ArrayList;
import java.util.List;

public class Room {
    private String name;
    private List<User> users;
    private List<Message> messages;

    public Room(String name) {
        this.name = name;
        this.users = new ArrayList<>();
        this.messages = new ArrayList<>();
    }

    public void addUser(User user) {
    }

    public void broadcastMessage(Message message) {
    }
}
