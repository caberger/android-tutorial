package at.ac.htlperg.viewmodeldemo;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class UserViewModel extends ViewModel {
    private MutableLiveData<Model> data = new MutableLiveData(new Model());

    public MutableLiveData<Model> getData() {
        return data;
    }
}
