package model;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class CSVLLM implements ILLM {

    private final String indentifier;
    private List<Phrase> phrases;
    private final String csvFilePath = System.getProperty("user.home")
            + System.getProperty("file.separator")
            + "Desktop"
            + System.getProperty("file.separator")
            + "jLLM"
            + System.getProperty("file.separator")
            + "input.csv";

    public CSVLLM() {
        this.indentifier = "RandomCSVLLM";
        this.phrases = new ArrayList<>();
        try {
            readCSV(csvFilePath);
        } catch (IOException ex) {
            System.err.println("Error al leer el archivo CSV y guardar las frases");
        }

    }

    @Override
    public String speak(String string) throws Exception {
        if (string == null || string.isEmpty()) {
            // Manejo de caso especial si el parámetro es nulo o vacío
            return getRandomPhrase().getContent();
        }

        if (string.contains("?") ||
            string.contains("cómo") ||
            string.contains("qué") ||
            string.contains("cuándo") ||
            string.contains("dónde") ||
            string.contains("por qué") ||
            string.contains("quién") ||
            string.contains("cuál") ||
            string.contains("cuánto") ||
            string.contains("cuáles")) {
            
            return getRandomPhrase("respuesta").getContent();
            
        } else if (string.endsWith("!") ||
            string.contains("increíble") ||
            string.contains("sorprendente") ||
            string.contains("asombroso") ||
            string.contains("wow") ||
            string.contains("impresionante")) {
            
            return getRandomPhrase("sorpresa").getContent();
            
        } else if (string.equalsIgnoreCase("adios") ||
            string.equalsIgnoreCase("chao") ||
            string.equalsIgnoreCase("hasta luego") ||
            string.equalsIgnoreCase("hasta pronto") ||
            string.equalsIgnoreCase("nos vemos") ||
            string.equalsIgnoreCase("hasta la vista") ||
            string.equalsIgnoreCase("bye") ||
            string.equalsIgnoreCase("hasta luego")) {
            
            return getRandomPhrase("despedida").getContent();
            
        } else if (string.equalsIgnoreCase("hola") ||
            string.equalsIgnoreCase("saludo") ||
            string.equalsIgnoreCase("buenos dias") ||
            string.equalsIgnoreCase("buenas tardes") ||
            string.equalsIgnoreCase("buenas noches") ||
            string.equalsIgnoreCase("hi") ||
            string.equalsIgnoreCase("hello") ||
            string.equalsIgnoreCase("greetings")) {
            
            return getRandomPhrase("saludo").getContent();
            
        } else if(string.contains("refran")){
            
            return getRandomPhrase("refran").getContent();
            
        }
        else {
            return getRandomPhrase().getContent();
        }

    }

    @Override
    public String getIdentifier() {
        return indentifier;
    }

    private void readCSV(String filePath) throws IOException {

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            reader.readLine(); // Saltamos la primera linea del archivo
            while ((line = reader.readLine()) != null) {
                List<String> values = splitString(line, ',');
                if (values.size() == 3) {
                    try {
                        if (values.get(2).length() != Integer.parseInt(values.get(1))) {
                            throw new NoCoincidenceException("La longitud especificada no coincide con la longitud real.");
                        }
                        phrases.add(new Phrase(values.get(0), Integer.parseInt(values.get(1)), values.get(2)));
                    } catch (NumberFormatException | NoCoincidenceException ex) {
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

}
