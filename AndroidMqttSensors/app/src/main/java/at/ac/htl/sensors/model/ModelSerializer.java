package at.ac.htl.sensors.model;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import java.io.IOException;

public class ModelSerializer {
    private ObjectMapper mapper = new ObjectMapper()
            .configure(SerializationFeature.INDENT_OUTPUT, true)
            .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
            ;
    public ModelSerializer() {
        mapper = new ObjectMapper()
                .configure(SerializationFeature.INDENT_OUTPUT, true)
                .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        mapper.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);
    }
    public String toResource(Model model) {
        try {
            return mapper.writeValueAsString(model);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
    public Model fromResource(String json) {
        Model model = null;
        try {
            model = mapper.readValue(json.getBytes(), Model.class);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return model;
    }
    public Model clone(Model model) {
        return fromResource(toResource(model));
    }
}