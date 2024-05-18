package com.bawp.todoister.data;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.bawp.todoister.model.Task;
import com.bawp.todoister.util.TaskRoomDatabse;

import java.util.List;

public class DoisterRepository {
    private final TaskDao taskDao;
    private LiveData<List<Task>> alltask ;

    public DoisterRepository(Application application){
        TaskRoomDatabse databse=TaskRoomDatabse.getDatabase(application);
        taskDao= databse.taskDao();
        alltask=taskDao.getTasks();
    }

    public LiveData<List<Task>> getAlltask(){
        return alltask;

    }
    public void insert(Task task){
        TaskRoomDatabse.databaseWriteExecutor.execute(() -> taskDao.insertTask(task));

    }
    public LiveData<Task> get(long id){
        return taskDao.get(id);
    }
    public void update(Task task){
        TaskRoomDatabse.databaseWriteExecutor.execute(() ->taskDao.update(task));
    }
    public void delete(Task task){
        TaskRoomDatabse.databaseWriteExecutor.execute(() ->taskDao.delete(task));
    }
}
