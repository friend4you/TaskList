package com.example.vladarsenyuk.tasklist;

import android.graphics.drawable.Icon;
import android.media.Image;
import android.text.format.DateFormat;
import android.text.format.Time;

import java.text.SimpleDateFormat;
import java.util.Date;

import static android.R.attr.name;

/**
 * Created by VladArsnyuk on 19.10.2016.
 */

public class Task {

    private String title;
    private String info;
    private float priority;
    private String dateS;
    private int img;

    public int getImg() {
        return img;
    }

    public void setImg(int img) {
        this.img = img;
    }

    public String getDateS() {
        return dateS;
    }

    public void setDateS(String dateS) {
        this.dateS = dateS;
    }

    public float getPriority() {
        return priority;
    }

    public void setPriority(float priority) {
        this.priority = priority;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public String getName() {
        return title;
    }

    public void setName(String name) {
        this.title = name;
    }

    @Override
    public String toString() {
        return getName();
    }

    public Task(String title, String info, float priority, String dateS, int img) {
        this.title = title;
        this.info = info;
        this.priority = priority;
        this.dateS = dateS;
        this.img = img;
    }
}
