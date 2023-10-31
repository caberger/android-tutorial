package at.ac.htl.sensors.model;

import androidx.lifecycle.ViewModel;

import java.util.function.Consumer;

import io.reactivex.rxjava3.subjects.BehaviorSubject;
import io.reactivex.rxjava3.subjects.ReplaySubject;

public class LocationViewModel extends ViewModel {
    final private ModelSerializer serializer = new ModelSerializer();
    final private BehaviorSubject<Model> store = BehaviorSubject.createDefault(new Model());

    public Model.LocationData location() {
        return store.getValue().locationData;
    }
    public BehaviorSubject<Model> getStore() {
        return store;
    }

    public void next(Consumer<Model> reducer) {
        var current = store.getValue();
        var nextState = serializer.clone(current);
        reducer.accept(nextState);
        store.onNext(nextState);
    }
}
