package Chat;

import java.util.ArrayList;
import java.util.Iterator;

public class ChatHistory implements IterableByUser {
    private User user;

    private ArrayList<Message> history = new ArrayList<>();


    public void addMessageToHistory(Message message){
        history.add(message);
    }

    public Message getLastMessage(){
        if(!history.isEmpty())
            return history.get(history.size()-1);
        return null;
    }

    public void remove(Message message){
        history.removeIf(entry -> entry == message);
    }

    public ArrayList<Message> getHistory() {
        return history;
    }

    @Override
    public Iterator iterator(User userToSearchWith){
        return new SearchMessagesByUser(userToSearchWith, history);
    }
}
