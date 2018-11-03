package com.maryang.mvi.mvi.reducer;

import com.maryang.mvi.mvi.MviIntent;
import com.maryang.mvi.redux.state.State;

public interface MviReducer<S extends State> {
    S reduce(S oldState, MviIntent action);
}
