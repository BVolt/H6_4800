package Chat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class MessageMementoTest {
    private Message initialMessage;
    private MessageMemento memento;

    @BeforeEach
    void setUp() {
        User sender = new User("Max", new ChatServer());
        User recipient = new User("Sara", new ChatServer());
        initialMessage = new Message(sender, "Hello", recipient);
        memento = new MessageMemento(initialMessage);
    }

    @Test
    void testGetState() {
        assertSame(initialMessage, memento.getState());
    }

    @Test
    void testSetState() {
        User newSender = new User("Tom", new ChatServer());
        User newRecipient = new User("Lucy", new ChatServer());
        Message newMessage = new Message(newSender, "Hi", newRecipient);
        memento.setState(newMessage);
        assertSame(newMessage, memento.getState());
    }
}