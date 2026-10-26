package com.example.nandivishal;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.database.annotations.NotNull;

public class LogInOne extends AppCompatActivity {

    private View decorview;
    DatabaseHelper myDb;
String Name,Password,Check;
    EditText name,passwordone,passwordtwo;
    Button set;
    DatabaseReference referenceName,reference,databaseReference,referenceUnique;
    FirebaseDatabase rootnode;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.log_in_one);
        Window window = this.getWindow();
        window.setStatusBarColor(this.getResources().getColor(R.color.golden));
        name=findViewById(R.id.nameinput);
        passwordone=findViewById(R.id.passwordinputone);
        passwordtwo=findViewById(R.id.passwordinputtwo);
        set=findViewById(R.id.set);
        FirebaseApp.initializeApp(this);
myDb=new DatabaseHelper(this);



AddData();

    }



    public void AddData()
    {
        set.setOnClickListener(new View.OnClickListener() {
                                   @Override
                                   public void onClick(View v)
                                   {
                                       if (!passwordone.getText().toString().equals("") && !passwordtwo.getText().toString().equals("") && !name.getText().toString().equals(""))
                                       {

                                           if (passwordone.getText().toString().matches(passwordtwo.getText().toString()))
                                           {
                                               reference= FirebaseDatabase.getInstance().getReference().child("Users").child("text");
                                               reference.setValue("Welcomer User");


                                              referenceUnique= FirebaseDatabase.getInstance().getReference().child("Users").child(name.getText().toString());
                                               referenceUnique.addValueEventListener(new ValueEventListener() {
                                                   @Override
                                                   public void onDataChange(@NonNull DataSnapshot snapshot)
                                                   {
                                                       if (snapshot.exists())
                                                       {
                                                           Toast.makeText(LogInOne.this, "User Name "+name.getText().toString()+" Is Already Taken", Toast.LENGTH_SHORT).show();
                                                           name.setText("");
                                                           passwordone.setText("");
                                                           passwordtwo.setText("");
                                                       }

                                                       else {
                                                           CreateUser();
                                                       }
                                                   }


                                                   @Override
                                                   public void onCancelled(@NonNull DatabaseError error)
                                                   {

                                                   }
                                               });





                                           }

                                           else {
                                               Toast.makeText(LogInOne.this, "password does not match", Toast.LENGTH_LONG).show();
                                           }

                                       }

                                       else {
                                           Toast.makeText(LogInOne.this, "please enter data", Toast.LENGTH_SHORT).show();
                                       }


                                   }
                               }
        );
    }

    private void CreateUser() {
        rootnode=FirebaseDatabase.getInstance();
        referenceName=  rootnode.getReference("Users").child(name.getText().toString()).child("user_password");
        referenceName.setValue(passwordtwo.getText().toString());
        boolean isInserted = myDb.insertData(name.getText().toString(), passwordtwo.getText().toString());
        Toast.makeText(this, "Welcome "+name.getText().toString(), Toast.LENGTH_SHORT).show();
        openMainActivity();
    }


    public void openMainActivity()
    {

            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
            finish();


    }


}