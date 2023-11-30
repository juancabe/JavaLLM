package model;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class CSVLLM implements ILLM {

    private final String indentifier;

    public CSVLLM() {
        this.indentifier = "RandomCSVLLM";
    }
    
    @Override
    public String speak(String string) throws Exception {
        return getRandomStrFromCsv();
    }

    @Override
    public String getIdentifier() {
        return indentifier;
    }

    private String getRandomStrFromCsv() throws Exception {
        String csvFilePath = "DataFiles/input.csv"; // Reemplaza con la ruta correcta de tu archivo CSV

        List<String> phrases = readCSV(csvFilePath);
        if (!phrases.isEmpty()) {
            return getRandomPhrase(phrases);
        }
        
        return "";
        
    }
    
    private static List<String> readCSV(String filePath) throws IOException {
        List<String> phrases = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                // Dividir la línea en valores usando la coma como separador
                String[] values = line.split(",");
                if (values.length == 3) {
                    phrases.add(values[2].trim());
                }
                if(values.length > 3){
                    values[2] += "," + values[3];
                    values[2] = values[2].replace("\"","");
                    phrases.add(values[2].trim());
                }
            }
        }
        
        return phrases;
    }
    
    private static String getRandomPhrase(List<String> phrases) throws Exception {
        if (phrases.isEmpty()) {
            throw new Exception("No phrases, line 64 model.CSVLLM");
        }

        Random random = new Random();
        int randomIndex = random.nextInt(phrases.size());

        return phrases.get(randomIndex);
    }



}
