package com.example.pruebatecnica.includes;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.pruebatecnica.R;

public class toolbar {
    public static void show(AppCompatActivity activity, String title, Boolean upButton){
       Toolbar toolbar = activity.findViewById(R.id.toolbar);
       activity.setSupportActionBar(toolbar);
        activity.getSupportActionBar().setTitle(title);
        activity.getSupportActionBar().setDisplayHomeAsUpEnabled(upButton);
    }
}
