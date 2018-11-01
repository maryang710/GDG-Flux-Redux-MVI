package com.maryang.mvi.redux.reducer;

import com.maryang.mvi.flux.Action;
import com.maryang.mvi.flux.todo.Todo;
import com.maryang.mvi.flux.todo.TodoActions;
import com.maryang.mvi.redux.state.TodoState;

public class TodoReducer implements Reducer<TodoState> {

    @Override
    public TodoState reduce(TodoState oldState, Action action) {
        switch (action.getType()) {
            case TodoActions.TYPE_CREATE:
                TodoState newState = new TodoState(oldState);
                String text = (String) action.getData().get(TodoActions.KEY_TEXT);
                newState.getTodos().add(new Todo(text));
                return newState;
            case TodoActions.TYPE_CLEAR:
                return new TodoState();
            default:
                return oldState;
        }
    }
}
