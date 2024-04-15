package Chat;

public class Main {
    public static void main(String[] args) {
        ChatServer chatServer = new ChatServer();

        //Server Registration is handled in this constructor to follow DRY principle
        User bob = new User("Bob", chatServer);
        User rob = new User("Rob", chatServer);
        User cob = new User("Cob", chatServer);

        /*
        For sending messages I would've preferred to call chatServer.sendMessage here
        however the instructions specify User class requiring a send message function so
        the following implementation has the chatServer.sendMessage within the user.sendMessage
        */

        bob.sendMessage("Hello Rob and cob", rob, cob);
        rob.sendMessage("Hi Bob", bob);
        cob.sendMessage("Hi Bob and Rob", rob, bob);


        rob.sendMessage("This message should be undone", bob, cob);
        rob.undo();
        System.out.println("Bob's History - After Undo\n========================================");
        bob.displayMessagesByUser(rob);

        chatServer.blockUser(rob, cob); // Rob block bob
        cob.sendMessage("This message should be blocked", rob);
        System.out.println("Rob's History - After Blocked Message\n========================================");
        rob.displayMessagesByUser(cob);
    }
}
