/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.google.gson.GsonBuilder;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Serializable;
import java.lang.reflect.Type;
import java.util.List;

/**
 *
 * @author Usuario
 */
public class JSONImportExport implements IRepository, Serializable{

    private String dirExport;
    private String dirImport;
    
    public JSONImportExport(){
        setFilesVariable();
    }
    
    @Override
    public List<Conversation> importConversations() throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(dirImport))) {
            // Lee el contenido del archivo JSON
            StringBuilder jsonContent = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                jsonContent.append(line);
            }

            // Convierte el JSON a una lista de Conversations
            Gson gson = new Gson();
            Type conversationListType = new TypeToken<List<Conversation>>(){}.getType();
            return gson.fromJson(jsonContent.toString(), conversationListType);
        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    public void exportConversations(List<Conversation> conversations) throws IOException {
        // Implementación para exportar conversaciones a un archivo JSON en dirExport
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        
        try (FileWriter writer = new FileWriter(dirExport)) {
            gson.toJson(conversations, writer);
        } catch (IOException e) {
            throw e;
        }
    }
    
    @Override
    public void setFilesVariable() {
        dirExport = IRepository.exportPathNoExt + "json";
        dirImport = IRepository.importPathNoExt + "json";
    }
    
    @Override
    public String getIEType() {
        return "json";
    }
    
    
}
