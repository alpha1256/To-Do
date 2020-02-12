package com.example.to_do;

import android.os.Parcelable;

public class userTask {
    private String taskName;
    private String taskDescription;

    public userTask()
    {
        taskName = null;
        taskDescription = null;
    }

    public userTask(String name, String descrip)
    {
        taskName = name;
        taskDescription = descrip;
    }

    /**
     *
     * @return task name
     */
    String getTName()
    {
        return taskName;
    }

    /**
     *
     * @return task description
     */
    String getTDescrip()
    {
        return taskDescription;
    }

    public void setTName(String name)
    {
        taskName = name;
    }

    public void setTdescrip(String tdescrip)
    {
        taskDescription = tdescrip;
    }
}
