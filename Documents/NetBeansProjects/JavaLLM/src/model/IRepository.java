package model;

import java.util.List;

interface IRepository {
    List<Conversation> importConversations();
    void exportConversation(List<Conversation> conversations);
}
