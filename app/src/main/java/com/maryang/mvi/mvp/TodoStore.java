package com.maryang.mvi.mvp;

import com.maryang.mvi.flux.todo.Todo;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.subjects.BehaviorSubject;

public class TodoStore {

    private static TodoStore instance;
    private List<Todo> todos = new ArrayList<>();
    private BehaviorSubject<List<Todo>> stateSubject = BehaviorSubject.create();

    private TodoStore() {
    }

    public synchronized static TodoStore get() {
        if (instance == null) instance = new TodoStore();
        return instance;
    }

    public void create(Todo todo) {
        todos.add(todo);
        stateSubject.onNext(todos);
    }

    public void clear() {
        todos.clear();
        stateSubject.onNext(todos);
    }

    public Observable<List<Todo>> states() {
        return stateSubject;
    }
}
