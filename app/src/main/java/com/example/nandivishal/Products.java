package com.example.nandivishal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Products extends AppCompatActivity {

    EditText pt_code,pt_name1,pt_name2,pt_type,pt_um,pt_tax_code;
    String pt_code1,pt_name3,pt_name4,pt_type1,pt_um1,pt_tax_code1;
    Button create_products,show_products;

    DatabaseHelperProduct ProductDb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_products);
        Window window = this.getWindow();
        window.setStatusBarColor(this.getResources().getColor(R.color.golden));
        ProductDb=new DatabaseHelperProduct(this);
        pt_code=findViewById(R.id.pt_code);
        pt_name1=findViewById(R.id.pt_description1);
        pt_name2=findViewById(R.id.pt_description2);
        pt_type=findViewById(R.id.pt_type);
        pt_um=findViewById(R.id.pt_um);
        pt_tax_code=findViewById(R.id.pt_tax_code);
        create_products=findViewById(R.id.create_Product);
        show_products=findViewById(R.id.view_products);
        AddProducts();
        ShowProduct();
    }

    private void ShowProduct()
    {
        show_products.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Products.this, ShowProducts.class);
                startActivity(intent);
            }
        });
    }

    public void AddProducts()
    {
        create_products.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {pt_code1=pt_code.getText().toString();
                pt_name3=pt_name1.getText().toString();
                pt_name4=pt_name2.getText().toString();
                pt_type1=pt_type.getText().toString();
                pt_um1=pt_um.getText().toString();
                pt_tax_code1=pt_tax_code.getText().toString();

                if (!pt_code1.equals("")&&!pt_name3.equals("")&&!pt_name4.equals("")&&!pt_type1.equals("")&&!pt_um1.equals("")&&!pt_tax_code1.equals(""))
                {
                    boolean isInserted =  ProductDb.insertData(pt_code1,pt_name3,pt_name4,pt_type1,pt_um1,pt_tax_code1);
                    pt_code.setText("");
                    pt_name1.setText("");
                    pt_name2.setText("");
                    pt_type.setText("");
                    pt_um.setText("");
                    pt_tax_code.setText("");
                    if (isInserted)
                    {
                        Toast.makeText(Products.this, "Product Added", Toast.LENGTH_SHORT).show();
                    }
                    else
                        Toast.makeText(Products.this, "Product Adding Failed , Try Again :/", Toast.LENGTH_SHORT).show();
                }
                else
                    Toast.makeText(Products.this, "Please Enter Data", Toast.LENGTH_SHORT).show();


            }
        });
    }
}