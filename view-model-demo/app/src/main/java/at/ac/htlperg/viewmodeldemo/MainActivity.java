package at.ac.htlperg.viewmodeldemo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.database.DataSetObserver;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    public static final String TAG = "log_" + MainActivity.class.getSimpleName();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        var viewModel = new ViewModelProvider(this).get(UserViewModel.class);
        ListView listView = findViewById(R.id.userListView);
        viewModel.getData().observe(this, model -> {
            var users = model.getUsers();
            users.stream().forEach(user -> Log.d(TAG, String.format("view Model changed: %s", user.name)));
            var adapter = new UserAdapter(users);
            listView.setAdapter(adapter);
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
    class UserAdapter implements ListAdapter {
        private final List<User> users;
        public UserAdapter(List<User> users) {
            this.users = users;
        }

        @Override
        public boolean areAllItemsEnabled() {
            return false;
        }

        @Override
        public boolean isEnabled(int position) {
            return false;
        }

        @Override
        public void registerDataSetObserver(DataSetObserver observer) {

        }

        @Override
        public void unregisterDataSetObserver(DataSetObserver observer) {

        }

        @Override
        public int getCount() {
            return users.size();
        }

        @Override
        public Object getItem(int position) {
            return users.get(position);
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public boolean hasStableIds() {
            return false;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if (convertView == null) {
                convertView = getLayoutInflater().inflate(R.layout.user_cell_layout, null);
            }
            TextView textView = convertView.findViewById(R.id.textView);
            var user = users.get(position);
            textView.setText(user.name);
            return convertView;
        }

        @Override
        public int getItemViewType(int position) {
            return 0;
        }

        @Override
        public int getViewTypeCount() {
            return 1;
        }

        @Override
        public boolean isEmpty() {
            return false;
        }
    }
}