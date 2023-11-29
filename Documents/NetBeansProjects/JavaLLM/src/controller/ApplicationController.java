package controller;

import model.ApplicationModel;
import view.ApplicationView;

public class ApplicationController {
    
    ApplicationView view;
    ApplicationModel model;
    
    public ApplicationController(ApplicationView view, ApplicationModel model){
        this.view = view;
        this.model = model;
    }
    
    public void appInit(){
        view.showApplicationStart("Bienvenido!");
    }
    
    public void showMainMenu(){
        view.showMainMenu();
    }
    
    public void appEnd(){
        view.showApplicationEnd("Adios!");
    }
    
    public void setView(ApplicationView view){
        this.view = view;
    }

    public boolean newConversation() {
        return model.newConversation();
    }

    public String getLLMId() {
        return model.getLLMId();
    }
    
    public String getNewMensaje(String opcion){
        return model.getNewMessage(opcion);
    }
    
}
