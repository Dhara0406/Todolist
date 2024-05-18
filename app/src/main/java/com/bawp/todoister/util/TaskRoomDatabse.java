package com.bawp.todoister.util;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverter;
import androidx.room.TypeConverters;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.bawp.todoister.data.TaskDao;
import com.bawp.todoister.model.Task;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
@Database(entities = {Task.class},version = 1,exportSchema = false)
@TypeConverters({Converter.class})
public  abstract class TaskRoomDatabse extends RoomDatabase {
    public static final int NUMBER_OF_THREADS=4;
    public static final String DATABASE_NAME="todoister_databse";
    private static volatile TaskRoomDatabse INSTANCE;

    public static final RoomDatabase.Callback sRoomDatabseCallback=new RoomDatabase.Callback(){
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            databaseWriteExecutor.execute(() ->{
                TaskDao taskDao= INSTANCE.taskDao();
                taskDao.deleteAll();



            });
        }
    };
    public static final ExecutorService databaseWriteExecutor= Executors.newFixedThreadPool(NUMBER_OF_THREADS);
    public static TaskRoomDatabse getDatabase(final Context context){
        if(INSTANCE==null){
            synchronized (TaskRoomDatabse.class){
                if(INSTANCE==null){
                    INSTANCE= Room.databaseBuilder(context.getApplicationContext(), TaskRoomDatabse.class,DATABASE_NAME)
                            .addCallback(sRoomDatabseCallback)
                            .build();


                }
            }
        }
        return INSTANCE;
    }
    public abstract TaskDao taskDao();


}
