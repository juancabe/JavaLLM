package view;

public class VoiceConsoleView extends SimpleConsoleView {
    
    @Override
    protected void out(String out){
        System.out.print(out);
        // Implementación de la TTS
    }
    
}
