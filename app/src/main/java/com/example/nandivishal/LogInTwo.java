package com.example.nandivishal;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class LogInTwo extends AppCompatActivity {


    DatabaseHelper myDb;
    String Name,Password,PasswordOne;
    TextView nameOutput;
    EditText passwordInput;
    Button go;
    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.log_in_two);
        myDb=new DatabaseHelper(this);
        ViewAll();
        //nameOutput=findViewById(R.id.name_output);
        passwordInput=findViewById(R.id.input_password);
        go=findViewById(R.id.go);
       // nameOutput.setText("Welcome "+Name);
        //Toast.makeText(this, "Welcome"+Name, Toast.LENGTH_SHORT).show();
        Window window = this.getWindow();
        window.setStatusBarColor(this.getResources().getColor(R.color.golden));
        buttonClick();
    }


    public void ViewAll()
    {
        Cursor res = myDb.getAllData();
        if (res == null) {
            return;
        }

        try {
            if (res.moveToFirst()) {
                Name = res.getString(1);
                Password = res.getString(2);
            }
        } finally {
            res.close();
        }
    }

    public void buttonClick()
    {
        go.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                PasswordOne=passwordInput.getText().toString();

                if (Password != null && PasswordOne.equals(Password))
                {
                    OpenActivity();
                }
                else
                Toast.makeText(LogInTwo.this, "Incorrect Password", Toast.LENGTH_SHORT).show();


            }
        });
    }

    public void OpenActivity()
    {
        Intent intent2 = new Intent(this, MainActivity.class);
        startActivity(intent2);
        finish();
    }
}