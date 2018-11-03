package com.maryang.mvi;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import com.maryang.mvi.flux.todo.TodoFluxActivity;
import com.maryang.mvi.mvi.TodoMviActivity;
import com.maryang.mvi.redux.TodoReduxActivity;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private Button btnFlux;
    private Button btnRedux;
    private Button btnMvi;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findView();
        setButton();
    }

    private void findView() {
        btnFlux = findViewById(R.id.btn_flux);
        btnRedux = findViewById(R.id.btn_redux);
        btnMvi = findViewById(R.id.btn_mvi);
    }

    private void setButton() {
        btnFlux.setOnClickListener(v ->
                startActivity(new Intent(MainActivity.this, TodoFluxActivity.class)));
        btnRedux.setOnClickListener(v ->
                startActivity(new Intent(MainActivity.this, TodoReduxActivity.class)));
        btnMvi.setOnClickListener(v ->
                startActivity(new Intent(MainActivity.this, TodoMviActivity.class)));
    }
}
