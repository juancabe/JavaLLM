package javallm;

import view.*;
import controller.*;
import model.*;
   

public class JavaLLM {

    public static void main(String[] args) {
        ApplicationModel model = null;
        ApplicationView view = null;
        ApplicationController controller = null;
        
        if(args.length != 3){
            JSONImportExport repo = new JSONImportExport();
            SmartILLM lanModel = new SmartILLM();
            
            model = new ApplicationModel(repo, lanModel);
            view = null;
            controller = new ApplicationController(view, model);
            view = new SimpleConsoleView(controller);
            controller.setView(view);
        }
        else{
            

            ILLM lanModel;
            IRepository repo;


            // Comprobar argumento repository
            switch (args[0]) {
                case "xml":
                    repo = new XMLImportExport();
                    break;
                case "json":
                    repo = new JSONImportExport();
                    break;
                default:
                    repo = new JSONImportExport();
            }

            // Comprobar argumento model
            switch (args[1]) {
                case "fake":
                    lanModel = new FakeLLM();
                    break;
                case "csv":
                    lanModel = new CSVLLM();
                    break;
                case "smart":
                    lanModel = new SmartILLM();
                    break;
                default:
                    lanModel = new SmartILLM();
            }

            model = new ApplicationModel(repo, lanModel);
            controller = new ApplicationController(view, model);

            // Comprobar argumento vista
            switch (args[0]) {
                case "consola":
                    view = new SimpleConsoleView(controller);
                    break;
                case "voz":
                    view = new VoiceConsoleView(controller);
                    break;
                default:
                    view = new SimpleConsoleView(controller);
            }
            controller.setView(view);
        }
        
        view.showApplicationStart("Hola!!!");
        view.showMainMenu();
    
    }
    
}
