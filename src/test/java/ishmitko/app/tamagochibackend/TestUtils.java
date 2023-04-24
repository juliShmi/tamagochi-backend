package ishmitko.app.tamagochibackend;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class TestUtils {

    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();


    public static String loadFile(String fileName) {
        try {
            return new String(TestUtils.class.getResourceAsStream(fileName).readAllBytes(), StandardCharsets.UTF_8);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static <T> T loadObject(String fileName, Class<T> objectClass) {
        String json = loadFile(fileName);
        try {
            return OBJECT_MAPPER.readValue(json, objectClass);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

}
