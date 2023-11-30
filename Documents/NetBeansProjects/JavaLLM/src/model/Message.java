package model;

import java.time.Instant;

public class Message {
    
    private String sender;
    private long epochSeconds;
    private String content;
    
    public Message(String sender, String content){
        this.sender = sender;
        this.epochSeconds = Instant.now().getEpochSecond();
        this.content = content;
    }

    public Message(){
        
    }
    
    public String getSender() {
        return sender;
    }

    public long getEpochSeconds() {
        return epochSeconds;
    }

    public String getContent() {
        return content;
    }
    
    
}
