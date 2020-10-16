package com.example.publiccomplaintregistration;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onStart() {
        super.onStart();
        //to redirect user to the login page
            Intent loginIntent = new Intent(MainActivity.this,login.class);
            startActivity(loginIntent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }
}