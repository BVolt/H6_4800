package Chat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Iterator;

import static org.junit.jupiter.api.Assertions.*;

class ChatHistoryTest {
    private ChatHistory chatHistory;
    private Message message1;
    private Message message2;
    private User max;
    private User sara;
    private ChatServer server;

    @BeforeEach
    void setUp() {
        server = new ChatServer();
        chatHistory = new ChatHistory();
        max = new User("Max", server);
        sara = new User("Sara", server);

        message1 = new Message(max, "Hi", sara);
        message2 = new Message(sara, "Hey", max);
        chatHistory.addMessageToHistory(message1);
        chatHistory.addMessageToHistory(message2);
    }

    @Test
    void testAddMessageToHistory() {
        Message message3 = new Message(sara, "Yo", max);
        chatHistory.addMessageToHistory(message3);
        assertEquals(message3, chatHistory.getLastMessage());
    }

    @Test
    void testGetLastMessage() {
        assertEquals(message2, chatHistory.getLastMessage());
    }

    @Test
    void testRemove() {
        chatHistory.remove(message2);
        assertFalse(chatHistory.getHistory().contains(message2) );
        assertEquals(message1, chatHistory.getLastMessage());
    }

    @Test
    void testIteratorByUser() {
        Iterator<Message> iterator = chatHistory.iterator(sara);
        assertTrue(iterator.hasNext());
        assertEquals(message1, iterator.next());
    }
}