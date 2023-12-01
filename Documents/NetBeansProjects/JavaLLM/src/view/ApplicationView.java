package view;

import controller.ApplicationController;

public abstract class ApplicationView {
    
    protected ApplicationController controller;
    
    public ApplicationView(ApplicationController controller) {
        this.controller = controller;
    }
    
    public abstract void showApplicationStart(String initInfo, Exception ex);
    public abstract boolean showMainMenu();
    public abstract void showApplicationEnd(String endInfo);
}
