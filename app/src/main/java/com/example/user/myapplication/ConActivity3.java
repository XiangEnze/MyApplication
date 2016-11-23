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
import android.telephony.SmsManager;
import android.widget.EditText;
import android.widget.Toast;





public class ConActivity3 extends Activity {

    Button buttonSend;
    EditText textPhoneNo;
    EditText Num1;
    EditText Num2;
    EditText Num3;
    EditText imel;
    MyApp myApp;



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Get the view from new_activity.xml
        setContentView(R.layout.activity_con3);

        buttonSend = (Button) findViewById(R.id.buttonSend);
        textPhoneNo = (EditText) findViewById(R.id.editTextPhoneNo);

        Num1 = (EditText) findViewById(R.id.Num1);
        Num2 = (EditText) findViewById(R.id.Num2);
        Num3 = (EditText) findViewById(R.id.Num3);
        myApp = (MyApp)getApplication();



        buttonSend.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(ConActivity3.this,
                        MainActivity.class);
                startActivity(myIntent);
                String phoneNo = myApp.gettextPhoneNo();
                String sms ="0000,A71,"+Num1.getText().toString()+","+Num2.getText().toString()+","+Num3.getText().toString();

                try {
                    SmsManager smsManager = SmsManager.getDefault();
                    smsManager.sendTextMessage(phoneNo, null, sms, null, null);

                    Toast.makeText(getApplicationContext(), "SMS Sent!",
                            Toast.LENGTH_LONG).show();
                } catch (Exception e) {
                    Toast.makeText(getApplicationContext(),
                            "SMS faild, please try again later!",
                            Toast.LENGTH_LONG).show();
                    e.printStackTrace();

                }

            }
        });
    }
}