package view;

import controller.ApplicationController;
import io.github.jonelo.jAdapterForNativeTTS.engines.SpeechEngine;
import io.github.jonelo.jAdapterForNativeTTS.engines.SpeechEngineNative;
import io.github.jonelo.jAdapterForNativeTTS.engines.Voice;
import io.github.jonelo.jAdapterForNativeTTS.engines.VoicePreferences;
import io.github.jonelo.jAdapterForNativeTTS.engines.exceptions.SpeechEngineCreationException;
import java.io.IOException;
import java.util.List;

public class VoiceConsoleView extends SimpleConsoleView {

    private Voice voice;
    private SpeechEngine speechEngine;

    public VoiceConsoleView(ApplicationController controller) {

        super(controller, true);
        this.voice = configureVoice();
        if (this.voice != null) {
            this.speechEngine.setVoice(voice.getName());
        }

    }

    private Voice configureVoice() {
        try {
            this.speechEngine = SpeechEngineNative.getInstance();
            List<Voice> voices = speechEngine.getAvailableVoices();
            VoicePreferences voicePreferences = new VoicePreferences();
            voicePreferences.setLanguage("es"); //  ISO-639-1
            voice = speechEngine.findVoiceByPreferences(voicePreferences);
            if (voice == null) {
                System.out.println("Aviso: La voz no se ha podido configurar segun las preferencias (en español)");
                voice = voices.get(0);
            }
            return voice;
        } catch (SpeechEngineCreationException e) {
            System.err.println(e.getMessage());
            out("No se pudo configurar la voz.");
        }
        out("No se pudo configurar la voz.");
        return null;
    }

    @Override
    protected void out(String out) {
        System.out.print(out);
        if (this.voice != null) {
            try {
                speechEngine.say(out);
                Thread.sleep(100 * out.length());
            } catch (IOException ex) {
                System.err.println("No pude hablar...");
            } catch (InterruptedException ex) {
                System.err.println("Se interrumpió...");
            }
        }
    }

}
