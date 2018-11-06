package com.maryang.mvi.mvp;

import android.os.Bundle;
import android.widget.Button;

import com.google.android.material.textfield.TextInputEditText;
import com.maryang.mvi.R;
import com.maryang.mvi.flux.todo.TodoAdapter;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class TodoMvpActivity extends AppCompatActivity {

    private TextInputEditText inputTodo;
    private Button btnAdd;
    private RecyclerView recyclerView;
    private Button btnClear;
    private TodoAdapter adapter;

    private TodoPresenter presenter = new TodoPresenter();
    private TodoStore store = TodoStore.get();

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
            presenter.create(inputTodo.getText().toString());
            inputTodo.setText("");
        });
        btnClear.setOnClickListener(v -> presenter.clear());
    }

    private void setRecyclerView() {
        adapter = new TodoAdapter();
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
    }

    private void subscribeStore() {
        store.states().subscribe(todos -> adapter.setItems(todos));
    }
}
