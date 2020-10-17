package com.example.publiccomplaintregistration;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Signup extends AppCompatActivity {
    EditText etUsername, etemail, etPassword, etConfirmPassword;
    Button registerbtn;
    private FirebaseAuth mAuth = FirebaseAuth.getInstance();
    ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup);
        register();
        registerFromGoogle();
    }

    private void registerFromGoogle() {

    }

    private void register() {

        etemail = findViewById(R.id.ETEmail);
        etPassword = findViewById(R.id.ETPassword);
        registerbtn = findViewById(R.id.btnLogin);
        registerbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = etemail.getText().toString();
                String password = etemail.getText().toString();

                mAuth.createUserWithEmailAndPassword(email, password)
                        .addOnCompleteListener(Signup.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    // Sign in success, update UI with the signed-in user's information
                                    Log.d("Signup", "createUserWithEmail:success");
                                    Toast.makeText(Signup.this, "Authentication Success.",
                                            Toast.LENGTH_SHORT).show();
                                    FirebaseUser user = mAuth.getCurrentUser();
                                    Intent mainActivity = new Intent(Signup.this, MainActivity.class);
                                    startActivity(mainActivity);
                                } else {
                                    // If sign in fails, display a message to the user.
                                    Log.w("Signup", "createUserWithEmail:failure", task.getException());
                                    Toast.makeText(Signup.this, "Authentication failed ",
                                            Toast.LENGTH_SHORT).show();
                                }

                            }
                        });

            }
        });
    }
}
