package t5;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class JsonBeautifier {

    public static void main(String[] args) {
        // Pfade zur Eingabe- und Ausgabedatei angeben
        String inputFilePath = "src/main/java/t5/input.json";
        String outputFilePath = "src/main/java/t5/output.json";


        try {
            // Unformatiertes JSON aus Datei lesen
            String jsonString = new String(Files.readAllBytes(Paths.get(inputFilePath)));

            // JSON formatieren
            String prettyJson = beautifyJson(jsonString);

            // Formatiertes JSON in Ausgabedatei schreiben
            Files.write(Paths.get(outputFilePath), prettyJson.getBytes());

            System.out.println("Das JSON wurde erfolgreich formatiert und in die Datei " + outputFilePath + " geschrieben.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String beautifyJson(String jsonString) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            Object json = objectMapper.readValue(jsonString, Object.class);
            ObjectWriter writer = objectMapper.writerWithDefaultPrettyPrinter();
            return writer.writeValueAsString(json);
        } catch (IOException e) {
            e.printStackTrace();
            return "Ungültiger JSON-Input";
        }
    }
}