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
                nuevaConversacion();
                break;
            case 2:
                listarEliminarConversaciones();
                break;
            case 3:
                importarExportarConversaciones();
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

    private void nuevaConversacion() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    private void listarEliminarConversaciones() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    private void importarExportarConversaciones() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
}
