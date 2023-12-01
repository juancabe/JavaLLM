package model;

import io.github.amithkoujalgi.ollama4j.core.OllamaAPI;
import io.github.amithkoujalgi.ollama4j.core.exceptions.OllamaBaseException;
import java.io.IOException;
import java.io.Serializable;
public class SmartILLM implements ILLM, Serializable{

    private final String indentifier;
    private final String host = "https://31e9-34-87-148-103.ngrok.io/";
    private OllamaAPI ollamaAPI;

    
    public SmartILLM() {
        this.indentifier = "SmartILLM";
        this.ollamaAPI = new OllamaAPI(host);
        ollamaAPI.setVerbose(true);
    }
    
    @Override
    public String speak(String string) {
        try {
            return ollamaAPI.ask("mistral",string);
        } catch (OllamaBaseException ex) {
            return ex.getMessage() + " - Excepcion OllamaBase";
        } catch (IOException ex) {
            return ex.getMessage() + " - Excepcion IO";
        } catch (InterruptedException ex) {
           return ex.getMessage() + " - Excepcion InterruptedException";
        }
    }

    @Override
    public String getIdentifier() {
        return indentifier;
    }

}
