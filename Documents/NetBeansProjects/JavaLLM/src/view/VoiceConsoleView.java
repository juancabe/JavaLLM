package view;

import controller.ApplicationController;

public class VoiceConsoleView extends SimpleConsoleView {

    public VoiceConsoleView(ApplicationController controller) {
        super(controller);
    }
    
    @Override
    protected void out(String out){
        System.out.print(out);
        // Implementación de la TTS
    }
    
}
