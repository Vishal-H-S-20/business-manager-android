package com.example.nandivishal;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private View decorview;
    DatabaseHelper myDb;
    TextView nameOutput;
    String Name1;
    CardView customers,products,price;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        myDb=new DatabaseHelper(this);
        nameOutput=findViewById(R.id.nameOutputtt);
        customers=findViewById(R.id.customer_cardView);
        products=findViewById(R.id.product_cardView);
        price=findViewById(R.id.price_cardView);


        customers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OpenCustomer();
            }
        });

        products.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OpenProduct();
            }
        });


       price.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               OpenPrice();
            }
        });


        ViewAll();
        nameOutput.setText(Name1);
        decorview=getWindow().getDecorView();
        decorview.setOnSystemUiVisibilityChangeListener(new View.OnSystemUiVisibilityChangeListener() {
            @Override
            public void onSystemUiVisibilityChange(int visibility) {

                if (visibility ==0)
                {
                    decorview.setSystemUiVisibility(hidestatusbar());
                }
            }
        });

    }









    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if (hasFocus)
        {
            decorview.setSystemUiVisibility(hidestatusbar());
        }
    }

    private int hidestatusbar()
    {
        return   View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                ;
    }

    public void ViewAll()
    {
        Cursor res = myDb.getAllData();

        if (res.getCount()== 0)
        {
            return;
        }
        else {
            StringBuffer buffer = new StringBuffer();
            while (res.moveToNext())
            {
                buffer.append(res.getString(1));
            }

            Name1=buffer.toString();

        }

    }

    public void OpenCustomer()
    {
        Intent intent2 = new Intent(this,Customer.class);
        startActivity(intent2);
    }

    public void OpenProduct()
    {
        Intent intent2 = new Intent(this,Products.class);
        startActivity(intent2);
    }

    public void OpenPrice()
    {
        Intent intent2 = new Intent(this,Price.class);
        startActivity(intent2);
    }
}