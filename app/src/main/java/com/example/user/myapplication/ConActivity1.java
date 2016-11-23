package com.example.user.myapplication;

import android.content.SharedPreferences;
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
import android.widget.EditText;
import android.widget.Toast;

public class ConActivity1 extends Activity {

    Button button3;
    MyApp myApp;
    EditText textPhoneNo;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Get the view from new_activity.xml
        setContentView(R.layout.activity_con1);

        myApp = (MyApp)getApplication();
        textPhoneNo = (EditText) findViewById(R.id.editTextPhoneNo);


        button3 = (Button) findViewById(R.id.button3);
        button3.setOnClickListener(new OnClickListener() {
            public void onClick(View arg0) {

                // Start NewActivity.class
                Intent myIntent = new Intent(ConActivity1.this,
                        ConActivity2.class);
                myApp.settextPhoneNo(textPhoneNo.getText().toString());
                SharedPreferences mySharedPreferences= getSharedPreferences("test",
                        Activity.MODE_PRIVATE);
                SharedPreferences.Editor editor = mySharedPreferences.edit();
                editor.putString("phone", textPhoneNo.getText().toString());
                editor.commit();
                startActivity(myIntent);
            }
        });


    }


}