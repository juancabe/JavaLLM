package model;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class XMLImportExport implements IRepository {
    
    private String dirExport;
    private String dirImport;
    
    @Override
    public List<Conversation> importConversations() {
        ObjectMapper mapper = new XmlMapper();
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        try {
            File file = new File(dirImport);
            return mapper.readValue(file, mapper.getTypeFactory().constructCollectionType(List.class, Conversation.class));
        } catch (IOException e) {
            // Manejar la excepción según tu lógica de manejo de errores
            return null;
        }
    }

    @Override
    public void exportConversation(List<Conversation> conversations) {
        ObjectMapper mapper = new XmlMapper();

        try {
            File file = new File(dirExport);
            mapper.writeValue(file, conversations);
        } catch (IOException e) {
            // Manejar la excepción según tu lógica de manejo de errores
            
        }
    }

    public void setFilesVarible() {
        dirExport = IRepository.exportPathNoExt + "xml";
        dirImport = IRepository.importPathNoExt + "xml";
    }
    
}
