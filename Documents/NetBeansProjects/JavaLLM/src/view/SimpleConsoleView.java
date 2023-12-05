package view;

import static com.coti.tools.Esdia.*;
import controller.ApplicationController;
import java.io.IOException;

public class SimpleConsoleView extends ApplicationView{

    public SimpleConsoleView(ApplicationController controller) {
        super(controller);
    }
   
    @Override
    public void showApplicationStart(String initInfo, Exception ex) {
        out(initInfo);
        if(ex != null){
            System.err.println("Error: " + ex.getMessage());
        }
    }

    @Override
    public boolean showMainMenu() {
        int opcion;
        do{
            String out = "\n\n";
            out = out + "--- jLLM ---\n"
                    + "1) Nueva Conversación\n"
                    + "2) Eliminar/Listar/Continuar conversaciones\n"
                    + "3) Importar/Exportar conversaciones\n"
                    + "4) Salir\n"
                    + "Introduzca una opción: ";
            out(out);
            opcion = readInt("");
            if(opcion == 4){
                return false;
            }
        }while(opcion<1 || opcion>4);
        
        switch(opcion){
            case 1:
                newConversation();
                break;
            case 2:
                listContinueEliminateConversations();
                break;
            case 3:
                importExportConversations();
                break;
            default:
                String out = "Error inesperado\n";
                out(out);
                showMainMenu();
        }
        return true;
    }

    @Override
    public void showApplicationEnd(String endInfo) {
        out(endInfo);
    }
    
    protected void out(String out){
        System.out.print(out);
    }

    private void newConversation() {
        String opcion ;
        String out;
        controller.newConversation();
        out = "\n\n---Nueva Conversacion---\n";
        out += "Puede empezar a hablar ahora!\n";
        out(out);
        do{ 
            out = "";
            opcion = readString("Usuario: ");
            if(opcion.toLowerCase().equals("/salir")){
                continue;
            }
            out += controller.getLLMId().split(":")[0] + ": ";
            try{
                out += controller.getNewMensaje(opcion) + "\n";
            } catch (Exception e) {
                out += "Lo siento, no se que decir. Ha fallado algo.";
                System.err.println(e.getMessage());
            }
            out(out);
            
        }while(!opcion.toLowerCase().equals("/salir"));
        
        controller.endConversation();
        
    }

    private void listContinueEliminateConversations() {
        
        int opcion;
        
        do{
        String out = """
                     
                     
                     ---Eliminar o listar Conversaciones---
                     1) Eliminar Conversaciones
                     2) Listar Conversaciones
                     3) Continuar conversación
                     Introduzca una opción: 
                     """;
                    out(out);
                    opcion = readInt("");
        }while(opcion<1 || opcion>3);
        
        switch (opcion) {
            case 1:
                eliminateConversation();
                break;
            case 2:
                {
                    String out = listConversations();
                    out(out);
                    break;
                }
            case 3:
                {
                    continueConversation();
                    break;
                }
            default:
                break;
        }
        
    }
    
    private void eliminateConversation(){
        String out;
        if(controller.getNumOfConversations() < 1){
            out = "No hay conversaciones disponibles";
            out(out);
        } else{
            out = "Conversaciones disponibles para eliminar: 1-" 
                + Integer.toString(controller.getNumOfConversations())
                + "\nIngrese la que quiere eliminar (0 para salir): ";
            out(out);
            int opcion;
            do{
                opcion = readInt("");
            }while(opcion < 0 || opcion > controller.getNumOfConversations());
            
            if(opcion == 0){
                out = "Saliendo...\n";
                out(out);
                return;
            }
            controller.eliminateConversation(opcion);
            out = "Conversación eliminada con éxito!\n";
            out(out);
        }
    }
    
    private String listConversations(){
        
        String out = "\n\n";
        
        if(controller.getNumOfConversations() == 0){
            out = "No hay conversaciones disponibles!";
            return out;
            
        }
        
        for(int i = 0; i < controller.getNumOfConversations(); i++){
            out += controller.getConversationInitTime(i) + " | ";
            out += String.format("%2d", controller.getConversationNumMessages(i)) + " | ";
            out += controller.getConversationFirst20Char(i) + "\n";
        }
        out += "\n";
        return out;
        
    }
    
