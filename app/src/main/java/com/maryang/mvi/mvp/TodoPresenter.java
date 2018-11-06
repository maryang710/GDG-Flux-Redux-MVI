package com.maryang.mvi.mvp;

import com.maryang.mvi.flux.todo.Todo;

public class TodoPresenter {

    private TodoRepository repository = new TodoRepository();
    private TodoStore store = TodoStore.get();

    public void create(String text) {
        Todo todo = repository.create(text);
        store.create(todo);
    }

    public void clear() {
        repository.clear();
        store.clear();
    }
}
