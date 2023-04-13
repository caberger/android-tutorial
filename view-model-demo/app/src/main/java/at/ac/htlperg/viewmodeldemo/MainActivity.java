package at.ac.htlperg.viewmodeldemo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    public static final String TAG = "log_" + MainActivity.class.getSimpleName();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        var viewModel = new ViewModelProvider(this).get(UserViewModel.class);

        viewModel.getData().observe(this, model -> {
            var users = model.getUsers();
            users.stream().forEach(user -> Log.d(TAG, String.format("view Model changed: %s", user.name)));
        });
        new UserService().load().thenAccept(users -> {
            var model = new Model(users);
            viewModel.getData().postValue(model);
        });

        /*
        Button addButton = findViewById(R.id.addbutton);
        addButton.setOnClickListener(button -> {
            var model = new Model(users);
            model.count = viewModel.getData().getValue().count + 1;
            viewModel.getData().postValue(model);
        });
        */

    }
    public void onButtonClicked(View view) {
        var intent = new Intent(this, DetailActivity.class);
        startActivity(intent);
    }
}