package com.maryang.mvi.redux.store;

import com.maryang.mvi.flux.Action;
import com.maryang.mvi.redux.reducer.Reducer;
import com.maryang.mvi.redux.state.State;

import io.reactivex.Observable;
import io.reactivex.subjects.BehaviorSubject;
import io.reactivex.subjects.PublishSubject;

public class SimpleStore<S extends State> implements Store<S> {

    private S initialState;
    private Reducer<S> reducer;

    private PublishSubject<Action> actionsSubject = PublishSubject.create();
    private BehaviorSubject<S> statesSubject = BehaviorSubject.create();

    private SimpleStore() {
        actionsSubject.map(action -> reducer.reduce(initialState, action))
                .doOnNext(s -> initialState = s)
                .subscribe(s -> statesSubject.onNext(s), Throwable::printStackTrace);
    }

    public static <S extends State> SimpleStore<S> create(S initialState, Reducer<S> reducer) {
        SimpleStore<S> store = new SimpleStore<>();
        store.initialState = initialState;
        store.reducer = reducer;
        return store;
    }

    @Override
    public void dispatch(Action action) {
        actionsSubject.onNext(action);
    }

    @Override
    public Observable<S> asObservable() {
        return statesSubject;
    }
}
