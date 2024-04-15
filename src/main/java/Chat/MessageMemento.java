package Chat;

public class MessageMemento {

    private Message state;

    public MessageMemento(Message state){
        this.state = state;
    }

    public Message getState(){
        return this.state;
    }

    public void setState(Message state){
        this.state = state;
    }
}
