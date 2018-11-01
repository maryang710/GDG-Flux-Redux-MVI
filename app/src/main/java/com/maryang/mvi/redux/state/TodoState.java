package com.maryang.mvi.redux.state;

import com.maryang.mvi.flux.todo.Todo;

import java.util.ArrayList;
import java.util.List;

public class TodoState implements State {

    private List<Todo> todos = new ArrayList<>();

    public TodoState() {
    }

    public TodoState(TodoState todoState) {
        for (Todo todo : todoState.getTodos()) this.todos.add(new Todo(todo));
    }

    public List<Todo> getTodos() {
        return todos;
    }
}
