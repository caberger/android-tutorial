package at.ac.htl.sensors.model;

import androidx.lifecycle.ViewModel;

import java.util.function.Consumer;

import io.reactivex.rxjava3.subjects.BehaviorSubject;
import io.reactivex.rxjava3.core.Observable;

public class LocationViewModel extends ViewModel {
    final private Mapper<Model> mapper = new Mapper<>(Model.class);
    final private BehaviorSubject<Model> store = BehaviorSubject.createDefault(new Model());

    public BehaviorSubject<Model> getStore() {
        return store;
    }
    public void next(Consumer<Model> reducer) {
        var current = store.getValue();
        var nextState = mapper.clone(current);
        reducer.accept(nextState);
        store.onNext(nextState);
    }
}
