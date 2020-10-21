package com.example.publiccomplaintregistration;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Register extends AppCompatActivity {
    EditText tVName,tVDate,tVDepartment,tVTitle,tVDescription;
    Button button4;
    DatabaseReference reff;
    long maxid=0;
    String status="Due";
    User user;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);
        tVName= findViewById(R.id.tVName);
        tVDate= findViewById(R.id.tVDate);
        tVDepartment= findViewById(R.id.tVDepartment);
        tVTitle= findViewById(R.id.tVTitle);
        tVDescription= findViewById(R.id.tVDescription);
        button4= findViewById(R.id.button4);
        user=new User();
        reff= FirebaseDatabase.getInstance().getReference().child("User");
        reff.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists())
                    maxid=(snapshot.getChildrenCount());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                user.setName(tVName.getText().toString().trim());
                user.setDate(tVDate.getText().toString().trim());
                user.setDepartment(tVDepartment.getText().toString().trim());
                user.setTitle(tVTitle.getText().toString().trim());
                user.setDescription(tVDescription.getText().toString().trim());
                user.setStatus(status);
                reff.child(String.valueOf(maxid+1)).setValue(user);
            }
        });
    }
}
