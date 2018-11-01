package com.maryang.mvi.redux.reducer;

import com.maryang.mvi.flux.Action;
import com.maryang.mvi.redux.state.State;

public interface Reducer<S extends State> {
    S reduce(S oldState, Action action);
}
