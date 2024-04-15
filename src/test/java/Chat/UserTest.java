package Chat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Iterator;

class UserTest {
    private User max;
    private User sara;
    private User tom;
    private ChatServer server;

    @BeforeEach
    void setUp() {
        server = new ChatServer();
        max = new User("Max", server);
        sara = new User("Sara", server);
        tom = new User("Tom", server);
    }

    @Test
    void testSendMessage() {
        max.sendMessage("Hello Sara", sara);
        max.displayAllMessages();
        Message lastMessage = sara.getChatHistory().getLastMessage();
        assertNotNull(lastMessage);
        assertEquals("Hello Sara", lastMessage.getMessageContent());
        assertEquals(max, lastMessage.getSender());
        assertTrue(lastMessage.getRecipients().contains(sara));
    }

    @Test
    void testReceiveMessage() {
        Message message = new Message(max, "Hi Tom", tom);
        tom.receiveMessage(message);
        Message lastReceived = tom.getChatHistory().getLastMessage();
        assertEquals("Hi Tom", lastReceived.getMessageContent());
    }

    @Test
    void testUndo() {
        max.sendMessage("Message to undo", sara);
        Message lastMessage = max.getChatHistory().getLastMessage();
        assertEquals("Message to undo", lastMessage.getMessageContent());
        max.undo();
        assertNull(max.getChatHistory().getLastMessage());
    }

    @Test
    void testBlockUser() {
        sara.addToBlockList(max);
        assertTrue(sara.getBlockList().contains(max));
        max.sendMessage("Blocked message", sara);
        assertNull(sara.getChatHistory().getLastMessage());
    }

    @Test
    void testIteratorByUser() {
        max.sendMessage("Hi Sara", sara);
        tom.sendMessage("Hello Sara", sara);
        Iterator<Message> iterator = sara.iterator(max);
        assertTrue(iterator.hasNext());
        assertEquals("Hi Sara", iterator.next().getMessageContent());
        assertFalse(iterator.hasNext());
    }
}