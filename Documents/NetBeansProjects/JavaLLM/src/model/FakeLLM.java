package model;

public class FakeLLM implements ILLM {

    private final String indentifier;

    public FakeLLM() {
        this.indentifier = "FakeLLM";
    }
    
    @Override
    public String speak(String string) {
        return "";
    }

    @Override
    public String getIdentifier() {
        return indentifier;
    }

}
