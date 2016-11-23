package com.example.user.myapplication;

import android.os.Bundle;
import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.content.Intent;
import android.view.View;
import android.widget.EditText;

public class ConActivity2 extends Activity {
    Button button4;
    MyApp myApp;
    EditText imel;



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Get the view from new_activity.xml
        setContentView(R.layout.activity_con2);

        myApp = (MyApp)getApplication();
        imel = (EditText) findViewById(R.id.imel);

        button4 = (Button) findViewById(R.id.button4);
        button4.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {

                // Start NewActivity.class
                Intent myIntent = new Intent(ConActivity2.this,
                        ConActivity3.class);
                myApp.setImel(imel.getText().toString());
                startActivity(myIntent);
            }
        });
    }
}