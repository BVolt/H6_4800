package Chat;

import java.util.ArrayList;
import java.util.Iterator;

public class User implements IterableByUser{
    private String userName;

    private ChatServer chatServer;
    private ChatHistory chatHistory;
    private MessageMemento messageMemento;

    private ArrayList<User> blockList;


    public User(String userName, ChatServer chatServer) {
        this.userName = userName;
        this.chatServer = chatServer;
        this.chatHistory = new ChatHistory();
        this.blockList = new ArrayList<>();
        this.chatServer.register(this);
    }

    public void sendMessage(String messageContent, User... recipients){
        Message message = new Message(this, messageContent, recipients);
        this.chatServer.sendMessage(message);
        this.chatHistory.addMessageToHistory(message);
        this.messageMemento = new MessageMemento(message);
    }

    public void receiveMessage(Message message){
        this.chatHistory.addMessageToHistory(message);
    }

    public void undo(){
        if(this.messageMemento != null){
            this.chatHistory.remove(messageMemento.getState());
            this.chatServer.undoMessage(messageMemento.getState());
            this.messageMemento = null;
        }
    }

    public ArrayList<User> getBlockList() {
        return blockList;
    }

    public void addToBlockList(User user){
        blockList.add(user);
    }

    public ChatHistory getChatHistory() {
        return chatHistory;
    }

    @Override
    public Iterator iterator(User userToSearchWith){
        return chatHistory.iterator(userToSearchWith);
    }

    @Override
    public String toString() {
        return userName;
    }

    public void displayAllMessages(){
        Iterator<Message> iterator = this.iterator(this);

        while(iterator.hasNext()){
            System.out.println(iterator.next());
        }
    }

    public void displayMessagesByUser(User user){
        Iterator<Message> iterator = this.iterator(user);

        while(iterator.hasNext()){
            System.out.println(iterator.next());
        }
    }
}
