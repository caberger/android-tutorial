package at.ac.htlperg.viewmodeldemo;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

public class UserViewModel extends ViewModel {
    private MutableLiveData<Model> data = new MutableLiveData(new Model(List.of()));

    public MutableLiveData<Model> getData() {
        return data;
    }
}
