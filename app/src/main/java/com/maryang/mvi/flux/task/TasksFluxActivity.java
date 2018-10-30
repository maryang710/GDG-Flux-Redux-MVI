package com.maryang.mvi.flux.task;

import android.os.Bundle;
import android.widget.Button;

import com.google.android.material.textfield.TextInputEditText;
import com.maryang.mvi.R;
import com.maryang.mvi.flux.Dispatcher;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class TasksFluxActivity extends AppCompatActivity {

    private TextInputEditText inputTask;
    private Button btnAdd;
    private RecyclerView recyclerView;
    private Button btnClear;
    private TaskAdapter adapter;

    private TaskActionCreator actionCreator = TaskActionCreator.get();
    private TaskStore store = TaskStore.get();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tasks);
        Dispatcher.register(this);
        Dispatcher.register(store);
        findView();
        setButton();
        setRecyclerView();
    }

    private void findView() {
        inputTask = findViewById(R.id.input_task);
        btnAdd = findViewById(R.id.btn_add);
        recyclerView = findViewById(R.id.list);
        btnClear = findViewById(R.id.btn_clear);
    }

    private void setButton() {
        btnAdd.setOnClickListener(v -> {
            actionCreator.create(inputTask.getText().toString());
            inputTask.setText("");
        });
        btnClear.setOnClickListener(v -> actionCreator.clear());
    }

    private void setRecyclerView() {
        adapter = new TaskAdapter();
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
    public void onEvent(TaskStore.TodoChangeEvent event) {
        updateUI();
    }

    private void updateUI() {
        adapter.setItems(store.getTasks());
    }
}
