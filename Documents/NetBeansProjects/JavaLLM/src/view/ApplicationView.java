package view;

import controller.ApplicationController;

public abstract class ApplicationView {
    
    ApplicationController controller;
    
    public abstract void showApplicationStart(String initInfo);
    public abstract void showMainMenu();
    public abstract void showApplicationEnd(String endInfo);
}
