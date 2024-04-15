package Chat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class MessageTest {
    private User sender;
    private User recipient;
    private Message message;
    private String content;

    @BeforeEach
    void setUp() {
        sender = new User("Max", new ChatServer());
        recipient = new User("Sara", new ChatServer());
        content = "Hello";
        message = new Message(sender, content, recipient);
    }

    @Test
    void getSender() {
        assertEquals(content, message.getMessageContent());
    }

    @Test
    void getRecipients() {
        assertTrue(message.getRecipients().contains(recipient));
        assertEquals(1, message.getRecipients().size());
    }

    @Test
    void getMessageContent() {
        assertEquals(content, message.getMessageContent());
    }
}