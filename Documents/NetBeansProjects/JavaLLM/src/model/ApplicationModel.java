package model;

import java.util.ArrayList;

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
        conversations.add(this.conversation);
        this.conversation = null;
    }
       
}
