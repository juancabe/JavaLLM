package view;

import controller.ApplicationController;

public abstract class ApplicationView {
    
    ApplicationController controller;
    
    abstract void showApplicationStart(String initInfo);
    abstract void showMainMenu();
    abstract void showApplicationEnd(String endInfo);
}
