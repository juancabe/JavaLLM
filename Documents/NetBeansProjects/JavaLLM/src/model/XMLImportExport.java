package model;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.List;

public class XMLImportExport implements IRepository, Serializable {
    
    private String dirExport;
    private String dirImport;
    
    public XMLImportExport(){
        setFilesVariable();
    }
    
    @Override
    public List<Conversation> importConversations() throws IOException {
        ObjectMapper mapper = new XmlMapper();
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        try {
            File file = new File(dirImport);
            return mapper.readValue(file, mapper.getTypeFactory().constructCollectionType(List.class, Conversation.class));
        } catch (IOException e) {
            throw e;
        }
    }

    @Override
    public void exportConversations(List<Conversation> conversations) throws IOException {
        ObjectMapper mapper = new XmlMapper();

        try {
            File file = new File(dirExport);
            mapper.writeValue(file, conversations);
        } catch (IOException e) {
            throw e;
        }
    }

    @Override
    public void setFilesVariable() {
        dirExport = IRepository.exportPathNoExt + "xml";
        dirImport = IRepository.importPathNoExt + "xml";
    }
    
    @Override
    public String getIEType() {
        return "xml";
    }
    
}
