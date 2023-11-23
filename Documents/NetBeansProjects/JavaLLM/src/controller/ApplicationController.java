package controller;

import view.ApplicationView;

public class ApplicationController {
    
    ApplicationView view;
    
    public ApplicationController(ApplicationView view){
        this.view = view;
    }
    
    public void appInit(){
        view.showApplicationStart("Bienvenido!");
    }
    
    public void showMainMenu(){
        view.showMainMenu();
    }
    
    public void appEnd(){
        view.showApplicationEnd("Adios!");
    }
    
}
