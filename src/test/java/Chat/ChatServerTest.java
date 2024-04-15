package Chat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ChatServerTest {
    private ChatServer server;
    private User max;
    private User sara;
    private User tom;

    @BeforeEach
    void setUp() {
        server = new ChatServer();
        max = new User("Max", server);
        sara = new User("Sara", server);
        tom = new User("Tom", server);
    }

    @Test
    void testSendMessage() {
        max.sendMessage("Hello", sara);
        Message lastMessage = sara.getChatHistory().getLastMessage();
        assertNotNull(lastMessage);
        assertEquals("Hello", lastMessage.getMessageContent());
        assertEquals(max, lastMessage.getSender());
        assertTrue(lastMessage.getRecipients().contains(sara));
    }

    @Test
    void testUndoMessage() {
        Message sentMessage = new Message(max, "Test", sara, tom);
        sara.receiveMessage(sentMessage);
        tom.receiveMessage(sentMessage);
        assertEquals("Test", sara.getChatHistory().getLastMessage().getMessageContent());
        assertEquals("Test", tom.getChatHistory().getLastMessage().getMessageContent());
        server.undoMessage(sentMessage);
        assertTrue(sara.getChatHistory().getHistory().isEmpty());
        assertTrue(tom.getChatHistory().getHistory().isEmpty());
    }

    @Test
    void testRegisterAndUnregisterUser() {
        User tom = new User("Tom", server);
        server.register(tom);
        assertTrue(server.getUsers().contains(tom));

        server.unregister(tom);
        assertFalse(server.getUsers().contains(tom));
    }

    @Test
    void testBlockUser() {
        server.blockUser(sara, max);
        assertTrue(sara.getBlockList().contains(max));
        max.sendMessage("Blocked?", sara);
        assertNull(sara.getChatHistory().getLastMessage());
    }
}