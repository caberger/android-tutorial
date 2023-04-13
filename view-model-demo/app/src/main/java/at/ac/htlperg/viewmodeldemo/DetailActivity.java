package at.ac.htlperg.viewmodeldemo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

public class DetailActivity extends AppCompatActivity {

    private static final String TAG = DetailActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Model model = new ModelSerializer().fromResource(getIntent().getStringExtra(Intent.EXTRA_TEXT));

        //Log.d(TAG, String.format("Count is: %d", model.count));
        setContentView(R.layout.activity_detail);
    }
}