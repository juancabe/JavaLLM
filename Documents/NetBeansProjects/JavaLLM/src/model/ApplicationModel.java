package model;

public class ApplicationModel {
    
    private ILLM lanModel;
    private IRepository repo;
    private Conversation conversation;
    
    public ApplicationModel(IRepository repo, ILLM lanModel){
        this.repo = repo;
        this.lanModel = lanModel;
    }

    public boolean newConversation() {
        Conversation conversation = new Conversation(lanModel.getIdentifier());
        return true;
    }
       
}
