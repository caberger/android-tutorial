package at.ac.htlperg.viewmodeldemo;

import android.util.Log;

import com.fasterxml.jackson.core.exc.StreamReadException;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionException;

public class UserService {
    private static final String TAG = UserService.class.getSimpleName();

    public CompletableFuture<List<User>> load() {
        try {
            var url = new URL("https://jsonplaceholder.typicode.com/users");
            var mapper = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
            return CompletableFuture.supplyAsync(() -> {
                try {
                    var users = List.of(mapper.readValue(url, User[].class));
                    return users;
                } catch (Exception e) {
                    Log.e(TAG, "failed to load", e);
                    throw new CompletionException(e);
                }
            });
        } catch (Exception e) {
            throw new CompletionException(e);
        }
    }
}
