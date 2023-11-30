package controller;

import java.io.IOException;
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
    
    public String getNewMensaje(String opcion) throws Exception{
        return model.getNewMessage(opcion);
    }

    public void endConversation() {
        model.endConversation();
    }

    public int getNumOfConversations() {
        return model.getNumOfConversations();
    }

    public String getConversationInitTime(int i) {
        return model.getConversationInitTime(i);
    }

    public String getConversationNumMessages(int i) {
        return model.getConversationNumMessages(i);
    }

    public String getConversationFirst20Char(int i) {
        return model.getConversationFirst20Char(i);
    }

    public void eliminateConversation(int opcion) {
        model.eliminateConversation(opcion);
    }
    
    public void importConversations() throws IOException{
        model.importConversations();
    }
    
    public void exportConversations() throws IOException{
        model.exportConversations();
    }

    public String getIEType() {
        return model.getIEType();
    }
    
}
    
