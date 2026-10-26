package com.example.nandivishal;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import java.text.DateFormat;
import java.util.Calendar;

public class Price extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {

    EditText pr_code,pr_per_unit,pr_pr,pr_mrp,pr_cm_type,pr_default;
    String pr_code1,pr_eff_date1,pr_per_unit1,pr_pr1,pr_mrp1,pr_cm_type1,pr_default1;
    Button create_price,show_price,pr_date;
DatabaseHelperPrice PriceDb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_price);
        Window window = this.getWindow();
        window.setStatusBarColor(this.getResources().getColor(R.color.golden));
        PriceDb=new DatabaseHelperPrice(this);
        pr_code=findViewById(R.id.pr_code);
        pr_date=findViewById(R.id.date_picker);
        pr_per_unit=findViewById(R.id.pr_pt_unit);
        pr_pr=findViewById(R.id.pr_price);
        pr_mrp=findViewById(R.id.pr_mrp);
        pr_cm_type=findViewById(R.id.pr_type);
        pr_default=findViewById(R.id.pr_default);
        create_price=findViewById(R.id.create_price);
        show_price=findViewById(R.id.view_price);
        CreatePrice();
        OpenDatePicker();
        ShowPrice();
    }

    private void OpenDatePicker()
    {
        pr_date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFragment datePicker= new com.example.nandivishal.DatePickerDialog();
                datePicker.show(getSupportFragmentManager(),"Enter Price date");
            }
        });
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth)
    {
        Calendar c = Calendar.getInstance();
        c.set(Calendar.YEAR,year);
        c.set(Calendar.MONTH,month);
        c.set(Calendar.DAY_OF_MONTH,dayOfMonth);
        pr_eff_date1= DateFormat.getDateInstance(DateFormat.MEDIUM).format(c.getTime());
    }

    private void CreatePrice()
    {

        create_price.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
pr_code1=pr_code.getText().toString();
                pr_per_unit1=pr_per_unit.getText().toString();
                pr_pr1=pr_pr.getText().toString();
                pr_mrp1=pr_mrp.getText().toString();
                pr_cm_type1=pr_cm_type.getText().toString();
                pr_default1=pr_default.getText().toString();
                if (!pr_code1.equals("")&&!pr_per_unit1.equals("")&&!pr_pr1.equals("")&&!pr_mrp1.equals("")&&!pr_cm_type1.equals("")&&!pr_default1.equals(""))
                {
                    if (pr_default1.matches("y")||pr_default1.matches("n"))
                    {

                        if (!(pr_eff_date1 == null))
                        {
                            boolean isInserted = PriceDb.insertData(pr_code1, pr_eff_date1, pr_per_unit1, pr_pr1, pr_mrp1, pr_cm_type1, pr_default1);
                            pr_code.setText("");
                            pr_per_unit.setText("");
                            pr_pr.setText("");
                            pr_mrp.setText("");
                            pr_cm_type.setText("");
                            pr_default.setText("");

                            if (isInserted) {
                                Toast.makeText(Price.this, "Product Added", Toast.LENGTH_SHORT).show();
                            } else
                                Toast.makeText(Price.this, "Product Adding Failed , Try Again :/", Toast.LENGTH_SHORT).show();
                        }
else
                            Toast.makeText(Price.this, "Please press the button 'enter price date and select the date you want'", Toast.LENGTH_LONG).show();

                    }

                    else
                        Toast.makeText(Price.this, "Price Default should cointain only (y or n)", Toast.LENGTH_SHORT).show();

                }
                else
                    Toast.makeText(Price.this, "Please Enter Data", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void ShowPrice()
    {
        show_price.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Price.this, ShowPrice.class);
                startActivity(intent);
            }
        });
    }


}