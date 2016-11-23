package com.example.user.myapplication;

import android.os.Bundle;
import android.app.Activity;

public class LocActivity extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Get the view from new_activity.xml
        setContentView(R.layout.activity_loc);
    }
}