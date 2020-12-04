package com.example.publiccomplaintregistration;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class Last_slide extends AppCompatActivity {

    Button but1;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.last);

        but1= (Button) findViewById(R.id.homebut);

        but1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openActivity_main();
            }
        });

    }

    public void openActivity_main(){
        Intent intent3=new Intent(this,MainActivity.class);
        startActivity(intent3);
    }

}
