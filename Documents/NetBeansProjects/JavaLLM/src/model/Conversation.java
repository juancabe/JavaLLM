package model;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import java.io.Serializable;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

public class Conversation implements Serializable {

    private List<Message> messagesArray;
    private double initEpochSeconds;
    private double endEpochSeconds;
    @JacksonXmlProperty(localName = "llmidentifier")
    private String LLMIdentifier;

    public Conversation() {
        // Constructor sin argumentos
    }

    public Conversation(String LLMIdentifier) {
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

    public void setEndEpoch() {
        this.endEpochSeconds = Instant.now().getEpochSecond();
    }

    public void addMessage(String sender, Instant instant, String string) {

        Message message = new Message(sender, string, instant);
        messagesArray.add(message);
    }

}
