package javallm;

import view.*;
import model.ApplicationModel;
import controller.ApplicationController;
   

public class JavaLLM {

    public static void main(String[] args) {
        
        ApplicationView view;
        ApplicationController controller;
        ApplicationModel model;
        
        if(args.length != 3){
            return;
        }
        
        // Comprobar argumento repository
        switch (args[0]) {
            case "xml":
                break;
            case "json":
                break;
            default:
                return;
        }
        
        // Comprobar argumento model
        switch (args[1]) {
            case "fake":
                break;
            case "csv":
                break;
            case "smart":
                break;
            default:
                return;
        }
        
        // Comprobar argumento vista
        switch (args[0]) {
            case "consola":
                view = new SimpleConsoleView();
                break;
            case "voz":
                view = new VoiceConsoleView();
                break;
            default:
                return;
        }
    
    }
    
}
