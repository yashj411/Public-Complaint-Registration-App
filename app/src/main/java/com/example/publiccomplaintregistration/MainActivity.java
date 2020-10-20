package com.example.publiccomplaintregistration;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity implements FirebaseAuth.AuthStateListener {

    Button signoutBtn,registerBtn,previousBtn,contactAdminBtn,guideLinesBtn;

    @Override
    protected void onStop() {
        super.onStop();
        FirebaseAuth.getInstance().removeAuthStateListener(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mAuth.signOut();
    }

    @Override
    protected void onStart() {
        super.onStart();
        FirebaseAuth.getInstance().addAuthStateListener(this);
    }
    public FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mAuth = FirebaseAuth.getInstance();
        signout();
        register();
        previous();
        guideLines();
        contactAdmin();
    }

    private void previous() {
        previousBtn = findViewById(R.id.btnPrevious);
        previousBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent previous = new Intent(getApplicationContext(),Previous.class);
                startActivity(previous);
            }
        });
    }

    private void guideLines(){
        guideLinesBtn = findViewById(R.id.btnGuidelines);
        guideLinesBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent guideLines = new Intent(getApplicationContext(),Guidelines.class);
                startActivity(guideLines);
            }
        });
    }

    private void contactAdmin() {
        contactAdminBtn  = findViewById(R.id.btnContactAdmin);
        contactAdminBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent loginIntent = new Intent(getApplicationContext(),ContactAdmin.class);
                startActivity(loginIntent);
            }
        });
    }
    private void signout(){
        signoutBtn  = findViewById(R.id.btnSignout);
        signoutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent loginIntent = new Intent(getApplicationContext(),login.class);
                startActivity(loginIntent);
            }
        });
    }
    private void register(){
        registerBtn  = findViewById(R.id.btnRegister);
        registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent RegisterIntent = new Intent(getApplicationContext(),Register.class);
                startActivity(RegisterIntent);
            }
        });
    }

    @Override
    public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
        if(FirebaseAuth.getInstance().getCurrentUser() == null){
            Intent loginIntent = new Intent(MainActivity.this,login.class);
            startActivity(loginIntent);
        }
        else{
            Log.i("TAG", "onAuthStateChanged: "
                    +FirebaseAuth.getInstance().getCurrentUser().getEmail());;
        }
    }
}