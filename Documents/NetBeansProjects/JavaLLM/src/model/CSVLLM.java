package model;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import static model.SHA1Calculator.calculateSHA1;

public class CSVLLM implements ILLM, Serializable {

    private final String indentifier;
    private List<Phrase> phrases;
    private final String csvFilePath = "DataFiles/input.csv";
    private String fileHash;

    public CSVLLM() {
        this.indentifier = "RandomCSVLLM";
        this.phrases = new ArrayList<>();
        try {
            readCSV(csvFilePath);
        } catch (IOException ex) {
            System.err.println("Error al leer el archivo CSV y guardar las frases");
        }
        this.fileHash = getCSVFileSHA1(this.csvFilePath);
        
    }
    
    @Override
    public String speak(String string) throws Exception {
        if (string == null || string.isEmpty()) {
            // Manejo de caso especial si el parámetro es nulo o vacío
            return getRandomPhrase().getContent();
        }

        // Filtros para determinar el tipo de respuesta
        if (string.contains("?")) {
            // Pregunta
            return getRandomPhrase("pregunta").getContent();
        } else if (string.endsWith("!")) {
            // Sorpresa
            return getRandomPhrase("sorpresa").getContent();
        } else if (string.equalsIgnoreCase("adios") || string.equalsIgnoreCase("chao")) {
            // Despedida
            return getRandomPhrase("despedida").getContent();
        } else if (string.equalsIgnoreCase("hola") || string.equalsIgnoreCase("saludo")) {
            // Saludo
            return getRandomPhrase("saludo").getContent();
        } else {
            // Respuesta por defecto para otros casos
            return getRandomPhrase().getContent();
        }
    }


    @Override
    public String getIdentifier() {
        return indentifier + ":" + fileHash;
    }
    
    private void readCSV(String filePath) throws IOException {
        
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                List<String> values = splitString(line, ',');
                if (values.size() == 3) {
                    try{
                        if(values.get(2).length() != Integer.parseInt(values.get(1))){
                            throw new NoCoincidenceException("La longitud especificada no coincide con la longitud real.");
                        }
                        phrases.add(new Phrase(values.get(0), Integer.parseInt(values.get(1)), values.get(2)));
                    } catch (NumberFormatException | NoCoincidenceException ex){
                        System.err.println("Línea del CSV ignorada: " + ex.getMessage());
                    }
                }
                
            }
        } catch (IOException ex) {
            throw ex;
        }
    }
    
    private static List<String> splitString(String input, char delimiter) {
        List<String> tokens = new ArrayList<>();
        boolean insideQuotes = false;
        StringBuilder currentToken = new StringBuilder();

        for (char c : input.toCharArray()) {
            if (c == '"') {
                insideQuotes = !insideQuotes;
            } else if (c == delimiter && !insideQuotes) {
                tokens.add(currentToken.toString().trim());
                currentToken = new StringBuilder();
            } else {
                currentToken.append(c);
            }
        }

        // Agregar el último token después del último delimitador
        tokens.add(currentToken.toString().trim());

        return tokens;
    }
    
    private Phrase getRandomPhrase() throws Exception {
        if (phrases.isEmpty()) {
            throw new Exception("No phrases read, line 64 model.CSVLLM");
        }

        Random random = new Random();
        int randomIndex = random.nextInt(phrases.size());
        return phrases.get(randomIndex);
    }
    
    private Phrase getRandomPhrase(String tipo) throws Exception {
        if (phrases.isEmpty()) {
            throw new Exception("No phrases read, line 64 model.CSVLLM");
        }

        List<Phrase> filteredPhrases = phrases.stream()
                .filter(phrase -> phrase.getType().equalsIgnoreCase(tipo))
                .collect(Collectors.toList());

        if (filteredPhrases.isEmpty()) {
            throw new Exception("No phrases found for type: " + tipo);
        }

        Random random = new Random();
        int randomIndex = random.nextInt(filteredPhrases.size());
        return filteredPhrases.get(randomIndex);
    }

    
    public static String getCSVFileSHA1(String csvFilePath){
        try {
            return calculateSHA1(csvFilePath);
        } catch (Exception ex) {
            System.err.println("Error al hashear el archivo CSV");
            return null;
        }
    }

    public String getFileHash() {
        return fileHash;
    }



}
