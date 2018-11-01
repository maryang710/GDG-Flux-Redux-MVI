package com.maryang.mvi.redux;

import com.maryang.mvi.flux.Action;
import com.maryang.mvi.flux.todo.TodoActions;

public class TodoActionCreator {

    private static TodoActionCreator instance;

    private TodoActionCreator() {
    }

    public synchronized static TodoActionCreator get() {
        if (instance == null) instance = new TodoActionCreator();
        return instance;
    }

    public Action create(String todo) {
        return Action.with(TodoActions.TYPE_CREATE)
                .data(TodoActions.KEY_TEXT, todo);
    }

    public Action clear() {
        return Action.with(TodoActions.TYPE_CLEAR);
    }
}