    private void continueConversation(){
        
        int count = 0, conversations = 0;
        String out = "\n\n";
        
        for(int i = 0; i < controller.getNumOfConversations(); i++){
            
            if(controller.getConversationLLM(i).equals(controller.getActualConversationLLM())){
                
                conversations++;
                
                
            }
        }
        if(conversations == 0){
            out("No hay conversaciones disponibles!");
            return;
        }
        
        out("\n\nConversaciones disponibles: \n");
        
        for(int i = 0; i < controller.getNumOfConversations(); i++){
            
            if(controller.getConversationLLM(i).equals(controller.getActualConversationLLM())){
                out += String.format("%2d) ", ++count);
                out += controller.getConversationInitTime(i) + " | ";
                out += controller.getConversationNumMessages(i) + " | ";
                out += controller.getConversationFirst20Char(i) + "\n";
            }
            
        }
        out += "\n";
        out(out);
        
        out = "Conversaciones disponibles para continuar: 1-" 
                + Integer.toString(count)
                + "\nIngrese la que quiere eliminar (0 para salir): ";
        out(out);
        int opcion;
        do{
            opcion = readInt("");
        }while(opcion < 0 || opcion > count);
        
        if(opcion == 0){
            out = "Saliendo...\n";
            out(out);
            return;
        }
        int conversation = -1;
        for(int i = 0; i < controller.getNumOfConversations(); i++){
            
            if(controller.getConversationLLM(i).equals(controller.getActualConversationLLM())){
                
                if(--count == 0){
                    conversation = i;
                }
                
            }
        }
        
        if(conversation == -1){
            out("Error inesperado al continuar la conversación");
            return;
        } 
        
        controller.continueConversation(conversation);
        out(controller.returnFullActualConversation());
        String msg;
        do{ 
            out = "";
            msg = readString("Usuario: ");
            if(msg.toLowerCase().equals("/salir")){
                continue;
            }
            out += controller.getLLMId().split(":")[0] + ": ";
            try{
                out += controller.getNewMensaje(msg) + "\n";
            } catch (Exception e) {
                out += "Lo siento, no sé qué decir. Ha fallado algo.";
                System.err.println(e.getMessage());
            }
            out(out);
            
        }while(!msg.toLowerCase().equals("/salir"));
        
        controller.endConversation();        
        
    }
    
    

    private void importExportConversations() {
        
        int opcion;
        
        do{
        String out = """
                     
                     
                     ---Importar o exportar Conversaciones---
                     1) Importar Conversaciones
                     2) Exportar Conversaciones
                     Introduzca su opción: 
                     """;
                    out(out);
                    opcion = readInt("");
        }while(opcion<1 || opcion>2);
        
        if(opcion == 1){
            importConversations();
        }
        else{
            exportConversations();
        }
        
        
    }

    private void importConversations() {
        String out = "Importando conversaciones...\n";
        try {
            controller.importConversations();
            out += "Importación realizada con éxito.\n\n";
            out(out);
        } catch (IOException ex) {
            out += "Error en la importación de las conversaciones.\n";
            out += "Compruebe la existencia del archivo input."
                    + controller.getIEType()
                    + " en una carpeta llamada jLLM en su escritorio.\n\n";
            out(out);
            System.err.println("Mensaje de error: " + ex.getMessage());
        }
    }

    private void exportConversations() {
        String out = "Exportando conversaciones...\n";
        try {
            controller.exportConversations();
            out += "Exportación realizada con éxito.\n\n";
            out(out);
        } catch (IOException ex) {
            out += "Error en la exportación de las conversaciones\n";
            out += "¿Existe la carpeta jLLM en su escritorio?";
            out(out);
            System.err.println("Mensaje de error: " + ex.getMessage());
        }
    }
    
}
