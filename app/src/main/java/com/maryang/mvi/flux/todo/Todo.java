package com.maryang.mvi.flux.todo;

public class Todo {

    private String text;

    public Todo(Todo todo) {
        this(todo.text);
    }

    public Todo(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }
}
