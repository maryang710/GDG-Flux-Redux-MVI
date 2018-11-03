package com.maryang.mvi.mvi;

import com.maryang.mvi.redux.state.State;

import io.reactivex.Observable;

public interface MviView<S extends State> {
    Observable<MviIntent> intents();

    void render(S state);
}