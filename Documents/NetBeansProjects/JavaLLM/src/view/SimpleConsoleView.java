package view;

import static com.coti.tools.Esdia.readInt;
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
    public void showMainMenu() {
        int opcion;
        do{
            String out = "\n\n";
            out = out + "--- jLLM ---\n"
                    + "1) Nueva Conversación\n"
                    + "2) Listar/Eliminar conversaciones\n"
                    + "3) Importar/Exportar conversaciones\n"
                    + "Introduzca una opción: ";
            out(out);
            opcion = readInt("");
        }while(opcion<1 || opcion>3);
        
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
    }

    @Override
    public void showApplicationEnd(String endInfo) {
        out(endInfo);
    }
    
    protected void out(String out){
        System.out.print(out);
    }

    private void newConversation() {
        String out;
        controller.newConversation();
        out = "\n\n---Nueva Conversacion---\n";
        out(out);
        
        
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
