package com.maryang.mvi.mvi.reducer;

import com.maryang.mvi.flux.todo.Todo;
import com.maryang.mvi.flux.todo.TodoActions;
import com.maryang.mvi.mvi.MviIntent;
import com.maryang.mvi.redux.state.TodoState;

public class TodoReducer implements MviReducer<TodoState> {

    @Override
    public TodoState reduce(TodoState oldState, MviIntent intent) {
        switch (intent.getType()) {
            case TodoActions.TYPE_CREATE:
                TodoState newState = new TodoState(oldState);
                String text = (String) intent.getData().get(TodoActions.KEY_TEXT);
                newState.getTodos().add(new Todo(text));
                return newState;
            case TodoActions.TYPE_CLEAR:
                return new TodoState();
            default:
                return oldState;
        }
    }
}
