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
    
    public void showMainMenu(){
        view.showMainMenu();
    }
    
    public void appEnd(){
        
        try {
            model.appEnd();
            view.showApplicationEnd("Guardado correcto, adiós!\n");
        } catch (IOException ex) {
            view.showApplicationEnd("""
                                    Error al guardar el estado de ejecuci\u00f3n.
                                    La pr\u00f3xima vez que abra la aplicaci\u00f3n empezar\u00e1 de cero.
                                    """);
            System.err.println("Mensaje de error: " + ex.getMessage());
        }
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

    public String getConversationLLM(int i) {
        return model.getConversationLLM(i);
    }

    public String getActualConversationLLM() {
        return model.getActualConversationLLM();
    }

    public void continueConversation(int conversation) {
        model.continueConversation(conversation);
    }

    public String returnFullActualConversation() {
        return model.returnFullActualConversation();
    }
    
}
    
