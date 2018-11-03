package com.maryang.mvi.mvi;

import android.os.Bundle;
import android.widget.Button;

import com.google.android.material.textfield.TextInputEditText;
import com.maryang.mvi.R;
import com.maryang.mvi.flux.todo.TodoActions;
import com.maryang.mvi.flux.todo.TodoAdapter;
import com.maryang.mvi.mvi.presenter.MviPresenter;
import com.maryang.mvi.mvi.reducer.TodoReducer;
import com.maryang.mvi.redux.state.TodoState;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import io.reactivex.Observable;
import io.reactivex.subjects.PublishSubject;

public class TodoMviActivity extends AppCompatActivity implements MviView<TodoState> {

    private TextInputEditText inputTodo;
    private Button btnAdd;
    private RecyclerView recyclerView;
    private Button btnClear;
    private TodoAdapter adapter;

    private MviPresenter<TodoState> presenter = new MviPresenter<>(new TodoState(), new TodoReducer());
    private PublishSubject<MviIntent> addIntent = PublishSubject.create();
    private PublishSubject<MviIntent> clearIntent = PublishSubject.create();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_todo);
        findView();
        setButton();
        setRecyclerView();
        initializeIntent();
    }

    private void findView() {
        inputTodo = findViewById(R.id.input_todo);
        btnAdd = findViewById(R.id.btn_add);
        recyclerView = findViewById(R.id.list);
        btnClear = findViewById(R.id.btn_clear);
    }

    private void setButton() {
        btnAdd.setOnClickListener(v -> {
            addIntent.onNext(MviIntent.with(TodoActions.TYPE_CREATE)
                    .data(TodoActions.KEY_TEXT, inputTodo.getText().toString()));
            inputTodo.setText("");
        });
        btnClear.setOnClickListener(v -> clearIntent.onNext(MviIntent.with(TodoActions.TYPE_CLEAR)));
    }

    private void setRecyclerView() {
        adapter = new TodoAdapter();
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
    }

    private void initializeIntent() {
        presenter.processIntents(intents());
        presenter.states().subscribe(this::render);
    }

    @Override
    public Observable<MviIntent> intents() {
        return Observable.merge(addIntent, clearIntent);
    }

    @Override
    public void render(TodoState state) {
        adapter.setItems(state.getTodos());
    }
}
