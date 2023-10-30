package at.ac.htl.sensors.model;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.compose.runtime.MutableState;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import org.jetbrains.annotations.NotNull;

import java.util.function.Consumer;

public class LocationViewModel extends ViewModel {
    final private ModelSerializer serializer = new ModelSerializer();
    final private MutableLiveData<Model> data = new MutableLiveData<>(new Model());

    public Model getModel() {
        return data.getValue();
    }
    public Model.LocationData location() {
        return data.getValue().locationData;
    }
    public MutableLiveData<Model> getData() {
        return data;
    }
    public void next(Consumer<Model> reducer) {
        var nextState = serializer.clone(getModel());
        reducer.accept(nextState);
        getData().postValue(nextState);
    }

    @NotNull
    public LocationViewModel invoke() {
        return this;
    }
}
