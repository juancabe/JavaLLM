package model;

import java.io.Serializable;
import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

public class Message implements Serializable {

    private String sender;
    private long epochSeconds;
    private String content;

    public Message(String sender, String content, Instant instant) {
        this.sender = sender;
        this.epochSeconds = instant.getEpochSecond();
        this.content = content;
    }

    public Message() {

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

    public String getEpochAsDate() {
        Instant instant = Instant.ofEpochSecond(epochSeconds);
        ZonedDateTime zonedDateTime = ZonedDateTime.ofInstant(instant, ZoneId.systemDefault());
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("[dd/MM/yy: HH:mm:ss]");
        String formattedDate = zonedDateTime.format(formatter);

        return formattedDate;
    }

}
