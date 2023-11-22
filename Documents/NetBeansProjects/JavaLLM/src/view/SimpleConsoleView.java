package view;

public class SimpleConsoleView extends ApplicationView{

    @Override
    public void showApplicationStart(String initInfo) {
        extraUtil();
    }

    @Override
    public void showMainMenu() {
        extraUtil();
    }

    @Override
    public void showApplicationEnd(String endInfo) {
        extraUtil();
    }
    
    protected void extraUtil(){
        return;
    }
    
}
