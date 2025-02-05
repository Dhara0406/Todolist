package com.bawp.todoister;

import android.os.Bundle;

import com.bawp.todoister.adapter.OnTodoClickLister;
import com.bawp.todoister.adapter.RecyclerViewAdapter;
import com.bawp.todoister.model.SharedViewModel;
import com.bawp.todoister.model.Task;
import com.bawp.todoister.model.TaskViewModel;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity  implements OnTodoClickLister{
    private static final String TAG ="ITEM";
    private TaskViewModel taskViewModel;
    private RecyclerView recyclerView;
    private RecyclerViewAdapter recyclerViewAdapter;
    BottomSheetFragment bottomSheetFragment;
    SharedViewModel sharedViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        bottomSheetFragment=new BottomSheetFragment();
        ConstraintLayout constraintLayout=findViewById(R.id.bottomSheet);
        BottomSheetBehavior<ConstraintLayout> bottomSheetBehavior=BottomSheetBehavior.from(constraintLayout);
        bottomSheetBehavior.setPeekHeight(BottomSheetBehavior.STATE_HIDDEN);

        recyclerView=findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        taskViewModel=new ViewModelProvider.AndroidViewModelFactory(
                MainActivity.this.getApplication()).create(TaskViewModel.class);


        taskViewModel=new ViewModelProvider.AndroidViewModelFactory(
                MainActivity.this.getApplication())
                .create(TaskViewModel.class);
        sharedViewModel=new ViewModelProvider(this).get(SharedViewModel.class);

//        taskViewModel.getAlltask().observe(this, new Observer<List<Task>>() {
//            @Override
//            public void onChanged(List<Task> tasks) {
//                for (Task task:tasks){
//                    Log.d(TAG, "onChanged: "+task.getTask());
//                }
//
//            }
//        });



        taskViewModel.getAlltask().observe(this, tasks -> {
//           recyclerView= new RecyclerView(tasks,);
//            recyclerViewAdapter
            OnTodoClickLister todoClickLister;
            recyclerViewAdapter=new RecyclerViewAdapter(tasks, this);
            recyclerView.setAdapter(recyclerViewAdapter);



        });

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(view -> {
//                Task task=new Task("Todo", Priority.HIGH, Calendar.getInstance().getTime(),
//                        Calendar.getInstance().getTime(),false);
//                TaskViewModel.insert(task);
            showBottomDialog();


        });
    }

    private void showBottomDialog() {
        bottomSheetFragment.show(getSupportFragmentManager(),bottomSheetFragment.getTag());

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onTodoClick(Task task) {
        //Log.d("Click", "onTodoClick: " + task.getTask());
        sharedViewModel.selectItem(task);
        sharedViewModel.setIsEdit(true);
        showBottomDialog();


    }

    @Override
    public void onTodoRadioButton(Task task) {
        TaskViewModel.delete(task);
        recyclerViewAdapter.notifyDataSetChanged();

    }
}