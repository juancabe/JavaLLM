package model;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class ApplicationModel implements Serializable {
    
    static final transient String binLocation = System.getProperty("user.home")
            + System.getProperty("file.separator")
            + "Desktop"
            + System.getProperty("file.separator")
            + "jLLM"
            + System.getProperty("file.separator")
            + "jLLM.bin";;
    private transient ILLM lanModel;
    private transient IRepository repo;
    private transient Conversation conversation;
    private ArrayList<Conversation> conversations;

    /*
    
    Constructor que intenta deserializar archivo y puede lanzar excepcion
    
     */
    private ApplicationModel(IRepository repo, ILLM lanModel) throws IOException, ClassNotFoundException, NoCoincidenceException {

        try {
            ApplicationModel model = loadModelStatus();
            this.repo = repo;
            this.lanModel = lanModel;
            this.conversations = model.conversations;

        } catch (IOException | ClassNotFoundException ex) {
            throw ex;
        }

    }

    /*
    
    Constructor que no intenta deserializar archivo, no lanza excepcion
    
     */
    public ApplicationModel(ILLM lanModel, IRepository repo) {
        this.lanModel = lanModel;
        this.repo = repo;
        this.conversations = new ArrayList<>();
    }

    /*
    
    Funcion para llamar a los constructores y devolver un model

     */
    public static ModelCreation crearInstancia(IRepository repo, ILLM lanModel) {

        try {
            ApplicationModel model = new ApplicationModel(repo, lanModel);
            return new ModelCreation(model, null);

        } catch (IOException | ClassNotFoundException | NoCoincidenceException ex) {
            ApplicationModel model = new ApplicationModel(lanModel, repo);
            return new ModelCreation(model, ex);
        }
    }

    public boolean newConversation() {
        conversation = new Conversation(lanModel.getIdentifier());
        return true;
    }

    public String getNewMessage(String opcion, Instant instant) throws Exception {

        String modelMessage = lanModel.speak(opcion);
        String toPrint;
        Instant modelInstant = Instant.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("[dd/MM/yy: HH:mm:ss]");
        ZonedDateTime zonedDateTime = ZonedDateTime.ofInstant(modelInstant, ZoneId.systemDefault());

        conversation.addMessage("Usuario", instant, opcion);
        conversation.addMessage(lanModel.getIdentifier(), modelInstant, modelMessage);

        toPrint = "[" + zonedDateTime.format(formatter) + "]: " + modelMessage;

        return toPrint;
    }

    public String getLLMId() {
        return conversation.getLLMIdentifier();
    }

    public void endConversation() {
        if(!this.conversation.getMessagesArray().isEmpty()){
            this.conversation.setEndEpoch();
            conversations.add(this.conversation);
        }
        this.conversation = null;
    }

    public int getNumOfConversations() {
        return conversations.size();
    }

    public String getConversationInitTime(int i) {
        return Double.toString(conversations.get(i).getInitEpochSeconds());
    }

    public int getConversationNumMessages(int i) {
        return conversations.get(i).getMessagesArray().size();
    }

    public String getConversationFirst20Char(int i) {
        String str = String.format("%20s", conversations.get(i).getMessagesArray().get(0).getContent());
        return str.substring(0, 20);
    }

    public void eliminateConversation(int opcion) {
        conversations.remove(opcion - 1);
    }

    public void importConversations() throws IOException {
        List<Conversation> importedConversations = repo.importConversations();
        for (Conversation importingConversation : importedConversations) {
            conversations.add(importingConversation);
        }
    }

    public void exportConversations() throws IOException {
        repo.exportConversations(conversations);
    }

    public String getIEType() {
        return repo.getIEType();
    }

    public void saveModelStatus() throws IOException {
        try (FileOutputStream fileOutputStream = new FileOutputStream(binLocation); ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream)) {
            objectOutputStream.writeObject(this);
        }
    }

    public static ApplicationModel loadModelStatus() throws IOException, ClassNotFoundException {
        try (FileInputStream fileInputStream = new FileInputStream(binLocation); ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream)) {
            return (ApplicationModel) objectInputStream.readObject();
        }
    }

    public void appEnd() throws IOException {
        try {
            saveModelStatus();
        } catch (IOException ex) {
            throw ex;
        }
    }

    public String getConversationLLM(int i) {
        return conversations.get(i).getLLMIdentifier();
    }

    public String getActualConversationLLM() {
        return lanModel.getIdentifier();
    }

    public void continueConversation(int conversation) {
        this.conversation = conversations.get(conversation);
        conversations.remove(conversation);
    }

    public String returnFullActualConversation() {
        String out = "";

        List<Message> messages = conversation.getMessagesArray();

        for (Message message : messages) {
            out += message.getSender() + " [" + message.getEpochAsDate() + "]: " + message.getContent() + "\n";
        }

        return out;
    }
}
