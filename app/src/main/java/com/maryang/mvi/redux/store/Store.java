package com.maryang.mvi.redux.store;

import com.maryang.mvi.flux.Action;
import com.maryang.mvi.redux.state.State;

import io.reactivex.Observable;

public interface Store<S extends State> {

    void dispatch(Action action);

    Observable<S> asObservable();
}
