package Chat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

class SearchMessagesByUserTest {
    private User max;
    private User sara;
    private User tom;
    private ChatServer server;
    private ArrayList<Message> messageHistory;
    private Message message1;
    private Message message2;
    private Message message3;

    @BeforeEach
    void setUp() {
        server = new ChatServer(); // Single ChatServer instance for all users
        max = new User("Max", server);
        sara = new User("Sara", server);
        tom = new User("Tom", server);
        messageHistory = new ArrayList<>();
        message1 = new Message(max, "Hello Sara", sara);
        message2 = new Message(sara, "Hello Max", max);
        message3 = new Message(tom, "Hi Sara", sara);
        messageHistory.add(message1);
        messageHistory.add(message2);
        messageHistory.add(message3);
    }

    @Test
    void testHasNextWithMessages() {
        SearchMessagesByUser iterator = new SearchMessagesByUser(sara, messageHistory);
        assertTrue(iterator.hasNext());
        iterator.next(); // Move to the first message
        assertTrue(iterator.hasNext());
    }

    @Test
    void testHasNextNoMessages() {
        // Empty the message history
        messageHistory.clear();
        SearchMessagesByUser iterator = new SearchMessagesByUser(sara, messageHistory);
        assertFalse(iterator.hasNext());
    }

    @Test
    void testNext() {
        SearchMessagesByUser iterator = new SearchMessagesByUser(sara, messageHistory);
        assertSame(message1, iterator.next());
        assertSame(message2, iterator.next());
    }

    @Test
    void testRemove() {
        SearchMessagesByUser iterator = new SearchMessagesByUser(sara, messageHistory);
        iterator.next(); // Access first message to activate remove possibility
        assertThrows(UnsupportedOperationException.class, iterator::remove, "Remove should throw UnsupportedOperationException.");
    }


    @Test
    void testIteratorForSara() {
        SearchMessagesByUser iterator = new SearchMessagesByUser(sara, messageHistory);
        assertTrue(iterator.hasNext());
        assertSame(message1, iterator.next());
        assertTrue(iterator.hasNext());
        assertSame(message2, iterator.next());
        assertTrue(iterator.hasNext());
        assertSame(message3, iterator.next());
        assertFalse(iterator.hasNext());
    }

    @Test
    void testIteratorForTom() {
        SearchMessagesByUser iterator = new SearchMessagesByUser(tom, messageHistory);
        assertTrue(iterator.hasNext());
        assertSame(message3, iterator.next());
        assertFalse(iterator.hasNext());
    }
}