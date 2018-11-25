package com.jman.androidshowjokelib;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

public class ShowJokeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_joke);

       // String jokeFromJavaLib = getIntent().getStringExtra("javaLibJoke");
        String gceJokeFromJavaLib = getIntent().getStringExtra("GCEJoke");
        TextView textView = findViewById(R.id.joke_textview);

        textView.setText(gceJokeFromJavaLib);
    }

}
