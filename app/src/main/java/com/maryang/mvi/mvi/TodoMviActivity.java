package com.maryang.mvi.mvi;

import android.os.Bundle;
import android.widget.Button;

import com.google.android.material.textfield.TextInputEditText;
import com.maryang.mvi.R;
import com.maryang.mvi.flux.Dispatcher;
import com.maryang.mvi.flux.todo.TodoActionCreator;
import com.maryang.mvi.flux.todo.TodoAdapter;
import com.maryang.mvi.flux.todo.TodoStore;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class TodoMviActivity extends AppCompatActivity {

    private TextInputEditText inputTodo;
    private Button btnAdd;
    private RecyclerView recyclerView;
    private Button btnClear;
    private TodoAdapter adapter;

    private TodoActionCreator actionCreator = TodoActionCreator.get();
    private TodoStore store = TodoStore.get();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_todo);
        Dispatcher.register(this);
        Dispatcher.register(store);
        findView();
        setButton();
        setRecyclerView();
    }

    private void findView() {
        inputTodo = findViewById(R.id.input_todo);
        btnAdd = findViewById(R.id.btn_add);
        recyclerView = findViewById(R.id.list);
        btnClear = findViewById(R.id.btn_clear);
    }

    private void setButton() {
        btnAdd.setOnClickListener(v -> {
            actionCreator.create(inputTodo.getText().toString());
            inputTodo.setText("");
        });
        btnClear.setOnClickListener(v -> actionCreator.clear());
    }

    private void setRecyclerView() {
        adapter = new TodoAdapter();
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Dispatcher.unregister(this);
        Dispatcher.register(store);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEvent(TodoStore.TodoChangeEvent event) {
        updateUI();
    }

    private void updateUI() {
        adapter.setItems(store.getTodos());
    }
}
