import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import at.ac.htlperg.viewmodeldemo.Model;

public class CounterViewModel extends ViewModel {
    private MutableLiveData<Model> data = new MutableLiveData(new Model());

    public MutableLiveData<Model> getData() {
        return data;
    }
}
