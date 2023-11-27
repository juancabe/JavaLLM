package model;

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
    
    void setFilesVarible();
    List<Conversation> importConversations();
    void exportConversation(List<Conversation> conversations);
}
