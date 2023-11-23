package model;

import java.util.List;

public interface IRepository {
    List<Conversation> importConversations();
    void exportConversation(List<Conversation> conversations);
}
