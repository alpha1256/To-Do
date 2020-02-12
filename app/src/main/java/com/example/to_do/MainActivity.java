package com.example.to_do;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.view.View;
import android.widget.Adapter;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.Console;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;
    private List<userTask>  tasklist= new ArrayList<>();
    private userTaskAdapter adapter;
    //private Context context;
    private static Bundle mBundleState;
    private final String KEY_RECYCLER_STATE = "recycler_state";
    private Parcelable listState;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = (RecyclerView) findViewById(R.id.recycleOne);

        recyclerView.setHasFixedSize(true);
        recyclerView.addItemDecoration(new DividerItemDecoration(this,
                DividerItemDecoration.VERTICAL));

        adapter = new userTaskAdapter(tasklist);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);
        preparetestdata();




    }

    private void preparetestdata()
    {
        String data = "";

        //File file = new File("assets//task.txt");
        try {

            //InputStream fis = MainActivity.this.getResources().getAssets().open("task.txt");
            InputStream fis = new FileInputStream(new File(getFilesDir(), "task.txt"));
            DataInputStream in = new DataInputStream(fis);
            BufferedReader br = new BufferedReader(new InputStreamReader(in));

            while ((data = br.readLine()) != null)
            {
                String delims = "[:]+";
                String[] temp = data.split(delims);
                String tempTask = new String();
                String tempDescription = new String();
                for (int i=0; i < temp.length; i++)
                {
                    if(i == 0)
                        tempTask = temp[i];
                    else
                        tempDescription = temp[i];
                }
                userTask tempUser = new userTask(tempTask, tempDescription);
                tasklist.add(tempUser);
            }

        } catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    /**
     * This function will add new task to task list
     * @param v
     */
    public void onAdd(View v)
    {
        EditText editTaskTitle = (EditText) findViewById(R.id.taskTitle);
        EditText editDescription = (EditText) findViewById(R.id.taskDescription);
        String tname = editTaskTitle.getText().toString();
        String tdescrip = editDescription.getText().toString();
        userTask task = new userTask(tname, tdescrip);
        editTaskTitle.setText("");
        editDescription.setText("");
        tasklist.add(task);
        Log.d("Tname :", tname);
        Log.d("tdescription :", tdescrip);
        try {
            FileOutputStream stream = new FileOutputStream(new File(getFilesDir(), "task.txt"));

            int counter =0;
            for (int i =0; i < tasklist.size(); i++)
            {

                String total = tasklist.get(i).getTName() + " : " + tasklist.get(i).getTDescrip() + "\n";
                stream.write(total.getBytes());
                counter++;
            }
            stream.close();
            adapter.notifyDataSetChanged();
            //recyclerView
            Log.d("UpList", String.valueOf(counter));
        }catch(IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onStop()
    {
        super.onStop();
        mBundleState = new Bundle();
        listState = recyclerView.getLayoutManager().onSaveInstanceState();
        mBundleState.putParcelable(KEY_RECYCLER_STATE, listState);
        onUpdate();
    }


    @Override
    public void onResume()
    {
        super.onResume();
        if (mBundleState != null)
        {
            listState = mBundleState.getParcelable(KEY_RECYCLER_STATE);
            recyclerView.getLayoutManager().onRestoreInstanceState(listState);
        }
    }

    void onUpdate()
    {
        try {
            FileOutputStream stream = new FileOutputStream(new File(getFilesDir(),"task.txt"));
            int counter =0;
            for (int i =0; i < tasklist.size(); i++)
            {

                String total = tasklist.get(i).getTName() + " : " + tasklist.get(i).getTDescrip() + "\n";
                stream.write(total.getBytes());
                counter++;
            }
            stream.close();
            //adapter.notifyDataSetChanged();
            //recyclerView
            Log.d("UpList", String.valueOf(counter));
        }catch(IOException e) {
            e.printStackTrace();
        }
    }



}



