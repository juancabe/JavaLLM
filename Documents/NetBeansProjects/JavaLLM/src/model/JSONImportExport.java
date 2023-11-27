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
import java.lang.reflect.Type;
import java.util.List;

/**
 *
 * @author Usuario
 */
public class JSONImportExport implements IRepository{

    private String dirExport;
    private String dirImport;
    
    @Override
    public List<Conversation> importConversations() {
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
            // Manejar la excepción de manera apropiada en tu aplicación
        }
        return null; // O manejar el retorno de manera adecuada si la lectura falla
    }

    @Override
    public void exportConversation(List<Conversation> conversations) {
        // Implementación para exportar conversaciones a un archivo JSON en dirExport
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        
        try (FileWriter writer = new FileWriter(dirExport)) {
            gson.toJson(conversations, writer);
        } catch (IOException e) {
            // Manejar la excepción de manera apropiada en tu aplicación
        }
    }
    
    @Override
    public void setFilesVarible() {
        dirExport = IRepository.exportPathNoExt + "xml";
        dirImport = IRepository.importPathNoExt + "xml";
    }
    
    
}
