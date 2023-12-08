package view;

import static com.coti.tools.Esdia.*;
import controller.ApplicationController;
import java.io.IOException;
import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SimpleConsoleView extends ApplicationView {

    public SimpleConsoleView(ApplicationController controller) {
        super(controller);
    }

    @Override
    public void showApplicationStart(String initInfo, Exception ex) {
        out(initInfo);
        if (ex != null) {
            System.err.println("Error: " + ex.getMessage());
        }
    }

    @Override
    public boolean showMainMenu() {
        int opcion;
        do {
            String out = "\n\n";
            out = out + "--- jLLM ---\n"
                    + "1) Nueva Conversación\n"
                    + "2) Eliminar/Listar/Continuar conversaciones\n"
                    + "3) Importar/Exportar conversaciones\n"
                    + "4) Salir\n"
                    + "Introduzca una opción: ";
            out(out);
            opcion = readInt("");
            if (opcion == 4) {
                return false;
            }
        } while (opcion < 1 || opcion > 4);

        switch (opcion) {
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

    protected void out(String out) {
        System.out.print(out);
    }

    private void conversate() {

        String opcion;
        String out;

        do {
            out = "";
            Instant instant = Instant.now();
            ZonedDateTime zonedDateTime = ZonedDateTime.ofInstant(instant, ZoneId.systemDefault());
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("[dd/MM/yy: HH:mm:ss]");

            out("Usuario [" + zonedDateTime.format(formatter) + "]: ");
            opcion = readString("");

            if (!opcion.toLowerCase().equals("/salir")) {

                out += controller.getLLMId().split(":")[0];
                try {
                    out += " " + controller.getNewMensaje(opcion, instant) + "\n";
                } catch (Exception e) {
                    out += "Lo siento, no se que decir. Ha fallado algo.";
                    System.err.println(e.getMessage());
                }
                out(out);
            }

        } while (!opcion.toLowerCase().equals("/salir"));

    }

    private void newConversation() {
        String out;
        controller.newConversation();

        out = "\n\n---Nueva Conversacion---\n";
        out += "Puede empezar a hablar ahora!\n";
        out(out);

        conversate();

        controller.endConversation();

    }

    private void listContinueEliminateConversations() {

        int opcion;

        do {
            String out = """
                     
                     
                     ---Eliminar o listar Conversaciones---
                     1) Eliminar Conversaciones
                     2) Listar Conversaciones
                     3) Continuar conversación
                     Introduzca una opción: 
                     """;
            out(out);
            opcion = readInt("");
        } while (opcion < 1 || opcion > 3);

        switch (opcion) {
            case 1:
                eliminateConversation();
                break;
            case 2: {
                listConversations();
                break;
            }
            case 3: {
                continueConversation();
                break;
            }
            default:
                break;
        }

    }

    private void eliminateConversation() {
        String out;
        
        try {
            out(getListOfConversations());
        } catch (NoConversationException ex) {
            out("No hay conversaciones disponibles para eliminar.\nSaliendo...\n");
        }
        
        out("\nIngrese la que quiere eliminar (0 para salir): ");
        int opcion;
        do {
            opcion = readInt("");
        } while (opcion < 0 || opcion > controller.getNumOfConversations());

        if (opcion == 0) {
            out = "Saliendo...\n";
            out(out);
            return;
        }
        controller.eliminateConversation(opcion);
        out = "Conversación eliminada con éxito!\n";
        out(out);
        
    }
    
    private String getListOfConversations() throws NoConversationException{
        
        String out = "";
        
        if (controller.getNumOfConversations() == 0) {
            throw new NoConversationException("No hay conversaciones en función listConversationsAskForNumber");
        }

        for (int i = 0; i < controller.getNumOfConversations(); i++) {

            out += String.format("%2d) ", i + 1);
            out += controller.getConversationInitTime(i) + " | ";
            out += controller.getConversationNumMessages(i) + " | ";
            out += controller.getConversationFirst20Char(i) + "\n";
        }
        
        return out;
        
    }

    private int listConversationsAskForNumber(String askForNumber) throws NoConversationException {

        String out = "\n\n";
        out(out);
        
        out(getListOfConversations());        

        out += "\n";
        out(out);

        out = "Conversaciones disponibles:" +"1-"
                + controller.getNumOfConversations()
                + "\n" + askForNumber;
        out(out);
        int option;
        do {
            option = readInt("");
        } while (option < 0 || option > controller.getNumOfConversations());

        return option;

    }

    private void listConversations() {

        int option;
        try {
            option = listConversationsAskForNumber("Ingrese la que quiere mostrar en detalle (0 para salir): ");
        } catch (NoConversationException e) {
            out("No hay conversaciones disponibles!");
            return;
        }
        
        if (option == 0) {
            out("Saliendo...\n");
            return;
        }
        
        controller.continueConversation(option-1);
        // Pedir la conversación en forma de String
        out(controller.returnFullActualConversation());

        controller.endConversation();

    }

    private void continueConversation() {
        int option;
        try {
            option = listConversationsAskForNumber("Ingrese la que quiere continuar en detalle (0 para salir): ");
        } catch (NoConversationException e) {
            out("No hay conversaciones disponibles!");
            return;
        }

        if (option == 0) {
            out("Saliendo...\n");
            return;
        }

        // Indicar que iniciamos una nueva conversación
        controller.continueConversation(option-1);
        // Pedir la conversación en forma de String
        out(controller.returnFullActualConversation());

        conversate();

        controller.endConversation();

    }

    private void importExportConversations() {

        int opcion;

        do {
            String out = """
                     
                     
                     ---Importar o exportar Conversaciones---
                     1) Importar Conversaciones
                     2) Exportar Conversaciones
                     Introduzca su opción: 
                     """;
            out(out);
            opcion = readInt("");
        } while (opcion < 1 || opcion > 2);

        if (opcion == 1) {
            importConversations();
        } else {
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
