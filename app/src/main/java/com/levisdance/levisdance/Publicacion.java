package com.levisdance.levisdance;

import android.graphics.drawable.Drawable;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.text.DateFormat;

/**
 * Created by edward on 18/09/17.
 */

public class Publicacion {

    private Drawable image;
    private int id;
    private String username;
    private String location;
    private String title;
    private String time;

    public Publicacion(Drawable image,int id, String username, String location, String title, String time) {

        this.image = image;
        this.id=id;
        this.username = username;
        this.location = location;
        this.title = title;
        this.time = time;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Drawable getImage() {
        return image;
    }

    public void setImage(Drawable image) {
        this.image = image;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

}
