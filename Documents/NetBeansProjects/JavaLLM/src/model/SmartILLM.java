package model;

public class SmartILLM implements ILLM {

    private final String indentifier;

    public SmartILLM() {
        this.indentifier = "SmartILLM";
    }
    
    @Override
    public String speak(String string) {
        return null;
    }

    @Override
    public String getIdentifier() {
        return indentifier;
    }

}
