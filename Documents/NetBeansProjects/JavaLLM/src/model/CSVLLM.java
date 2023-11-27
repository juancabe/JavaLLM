package model;

public class CSVLLM implements ILLM {

    private final String indentifier;

    public CSVLLM() {
        this.indentifier = "CSVLLM";
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
