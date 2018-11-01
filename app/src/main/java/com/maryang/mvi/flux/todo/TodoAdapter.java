package com.maryang.mvi.flux.todo;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.maryang.mvi.R;

import java.util.ArrayList;
import java.util.List;

import androidx.appcompat.widget.AppCompatCheckBox;
import androidx.recyclerview.widget.RecyclerView;

public class TodoAdapter extends RecyclerView.Adapter<TodoAdapter.ViewHolder> {

    private List<Todo> todos = new ArrayList<>();

    public void setItems(List<Todo> todos) {
        this.todos = todos;
        notifyDataSetChanged();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int i) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_todo_item, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int i) {
        viewHolder.bind(todos.get(i));
    }

    @Override
    public int getItemCount() {
        return todos.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        private AppCompatCheckBox checkBox;
        private TextView txtTodo;

        ViewHolder(View v) {
            super(v);
            checkBox = v.findViewById(R.id.checkbox);
            txtTodo = v.findViewById(R.id.txt_todo);
        }

        void bind(final Todo todo) {
            txtTodo.setText(todo.getText());
        }
    }
}