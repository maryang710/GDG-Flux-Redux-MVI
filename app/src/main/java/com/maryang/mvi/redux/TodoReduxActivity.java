package com.maryang.mvi.redux;

import android.os.Bundle;
import android.widget.Button;

import com.google.android.material.textfield.TextInputEditText;
import com.maryang.mvi.R;
import com.maryang.mvi.flux.todo.TodoAdapter;
import com.maryang.mvi.redux.reducer.TodoReducer;
import com.maryang.mvi.redux.state.TodoState;
import com.maryang.mvi.redux.store.SimpleStore;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import io.reactivex.android.schedulers.AndroidSchedulers;

public class TodoReduxActivity extends AppCompatActivity {

    private TextInputEditText inputTodo;
    private Button btnAdd;
    private RecyclerView recyclerView;
    private Button btnClear;
    private TodoAdapter adapter;

    private TodoActionCreator actionCreator = TodoActionCreator.get();
    private SimpleStore<TodoState> store = SimpleStore.create(new TodoState(), new TodoReducer());

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_todo);
        findView();
        setButton();
        setRecyclerView();
        subscribeStore();
    }

    private void findView() {
        inputTodo = findViewById(R.id.input_todo);
        btnAdd = findViewById(R.id.btn_add);
        recyclerView = findViewById(R.id.list);
        btnClear = findViewById(R.id.btn_clear);
    }

    private void setButton() {
        btnAdd.setOnClickListener(v -> {
            String text = inputTodo.getText().toString();
            if ("".equals(text)) return;
            store.dispatch(actionCreator.create(text));
            inputTodo.setText("");
        });
        btnClear.setOnClickListener(v -> store.dispatch(actionCreator.clear()));
    }

    private void setRecyclerView() {
        adapter = new TodoAdapter();
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
    }

    private void subscribeStore() {
        store.asObservable()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::updateUI);
    }

    private void updateUI(TodoState state) {
        adapter.setItems(state.getTodos());
    }
}
