package com.maryang.mvi.flux.task;

import com.maryang.mvi.flux.Action;
import com.maryang.mvi.flux.Dispatcher;

public class TaskActionCreator {

    private static TaskActionCreator instance;

    private TaskActionCreator() {
    }

    public synchronized static TaskActionCreator get() {
        if (instance == null) instance = new TaskActionCreator();
        return instance;
    }

    public void create(String task) {
        Dispatcher.dispatch(Action.with(TaskActions.TYPE_CREATE)
                .data(TaskActions.KEY_TEXT, task));
    }

    public void clear() {
        Dispatcher.dispatch(Action.with(TaskActions.TYPE_CLEAR));
    }
}
