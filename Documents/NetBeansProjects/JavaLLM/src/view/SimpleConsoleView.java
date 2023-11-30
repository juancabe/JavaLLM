package view;

import static com.coti.tools.Esdia.*;
import controller.ApplicationController;

public class SimpleConsoleView extends ApplicationView{

    public SimpleConsoleView(ApplicationController controller) {
        super(controller);
    }
   
    @Override
    public void showApplicationStart(String initInfo) {
        out(initInfo);
    }

    @Override
    public boolean showMainMenu() {
        int opcion;
        do{
            String out = "\n\n";
            out = out + "--- jLLM ---\n"
                    + "1) Nueva Conversación\n"
                    + "2) Listar/Eliminar conversaciones\n"
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
                listEliminateConversations();
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
        out(out);
        do{ 
            out = "";
            opcion = readString("Usuario: ");
            if(opcion.toLowerCase().equals("/salir")){
                continue;
            }
            out += controller.getLLMId() + ": ";
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

    private void listEliminateConversations() {
        
        int opcion;
        
        do{
        String out = """
                     ---Eliminar o listar Conversaciones---
                     1) Eliminar Conversaciones
                     2) Listar Conversaciones
                     """;
                    out(out);
                    opcion = readInt("");
        }while(opcion<1 || opcion>2);
        
    }

    private void importExportConversations() {
        
        int opcion;
        
        do{
        String out = """
                     ---Importar o exportar Conversaciones---
                     1) Importar Conversaciones
                     2) Exportar Conversaciones
                     """;
                    out(out);
                    opcion = readInt("");
        }while(opcion<1 || opcion>2);
        
        
    }
    
}
