package com.maryang.mvi.flux.todo;

import com.maryang.mvi.flux.Action;
import com.maryang.mvi.flux.Store;

import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.List;

public class TodoStore extends Store {

    private static TodoStore instance;
    private List<Todo> todos = new ArrayList<>();

    private TodoStore() {
    }

    public synchronized static TodoStore get() {
        if (instance == null) instance = new TodoStore();
        return instance;
    }

    public List<Todo> getTodos() {
        return todos;
    }

    @Subscribe
    public void onEvent(Action action) {
        switch (action.getType()) {
            case TodoActions.TYPE_CREATE:
                String text = (String) action.getData().get(TodoActions.KEY_TEXT);
                todos.add(new Todo(text));
                emitChange();
                break;
            case TodoActions.TYPE_CLEAR:
                todos.clear();
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
