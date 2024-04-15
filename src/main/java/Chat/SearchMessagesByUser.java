package Chat;

import java.util.ArrayList;
import java.util.Iterator;

public class SearchMessagesByUser implements Iterator {
    private User userToSearchWith;
    private int indexInMessageHistory;
    private ArrayList<Message> history;

    private int messageHistorySize;

    public SearchMessagesByUser(User userToSearchWith, ArrayList<Message> history) {
        this.userToSearchWith = userToSearchWith;
        this.indexInMessageHistory = 0;
        this.history = history;
        this.messageHistorySize = history.size();
    }

    @Override
    public boolean hasNext(){
        Message message = null;
        while(indexInMessageHistory < messageHistorySize){
            message = history.get(indexInMessageHistory);
            if(message.getSender().equals(userToSearchWith) || message.getRecipients().contains(userToSearchWith)){
                return true;
                }
            indexInMessageHistory++;
        }
        return false;
    }

    @Override
    public Message next(){
        if(hasNext()){
            return history.get(indexInMessageHistory++);
        }
        return null;
    }

    @Override
    public void remove(){
        Iterator.super.remove();
    }
}
