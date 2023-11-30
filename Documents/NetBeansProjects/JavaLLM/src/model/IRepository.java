package model;

import java.io.IOException;
import java.util.List;

public interface IRepository {
    
    public static String exportPathNoExt = System.getProperty("user.home") +
            System.getProperty("file.separator") +
            "Desktop" +
            System.getProperty("file.separator") +
            "jLLM" +
            System.getProperty("file.separator") +
            "output.";
    public static String importPathNoExt = System.getProperty("user.home") +
            System.getProperty("file.separator") +
            "Desktop" +
            System.getProperty("file.separator") +
            "jLLM" +
            System.getProperty("file.separator") +
            "input.";
    public String getIEType();
    void setFilesVariable();
    List<Conversation> importConversations() throws IOException;
    void exportConversations(List<Conversation> conversations) throws IOException;
}
