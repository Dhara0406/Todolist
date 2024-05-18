package com.bawp.todoister.adapter;

import com.bawp.todoister.model.Task;

public interface OnTodoClickLister {
    void onTodoClick( Task task);
    void onTodoRadioButton(Task task);
}
