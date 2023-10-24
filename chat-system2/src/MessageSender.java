import java.io.*;
import java.net.*;
import java.util.*;

import java.io.*;
import java.net.*;
import java.util.*;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class MessageSender {
    private PrintWriter out;
    private int messageIdCounter;
    private Map<Integer, String> unacknowledgedMessages;
    private BlockingQueue<Integer> acknowledgedQueue;

    public MessageSender(PrintWriter out) {
        this.out = out;
        this.messageIdCounter = 1;
        this.unacknowledgedMessages = new HashMap<>();
        this.acknowledgedQueue = new LinkedBlockingQueue<>();

        Thread acknowledgmentHandler = new Thread(() -> {
            while (true) {
                try {
                    int acknowledgedMessageId = acknowledgedQueue.take();
                    handleAcknowledgment(acknowledgedMessageId);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        acknowledgmentHandler.setDaemon(true);
        acknowledgmentHandler.start();
    }

    public void sendMessage(String message) {
        int messageId = messageIdCounter++;
        String messageWithId = messageId + " " + message;
        unacknowledgedMessages.put(messageId, messageWithId);
        System.out.println(messageWithId);
    }

    private void handleAcknowledgment(int messageId) {
        unacknowledgedMessages.remove(messageId);
    }

    public void resendUnacknowledgedMessages() {
        for (String messageWithId : unacknowledgedMessages.values()) {
            out.println(messageWithId);
        }
    }

    public void processAcknowledgment(int messageId) {
        acknowledgedQueue.offer(messageId);
    }
}
