package com.example.nandivishal;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.gms.tasks.OnCanceledListener;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.database.annotations.NotNull;

import java.util.HashMap;
import java.util.Map;

public class 9Customer extends AppCompatActivity {
    DatabaseHelperCustomer CustomerDb;
    DatabaseHelper databaseHelper;
    EditText cm_code,cm_name,cm_region,cm_sls_psn,cm_type,cm_lst_no;
    String cm_code1,cm_name1,cm_region1,cm_sls_psn1,cm_lst_no1;
    String cm_type1;
    Button create_customer,show_customer;
    FirebaseDatabase rootNode;
    DatabaseReference reference,referenceMaxId,referenceFirst;
    Cursor res,resName;
    UserHelperClass userHelperClass;
    Boolean success=true;
    String user_name;
    int maxId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer);
        Window window = this.getWindow();
        window.setStatusBarColor(this.getResources().getColor(R.color.golden));
        CustomerDb=new DatabaseHelperCustomer(this);
        databaseHelper=new DatabaseHelper(this);
        cm_code=findViewById(R.id.cm_code);
        cm_name=findViewById(R.id.cm_name);
        cm_region=findViewById(R.id.cm_region);
        cm_sls_psn=findViewById(R.id.cm_sls_pns);
        cm_type=findViewById(R.id.cm_type);
        cm_lst_no=findViewById(R.id.cm_lst_no);
        create_customer=findViewById(R.id.create_customer);
        show_customer=findViewById(R.id.view_customer);
        FirebaseApp.initializeApp(this);

        getUserName();

        reference=FirebaseDatabase.getInstance().getReference("Users").child(user_name).child("Customers");
        res=CustomerDb.getAllData();
        AddCustomers();
        Showcustomer();
    }

    private void getUserName() {
        resName=databaseHelper.getAllData();
        if (resName.getCount()== 0)
        {
            return;
        }
        else {
            StringBuffer buffer = new StringBuffer();
            while (resName.moveToNext())
            {
                buffer.append(resName.getString(1));
            }

            user_name=buffer.toString();
        }
    }


    private void Showcustomer()
    {
        show_customer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Customer.this, ShowCustomer.class);
                startActivity(intent);
            }
        });
    }

    public void AddCustomers()
    {
        create_customer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {cm_code1=cm_code.getText().toString();
                cm_name1=cm_name.getText().toString();
                cm_region1=cm_region.getText().toString();
                cm_sls_psn1=cm_sls_psn.getText().toString();
                cm_lst_no1=cm_lst_no.getText().toString();
                cm_type1=cm_type.getText().toString();
                if (!cm_code1.equals("")&&!cm_name1.equals("")&&!cm_region1.equals("")&&!cm_sls_psn1.equals("")&&!cm_type1.equals("")&&!cm_lst_no1.equals(""))
                {
                    CustomerDb.insertData(cm_code1,cm_name1,cm_region1,cm_sls_psn1,cm_type1,cm_lst_no1);
                    cm_code.setText("");
                    cm_name.setText("");
                    cm_region.setText("");
                    cm_sls_psn.setText("");
                    cm_lst_no.setText("");
                    cm_type.setText("");
                    setOnlineData();
                }
                else
                    Toast.makeText(Customer.this, "Please Enter Data", Toast.LENGTH_SHORT).show();


            }
        });
    }

    public void setOnlineData()
    {



            userHelperClass = new UserHelperClass(cm_code1, cm_name1, cm_region1, cm_sls_psn1, cm_type1, cm_lst_no1);


               reference.child(cm_name1).setValue(userHelperClass).addOnCompleteListener(new OnCompleteListener<Void>() {
                   @Override
                   public void onComplete(@NonNull Task<Void> task) {

                       Toast.makeText(Customer.this, "Successfully Created A New Customer", Toast.LENGTH_SHORT).show();
                   }
               }).addOnCanceledListener(new OnCanceledListener() {
                   @Override
                   public void onCanceled() {
                       Toast.makeText(Customer.this, "Customer Adding Failed", Toast.LENGTH_SHORT).show();
                   }
               });


    }

}