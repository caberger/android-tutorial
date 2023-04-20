package at.ac.htlperg.viewmodeldemo;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionException;

import kotlin.NotImplementedError;

public class UserService {
    public CompletableFuture<List<User>> load() {
        try {
            var url = new URL("https://jsonplaceholder.typicode.com/users");
            var mapper = new ObjectMapper()
                    .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

            return CompletableFuture.supplyAsync(() -> {
                var users = new User[0];
                try {
                    users = mapper.readValue(url, User[].class);
                    return List.of(users);
                } catch (IOException e) {
                    throw new CompletionException(e);
                }
            });
        } catch (Exception e) {
            throw new CompletionException(e);
        }
    }
}
