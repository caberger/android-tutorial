package at.ac.htlperg.viewmodeldemo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class MainActivity extends AppCompatActivity {
    public static final String TAG = "log_" + MainActivity.class.getSimpleName();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        var viewModel = new ViewModelProvider(this).get(UserViewModel.class);

        viewModel.getData().observe(this, model -> {
            model.getUsers().stream().forEach(user -> {
                Log.d(TAG, String.format("view Model changed: %s", user.name));
            });
        });
        var userService = new UserService();
        userService.load().thenAccept(users -> {
            var model = new Model(users);
            viewModel.getData().postValue(model);
        });
    }

}