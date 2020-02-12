package com.example.to_do;


import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;


import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class userTaskAdapter extends RecyclerView.Adapter<userTaskAdapter.MyViewHolder> {
    private List<userTask> taskList;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView Tname, Tdescription;
        public CheckBox checkBox;
        public MyViewHolder (View view)
        {
            super(view);
            Tname = (TextView) view.findViewById(R.id.name);
            Tdescription = (TextView) view.findViewById(R.id.description);
            checkBox = (CheckBox) view.findViewById(R.id.checkOne);
            checkBox.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (checkBox.isChecked())
                    {
                        removeAt(getAdapterPosition());
                        notifyDataSetChanged();
                    }
                }
            });

            Tname.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    removeAt(getAdapterPosition());
                    Log.d("LongClick", "This works");
                    notifyDataSetChanged();
                    return false;
                }
            });
        }
    }

    public userTaskAdapter(List<userTask> tlist)
    {
        this.taskList = tlist;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewtype)
    {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.tasklayout,parent,false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position)
    {
        userTask task = taskList.get(position);
        holder.Tname.setText(task.getTName());
        holder.Tdescription.setText(task.getTDescrip());
        holder.checkBox.setChecked(false);
    }

    @Override
    public int getItemCount()
    {
        return taskList.size();
    }

    /**
     * remove from arraylist and file
     * @param position
     */
    public void removeAt(int position)
    {
        taskList.remove(position);
    }

}
