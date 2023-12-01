/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author Usuario
 */
public class ModelCreation {
    
    private ApplicationModel model;
    private Exception ex;

    public ModelCreation(ApplicationModel model, Exception ex){
        this.model = model;
        this.ex = ex;
    }
    
    public ApplicationModel getModel() {
        return model;
    }
    public Exception getEx() {
        return ex;
    }
    
}
