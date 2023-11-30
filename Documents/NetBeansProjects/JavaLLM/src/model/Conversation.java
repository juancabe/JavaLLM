package model;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

public class Conversation {
    
    private String LLMIdentifier;
    private ArrayList<Message> messagesArray;
    private double initEpochSeconds;
    private double endEpochSeconds;

    public Conversation(String LLMIdentifier){
        this.LLMIdentifier = LLMIdentifier;
        this.messagesArray = new ArrayList<>();
        this.initEpochSeconds = Instant.now().getEpochSecond();
    }

    public String getLLMIdentifier() {
        return LLMIdentifier;
    }

    public List<Message> getMessagesArray() {
        return messagesArray;
    }

    public double getInitEpochSeconds() {
        return initEpochSeconds;
    }

    public double getEndEpochSeconds() {
        return endEpochSeconds;
    }
    
    public void addMessage(String sender, String string){
        
        Message message = new Message(sender, string);
        messagesArray.add(message);
    }
    
}
