package com.bawp.todoister.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.Date;

@Entity(tableName = "task_name")
public class Task {
    @ColumnInfo(name="task_id")
    @PrimaryKey(autoGenerate = true)
            public long taskid;

    public String task;
//    public Priority priority;
    public Priority priority;
    @ColumnInfo(name="due_date")
    public Date dueDate;
    @ColumnInfo(name="created_at")
    public  Date dateCreated;

    @ColumnInfo(name="is_done")
    public boolean isdone;

    public Task(String task, Priority priority, Date dueDate, Date dateCreated, boolean isdone) {
        this.task = task;
        this.priority = priority;
        this.dueDate = dueDate;
        this.dateCreated = dateCreated;
        this.isdone = isdone;
    }


    public long getTaskid() {
        return taskid;
    }

    public void setTaskid(long taskid) {
        this.taskid = taskid;
    }

    public String getTask() {
        return task;
    }

    public void setTask(String task) {
        this.task = task;
    }

    public Priority getPriority() {
        return priority;
    }

    public void setPriority(Priority priority) {
        this.priority = priority;
    }

    public Date getDueDate() {
        return dueDate;
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }

    public Date getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }

    public boolean isIsdone() {
        return isdone;
    }

    public void setIsdone(boolean isdone) {
        this.isdone = isdone;
    }

    @Override
    public String toString() {
        return "Task{" +
                "taskid=" + taskid +
                ", task='" + task + '\'' +
                ", priority=" + priority +
                ", dueDate=" + dueDate +
                ", dateCreated=" + dateCreated +
                ", isdone=" + isdone +
                '}';
    }
}
