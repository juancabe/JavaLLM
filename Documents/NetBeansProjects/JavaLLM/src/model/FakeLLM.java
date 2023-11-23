package model;

public class FakeLLM implements ILLM {

    @Override
    public String speak(String string) {
        return null;
    }

    @Override
    public String getIdentifier() {
        return null;
    }

}
