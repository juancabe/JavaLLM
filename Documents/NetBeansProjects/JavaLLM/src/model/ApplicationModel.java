package model;

public class ApplicationModel {
    
    ILLM lanModel;
    IRepository repo;
    
    public ApplicationModel(IRepository repo, ILLM lanModel){
        this.repo = repo;
        this.lanModel = lanModel;
    }
       
}
