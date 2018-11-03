package com.maryang.mvi.mvi.presenter;

import com.maryang.mvi.mvi.MviIntent;
import com.maryang.mvi.mvi.reducer.MviReducer;
import com.maryang.mvi.redux.state.State;

import io.reactivex.Observable;
import io.reactivex.subjects.BehaviorSubject;
import io.reactivex.subjects.PublishSubject;

public class MviPresenter<S extends State> {

    private S initialState;
    private MviReducer<S> reducer;

    private PublishSubject<MviIntent> intentSubject = PublishSubject.create();
    private BehaviorSubject<S> stateSubject = BehaviorSubject.create();

    public MviPresenter(S initialState, MviReducer<S> reducer) {
        this.initialState = initialState;
        this.reducer = reducer;
    }

    public void processIntents(Observable<MviIntent> intents) {
        intents
                .scan(initialState, (s, intent) -> reducer.reduce(s, intent))
                .doOnNext(state -> initialState = state)
                .subscribe(stateSubject);
    }

    public Observable<S> states() {
        return stateSubject;
    }
}
