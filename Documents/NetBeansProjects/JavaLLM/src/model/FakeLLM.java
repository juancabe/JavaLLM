package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class FakeLLM implements ILLM, Serializable {

    private final String indentifier;
    private HashMap<String, String> inputOutput;
    private ArrayList<String> defaultOutput;

    public FakeLLM() {
        this.indentifier = "FakeLLM";
        this.inputOutput = new HashMap<>();
        this.defaultOutput = new ArrayList<>();
        generateInputOutput();
        generateDefaultOutput();
    }
    
    @Override
    public String speak(String string) {
        for (String key : inputOutput.keySet()) {
            if (string.toLowerCase().contains(key.toLowerCase())) {
                return inputOutput.get(key);
            }
        }
        Random random = new Random();
        return defaultOutput.get(random.nextInt(defaultOutput.size()));
    }

    @Override
    public String getIdentifier() {
        return indentifier;
    }

    private void generateInputOutput() {
        
        inputOutput.put("hola", "Buenas! ¿Qué tal el día?");
        inputOutput.put("bien", "Me alegro!");
        inputOutput.put("buenos", "Buenas! ¿Qué tal el día?");
        inputOutput.put("llamas", "Me llamo FakeLLM! Encantado de conocerte!");
        inputOutput.put("chiste", "¿Cuál es el idioma de las tortugas? El tortugués.");
        inputOutput.put("adios", "Hasta luego. ¡Que tengas un buen día!");
        inputOutput.put("gracias", "De nada. Siempre estoy aquí para ayudar.");
        inputOutput.put("nombre", "Mi nombre es FakeLLM, ¿y el tuyo?");
        inputOutput.put("tiempo", "El tiempo está variable hoy, ¿no?");
        inputOutput.put("programacion", "¡Me encanta la programación! ¿En qué puedo ayudarte?");
        inputOutput.put("noticias", "No tengo acceso a las noticias, pero puedo contarte un chiste si quieres.");
        inputOutput.put("edad", "No tengo edad, ¡soy una inteligencia artificial!");
        inputOutput.put("musica", "La música siempre alegra el día, ¿tienes algún género favorito?");
        inputOutput.put("triste", "Lo siento escuchar eso. ¿Puedo ayudarte de alguna manera?");
        inputOutput.put("feliz", "¡Me alegra escuchar que estás feliz! ¿Hay algo en particular que quieras compartir?");
        inputOutput.put("viaje", "Viajar es una experiencia maravillosa. ¿Tienes algún destino en mente?");
        inputOutput.put("tecnologia", "La tecnología avanza rápidamente. ¿Hay alguna novedad que te interese?");
        inputOutput.put("deporte", "El deporte es una excelente forma de mantenerse activo. ¿Tienes algún deporte favorito?");
        inputOutput.put("cafe", "El café es mi combustible favorito. ¿Cómo prefieres tu café?");
        inputOutput.put("libro", "¿Has leído algún buen libro últimamente? ¡Me encantaría conocer tus recomendaciones!");
        inputOutput.put("sueño", "El sueño es importante para el bienestar. ¿Has tenido sueños interesantes recientemente?");
        inputOutput.put("educacion", "La educación es clave para el crecimiento personal. ¿Estás aprendiendo algo nuevo?");
        inputOutput.put("cine", "¿Te gusta el cine? ¿Tienes alguna película favorita?");
        inputOutput.put("salud", "La salud es lo más importante. ¿Cómo te encuentras hoy?");
        inputOutput.put("naturaleza", "La naturaleza tiene una belleza única. ¿Disfrutas pasar tiempo al aire libre?");
        inputOutput.put("ciencia", "La ciencia es fascinante. ¿Hay algún tema científico que te intrigue?");
        inputOutput.put("futuro", "El futuro es emocionante. ¿Tienes metas o sueños que te gustaría alcanzar?");
        inputOutput.put("computadoras", "Las computadoras son increíbles. ¿Tienes alguna pregunta técnica?");
        inputOutput.put("viaje espacial", "Explorar el espacio es fascinante. ¿Te gustaría viajar al espacio algún día?");
        inputOutput.put("arte", "El arte tiene el poder de inspirar. ¿Tienes alguna forma de arte favorita?");
        inputOutput.put("moda", "La moda es una expresión única. ¿Sigues alguna tendencia de moda?");
        inputOutput.put("aventura", "La vida es una gran aventura. ¿Has tenido alguna experiencia emocionante últimamente?");
        inputOutput.put("mascotas", "Las mascotas son maravillosas compañeras. ¿Tienes alguna?");
        inputOutput.put("meditacion", "La meditación puede ser beneficiosa. ¿Has probado alguna vez la meditación?");

    }

    private void generateDefaultOutput() {

        this.defaultOutput.add("Lo siento, no entendí. ¿Podrías reformular?");
        this.defaultOutput.add("No estoy seguro de cómo responder a eso. ¿Puedes darme más información?");
        this.defaultOutput.add("Interesante, pero no tengo una respuesta específica para eso.");
        this.defaultOutput.add("Hmm, eso es intrigante. ¿Hay algo más de lo que te gustaría hablar?");
        this.defaultOutput.add("No tengo información sobre ese tema en particular.");
        this.defaultOutput.add("Mis disculpas, no tengo una respuesta predefinida para esa entrada.");
        this.defaultOutput.add("¿Podrías ser más específico? Estoy aquí para ayudar en lo que pueda.");
        this.defaultOutput.add("Esa no es una entrada que esperaba. ¿Puedes proporcionar más contexto?");
        this.defaultOutput.add("Parece que mi programación no tiene una respuesta para eso. ¿Hay algo más en lo que pueda ayudarte?");
        this.defaultOutput.add("No tengo conocimiento sobre ese tema en este momento.");
        this.defaultOutput.add("¡Vaya, eso es nuevo para mí! ¿Puedes contarme más?");
        this.defaultOutput.add("No estoy seguro de entender completamente. ¿Puedes explicar más detalladamente?");
        this.defaultOutput.add("Lo siento, mi capacidad para responder a eso es limitada en este momento.");
        this.defaultOutput.add("Esa no es una solicitud que pueda manejar en este momento.");
        this.defaultOutput.add("Mis disculpas, parece que no tengo una respuesta apropiada para eso.");
        this.defaultOutput.add("No tengo información almacenada sobre ese tema específico.");
        this.defaultOutput.add("Parece que esta es una situación inusual. ¿En qué más puedo ayudarte?");
        this.defaultOutput.add("No tengo una respuesta predefinida para esa entrada. ¿Hay algo más de lo que te gustaría hablar?");
        this.defaultOutput.add("Lo siento, parece que no tengo información relevante para eso.");
        this.defaultOutput.add("No puedo procesar esa entrada en este momento. ¿Puedes intentar de nuevo?");
    }

}
