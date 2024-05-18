package com.bawp.todoister.adapter;

import android.content.res.ColorStateList;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatRadioButton;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.recyclerview.widget.RecyclerView;

import com.bawp.todoister.R;
import com.bawp.todoister.model.Task;
import com.bawp.todoister.util.Utils;
import com.google.android.material.chip.Chip;

import java.util.List;

public class RecyclerViewAdapter  extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {
    private final List<Task> taskList;
    private final OnTodoClickLister todoClickLister;

    public RecyclerViewAdapter(List<Task> taskList, OnTodoClickLister onTodoClickLister) {
        this.taskList = taskList;
        this.todoClickLister = onTodoClickLister;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                 .inflate(R.layout.todo_row,parent,false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewAdapter.ViewHolder holder, int position) {
        Task task=taskList.get(position);
//
        String formatted= Utils.formateDate(task.getDueDate());

        ColorStateList colorStateList=new ColorStateList(new int[][]{
                new int[]{-android.R.attr.state_enabled},
                new int[]{android.R.attr.state_enabled}
        },
                new int[]{
                        Color.LTGRAY,
                        Utils.priorityColor(task)

                });
        holder.task.setText(task.getTask());
        holder.todayChip.setText(formatted);
        holder.todayChip.setTextColor(Utils.priorityColor(task));
        holder.todayChip.setChipIconTint(colorStateList);
        holder.radiobutton.setButtonTintList(colorStateList);

    }

    @Override
    public int getItemCount() {
        return taskList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        public AppCompatRadioButton radiobutton;
        public AppCompatTextView task;
        public Chip todayChip;
        OnTodoClickLister onTodoClickLister;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            radiobutton=itemView.findViewById(R.id.todo_radio_button);
            task=itemView.findViewById(R.id.todo_row_todo);
            todayChip=itemView.findViewById(R.id.todo_row_chip);
            this.onTodoClickLister=todoClickLister;
            itemView.setOnClickListener(this);
            radiobutton.setOnClickListener(this);

        }

        @Override
        public void onClick(View view) {
            Task currTask=taskList.get(getAdapterPosition());

            int id=view.getId();
            if (id==R.id.todo_row_layout){
                onTodoClickLister.onTodoClick( currTask);
            }
            else if (id==R.id.todo_radio_button){
//                currTask=taskList.get(getAdapterPosition());
                onTodoClickLister.onTodoRadioButton(currTask);

            }

        }
    }
}
