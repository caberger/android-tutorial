package at.ac.htl.sensors.model;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class LocationViewModel extends ViewModel {
    private MutableLiveData<Model> data = new MutableLiveData<>();
}
