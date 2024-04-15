package Chat;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;

public class Message {
    private User sender;
    private ArrayList<User> recipients;
    private String timestamp;
    private String messageContent;

    public Message(User sender, String messageContent, User... recipients) {
        this.sender = sender;
        this.recipients = new ArrayList<>(Arrays.asList(recipients));
        this.messageContent = messageContent;
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM-dd-yy\nHH:mm:ss.SSS");
        this.timestamp = now.format(formatter);
    }

    public User getSender() {
        return sender;
    }

    public ArrayList<User> getRecipients() {
        return recipients;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public String getMessageContent() {
        return messageContent;
    }


    @Override
    public String toString() {
        return
                "From " + sender +
                "\nTo " + recipients +
                '\n' + timestamp +
                "\nMessage: " + messageContent + '\n';
    }
}
