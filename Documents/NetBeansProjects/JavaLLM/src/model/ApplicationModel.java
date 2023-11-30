package model;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ApplicationModel {
    
    private ILLM lanModel;
    private IRepository repo;
    private Conversation conversation;
    private ArrayList<Conversation> conversations;
    
    public ApplicationModel(IRepository repo, ILLM lanModel){
        this.repo = repo;
        this.lanModel = lanModel;
        this.conversations = new ArrayList<>();
    }

    public boolean newConversation() {
        conversation = new Conversation(lanModel.getIdentifier());
        return true;
    }

    public String getNewMessage(String opcion) throws Exception {
        String modelMessage = lanModel.speak(opcion);
        conversation.addMessage("Usuario", opcion);
        conversation.addMessage(lanModel.getIdentifier(), modelMessage);
        return modelMessage;
    }

    public String getLLMId() {
        return conversation.getLLMIdentifier();
    }

    public void endConversation() {
        this.conversation.setEndEpoch();
        conversations.add(this.conversation);
        this.conversation = null;
    }

    public int getNumOfConversations() {
        return conversations.size();
    }

    public String getConversationInitTime(int i) {
        return Double.toString(conversations.get(i).getInitEpochSeconds());
    }

    public String getConversationNumMessages(int i) {
        return Integer.toString(conversations.get(i).getMessagesArray().size());
    }

    public String getConversationFirst20Char(int i) {
        String str = String.format("%20s", conversations.get(i).getMessagesArray().get(0).getContent());
        return str.substring(0, 20);
    }

    public void eliminateConversation(int opcion) {
        conversations.remove(opcion-1);
    }
    
    public void importConversations() throws IOException {
        List<Conversation> importedConversations = repo.importConversations();
        for(Conversation importingConversation : importedConversations){
            conversations.add(importingConversation);
            System.out.println(importingConversation.getInitEpochSeconds());
        }
    }
    
    public void exportConversations() throws IOException {
        repo.exportConversations(conversations);
    }

    public String getIEType() {
        return repo.getIEType();
    }
    
}
