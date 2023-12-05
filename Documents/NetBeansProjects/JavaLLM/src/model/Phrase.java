package model;

import java.io.Serializable;

public class Phrase implements Serializable{
    
    private String type;
    private int lenght;
    private String content;

    /**
     *
     * @param type
     * @param lenght
     * @param content
     */
    public Phrase(String type, int lenght, String content) {
        this.type = type;
        this.lenght = lenght;
        this.content = content;
    }

    public String getType() {
        return type;
    }

    public int getLenght() {
        return lenght;
    }

    public String getContent() {
        return content;
    }
    
}
