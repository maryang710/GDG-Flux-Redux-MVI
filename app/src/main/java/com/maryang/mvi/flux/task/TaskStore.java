package com.maryang.mvi.flux.task;

import com.maryang.mvi.flux.Action;
import com.maryang.mvi.flux.Store;

import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.List;

public class TaskStore extends Store {

    private static TaskStore instance;
    private List<Task> tasks = new ArrayList<>();

    private TaskStore() {
    }

    public synchronized static TaskStore get() {
        if (instance == null) instance = new TaskStore();
        return instance;
    }

    public List<Task> getTasks() {
        return tasks;
    }

    @Subscribe
    public void onEvent(Action action) {
        switch (action.getType()) {
            case TaskActions.TYPE_CREATE:
                String text = (String) action.getData().get(TaskActions.KEY_TEXT);
                tasks.add(new Task(text));
                emitChange();
                break;
            case TaskActions.TYPE_CLEAR:
                tasks.clear();
                emitChange();
                break;
        }
    }

    @Override
    protected ChangeEvent changeEvent() {
        return new TodoChangeEvent();
    }

    public class TodoChangeEvent implements Store.ChangeEvent {
    }
}
