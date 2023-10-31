package at.ac.htl.sensors.model;

import androidx.lifecycle.ViewModel;

import java.util.function.Consumer;

import io.reactivex.rxjava3.subjects.ReplaySubject;

public class LocationViewModel extends ViewModel {
    final private ModelSerializer serializer = new ModelSerializer();
    final private ReplaySubject<Model> store = ReplaySubject.create();

    public Model.LocationData location() {
        return store.getValue().locationData;
    }
    public ReplaySubject<Model> getStore() {
        return store;
    }

    public void next(Consumer<Model> reducer) {
        var current = store.getValue();
        if (current == null) {
            current = new Model();
        }
        var nextState = serializer.clone(current);
        reducer.accept(nextState);
        store.onNext(nextState);
    }
}
