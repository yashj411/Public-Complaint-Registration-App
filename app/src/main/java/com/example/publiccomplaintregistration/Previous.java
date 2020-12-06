package com.example.publiccomplaintregistration;

import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.Map;

public class Previous extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        final ListView listView;
        final ArrayList<String> data = new ArrayList<String>();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.previous);
        DatabaseReference mRef;
        mRef = FirebaseDatabase.getInstance().getReference("User");
        final ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>
                (this, android.R.layout.simple_list_item_1, data);
        listView = findViewById(R.id.listView);

        mRef.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                if (snapshot.exists()) {
                    Map map = (Map) snapshot.getValue();
                    String title = (String) map.get("title");
                    String description = (String) map.get("description");
                    String date = (String) map.get("date");
                    String department = (String) map.get("department");
                    String name = (String) map.get("name");

                    data.add(name+"\n"+title+"\n"+description+"\n"+date+"\n"+department);
                    Log.i("tag", "values" + map.toString());
                    arrayAdapter.notifyDataSetChanged();
                    listView.setAdapter(arrayAdapter);
                }
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot snapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}
