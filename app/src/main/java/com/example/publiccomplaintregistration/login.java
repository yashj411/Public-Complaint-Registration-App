package com.example.publiccomplaintregistration;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class login extends AppCompatActivity {
    TextView dontHaveAnAccount;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        //redirecting user to the register or signup page
        //this type of onclick code is to be followed for all the buttons
        redirectToSignup();
    }
    private void redirectToSignup(){
        dontHaveAnAccount = findViewById(R.id.tVDontHaveAccount);
        dontHaveAnAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent signupIntent = new Intent(login.this,Signup.class);
                startActivity(signupIntent);
            }
        });
    }
}
