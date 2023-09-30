package com.example.dateutility;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.method.LinkMovementMethod;
import android.widget.TextView;

public class IActivity extends AppCompatActivity {

    TextView viewgithub,viewemail;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_iactivity);

        viewgithub = (TextView) findViewById(R.id.viewgithub);
        viewgithub.setMovementMethod(LinkMovementMethod.getInstance());

        viewemail = (TextView) findViewById(R.id.viewemail);
        viewemail.setMovementMethod(LinkMovementMethod.getInstance());


    }
}