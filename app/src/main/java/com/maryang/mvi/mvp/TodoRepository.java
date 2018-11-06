package com.maryang.mvi.mvp;

import com.maryang.mvi.flux.todo.Todo;

public class TodoRepository {

    public Todo create(String text) {
        return new Todo(text);
    }

    public void clear() {
        // Do something
    }
}
