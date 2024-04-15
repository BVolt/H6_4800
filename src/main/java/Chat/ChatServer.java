package Chat;

import java.util.ArrayList;
import java.util.Arrays;

public class ChatServer {
    private ArrayList<User> users = new ArrayList<>();

    public void register(User user){
        if(!users.contains(user))
            users.add(user);
    }

    public void unregister(User user){
        users.remove(user);
    }


    public void sendMessage(Message message){
        if(users.contains(message.getSender())){
            for(User recipient: message.getRecipients()){
                if(users.contains(recipient) && !recipient.getBlockList().contains(message.getSender())) {
                    recipient.receiveMessage(message);
                }
            }
        }
    }

    public void undoMessage(Message message){
        for(User recipient: message.getRecipients()){
            recipient.getChatHistory().remove(message);
        }
    }

    public ArrayList<User> getUsers() {
        return users;
    }

    public void blockUser(User user, User blockedUser){
        user.addToBlockList(blockedUser);
    }
}
