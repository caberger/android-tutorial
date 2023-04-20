package at.ac.htlperg.viewmodeldemo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.database.DataSetObserver;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
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
        ListView listView = findViewById(R.id.user_list);
        viewModel.getData().observe(this, model -> {
            var adapter = new UserAdpater(model.getUsers());
            listView.setAdapter(adapter);
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
    class UserAdpater implements ListAdapter {
        private final List<User> users;

        UserAdpater(List<User> users) {
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
        public User getItem(int position) {
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
                convertView = getLayoutInflater().inflate(R.layout.user_list_cell_layout, null);
            }
            var user = getItem(position);
            TextView id = convertView.findViewById(R.id.id);
            id.setText(Integer.valueOf(user.id).toString());
            TextView name = convertView.findViewById(R.id.username);
            name.setText(user.name);
            convertView.setOnClickListener(view -> {
                Log.d(TAG, String.format("user %d ausgewählt (%s)",
                        user.id, user.name));
            });
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