package com.maryang.mvi.flux.todo;

import com.maryang.mvi.flux.Action;
import com.maryang.mvi.flux.Dispatcher;

public class TodoActionCreator {

    private static TodoActionCreator instance;

    private TodoActionCreator() {
    }

    public synchronized static TodoActionCreator get() {
        if (instance == null) instance = new TodoActionCreator();
        return instance;
    }

    public void create(String todo) {
        Dispatcher.dispatch(Action.with(TodoActions.TYPE_CREATE)
                .data(TodoActions.KEY_TEXT, todo));
    }

    public void clear() {
        Dispatcher.dispatch(Action.with(TodoActions.TYPE_CLEAR));
    }
}
