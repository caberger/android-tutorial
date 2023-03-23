package at.ac.htlperg.viewmodeldemo;

import static androidx.constraintlayout.helper.widget.MotionEffect.TAG;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.helper.widget.MotionEffect;
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
        var viewModel = new ViewModelProvider(this).get(CounterViewModel.class);

        viewModel.getData().observe(this, model -> {
            var count = model.count;
            Log.d(TAG, String.format("view Model changed: %s", count));
        });
    }
    public void onButtonClicked(View view) {
        var intent = new Intent(this, DetailActivity.class);
        startActivity(intent);
    }
}