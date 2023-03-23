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
        var viewModel = new ViewModelProvider(this).get(CounterViewModel.class);

        viewModel.getData().observe(this, model -> {
            var count = model.count;
            Log.d(TAG, String.format("view Model changed: %s", count));
        });
        Button addButton = findViewById(R.id.addbutton);
        addButton.setOnClickListener(button -> {
            var oldModel = viewModel.getData().getValue();
            var model = new ModelSerializer().clone(oldModel);
            model.count++;
            viewModel.getData().postValue(model);
        });
    }
    public void onButtonClicked(View view) {
        var viewModel = new ViewModelProvider(this).get(CounterViewModel.class);
        var json = new ModelSerializer().toResource(viewModel.getData().getValue());
        Log.d(TAG, "json is: " + json);
        var intent = new Intent(this, DetailActivity.class);
        intent.putExtra(Intent.EXTRA_TEXT, json);
        startActivity(intent);
    }
}