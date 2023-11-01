package at.ac.htl.sensors.model;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import java.io.IOException;

public class Mapper<T> {
    private Class<? extends T> clazz;
    private ObjectMapper mapper;

    public Mapper(Class<? extends T> clazz) {
        this.clazz = clazz;
        mapper = new ObjectMapper()
                .configure(SerializationFeature.INDENT_OUTPUT, true)
                .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        mapper.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY); // records
    }
    public String toResource(T model) {
        try {
            return mapper.writeValueAsString(model);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
    public T fromResource(String json) {
        T model = null;
        try {
            model = mapper.readValue(json.getBytes(), clazz);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return model;
    }
    public T clone(T model) {
        return fromResource(toResource(model));
    }
}