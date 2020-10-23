package com.example.publiccomplaintregistration;

import android.content.ContentResolver;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

public class Register extends AppCompatActivity {
    EditText tVName,tVDate,tVDepartment,tVTitle,tVDescription;
    Button button4,button7;
    StorageReference mStorageRef;
     public Uri imguri;
    DatabaseReference reff;
    long maxid=0;
    String status="Due";
    String imageid=null;
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
        button7=findViewById(R.id.button7);
        mStorageRef= FirebaseStorage.getInstance().getReference("Images");
        user=new User();
        reff= FirebaseDatabase.getInstance().getReference().child("User");

         button7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Filechooser();
            }
        });

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
                Fileuploader();
                user.setImageID(imageid);
                reff.child(String.valueOf(maxid+1)).setValue(user);
                Toast.makeText(Register.this,"Your complaint has been registered successfully",Toast.LENGTH_SHORT).show();
                 openActivity_main();

            }
        });
    }

    private void openActivity_main(){
        Intent intent2=new Intent(this,MainActivity.class);
        startActivity(intent2);
    }

    private String getExtension(Uri uri){
        ContentResolver cr=getContentResolver();
        MimeTypeMap mimeTypeMap=MimeTypeMap.getSingleton();
        return mimeTypeMap.getExtensionFromMimeType(cr.getType(uri));
    }

    private void Fileuploader(){
        imageid=System.currentTimeMillis()+"."+getExtension(imguri);
        StorageReference Ref=mStorageRef.child(System.currentTimeMillis()+"."+getExtension(imguri));
        Ref.putFile(imguri)
                .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        // Get a URL to the uploaded content
                        // Uri downloadUrl = taskSnapshot.getDownloadUrl();
                         Toast.makeText(Register.this, "Image Uploaded successfully", Toast.LENGTH_SHORT).show();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception exception) {
                        // Handle unsuccessful uploads
                        // ...
                    }
                });
    }

    private void Filechooser(){
        Intent intent1=new Intent();
        intent1.setType("image/*");
        intent1.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent1,1);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==1 && resultCode==RESULT_OK && data!=null && data.getData()!=null)
        {
            imguri=data.getData();
        }
    }

}
