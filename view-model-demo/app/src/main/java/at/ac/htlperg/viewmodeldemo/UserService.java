package at.ac.htlperg.viewmodeldemo;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.concurrent.CompletionException;

import kotlin.NotImplementedError;

public class UserService {
    public List<User> load() {
        try {
            var url = new URL("https://jsonplaceholder.typicode.com/users");
            var mapper = new ObjectMapper()
                    .configure(SerializationFeature.FAIL_ON_UNWRAPPED_TYPE_IDENTIFIERS, false);

        } catch (MalformedURLException e) {
            throw new CompletionException(e);
        }
    }
}
