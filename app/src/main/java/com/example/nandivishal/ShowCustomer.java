package com.example.nandivishal;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.DialogInterface;
import android.database.Cursor;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ShowCustomer extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private ExampleAdapter mAdapter;
    private RecyclerView.LayoutManager mlayoutManager;
    DatabaseHelperCustomer CustomerDb;
    Cursor resCus,resDelete,res,resName;
    TextView textView;
    ArrayList<ExampleItem> exampleList = new ArrayList<>();
    DatabaseReference reference,reference2,referenceDelete;
    DatabaseHelper databaseHelper;
    String value,id,user_name;
TextView text_code,text_name,text_region,text_sls_psn,text_type,text_lst_no;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_customer);
        CustomerDb=new DatabaseHelperCustomer(this);
        databaseHelper=new DatabaseHelper(this);
        ArrayList<ExampleItem> exampleList = new ArrayList<>();
        mRecyclerView=findViewById(R.id.recycler_customer);
        mRecyclerView.setHasFixedSize(true);
        mlayoutManager = new LinearLayoutManager(ShowCustomer.this);
        mRecyclerView.setLayoutManager(mlayoutManager);
        getUserName();
        resCus = CustomerDb.getAllData();
        resDelete = CustomerDb.getAllData();
        textView=findViewById(R.id.customer_empty);
        fetchData();
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

    @Override
    protected void onStart() {
        super.onStart();
        getWindow().setStatusBarColor(Color.parseColor("#162840"));
    }



    private void BottomCardView()
    {
        mAdapter.setOnItemClickListener(new ExampleAdapter.OnItemClickListener() {
            @Override
            public void onDeleteClick(int position)
            {
                AlertDialog.Builder alert=new AlertDialog.Builder(ShowCustomer.this);
                alert.setTitle("Deleting Customer");
                alert.setMessage("Are Sure You Want To Delete This");
                alert.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        resDelete.moveToPosition(position);
                        id=resDelete.getString(0);

                        String postKeydelete = exampleList.get(position).getmLine1();
                        referenceDelete=  FirebaseDatabase.getInstance().getReference("Users").child(user_name).child("Customers").child(postKeydelete);
                        referenceDelete.addValueEventListener(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot dataSnapshot)
                            {
                                referenceDelete.removeValue();
                                CustomerDb.deleteData(id);
                                mAdapter.notifyItemRemoved(position);
                                Toast.makeText(ShowCustomer.this, "Customer Deleted Successfully", Toast.LENGTH_SHORT).show();
                            }
                            @Override
                            public void onCancelled(@NonNull DatabaseError error)
                            {

                            }

                        });
                        dialog.dismiss();
                    }
                }).setNegativeButton("No", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which)
                    {
                        dialog.dismiss();
                    }
                });
                alert.create().show();


            }

            @Override
            public void onItemClick(int position)
            {
                resCus.moveToPosition(position);
                value= resCus.getString(0);
                String postKey = exampleList.get(position).getmLine1();
                reference2=  FirebaseDatabase.getInstance().getReference("Users").child(user_name).child("Customers").child(postKey);
                reference2.addValueEventListener(new ValueEventListener() {
                    String name1,code1,region1,sls_psn1,type1,lst_no1;
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot)
                    {

                        name1 = dataSnapshot.child("cust_name").getValue(String.class);
                        code1= dataSnapshot.child("cust_code").getValue(String.class);
                        region1= dataSnapshot.child("cust_region").getValue(String.class);
                        sls_psn1= dataSnapshot.child("cust_sls_psn").getValue(String.class);
                        type1= dataSnapshot.child("cust_type").getValue(String.class);
                        lst_no1= dataSnapshot.child("cust_tax").getValue(String.class);

                        BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(ShowCustomer.this);
                        bottomSheetDialog.setContentView(R.layout.bottom_card_view);
                        bottomSheetDialog.setCanceledOnTouchOutside(false);




                        text_code=bottomSheetDialog.findViewById(R.id.cust_code77);
                        text_name=bottomSheetDialog.findViewById(R.id.cust_name77);
                        text_region=bottomSheetDialog.findViewById(R.id.cust_region77);
                        text_sls_psn=bottomSheetDialog.findViewById(R.id.cust_sls_psn77);
                        text_type=bottomSheetDialog.findViewById(R.id.cust_type77);
                        text_lst_no=bottomSheetDialog.findViewById(R.id.cust_lst_no77);

                        text_code.setText("Customer Code :  "+code1);
                        text_name.setText("Customer Name :  "+name1);
                        text_region.setText("Customer Region :  "+region1);
                        text_sls_psn.setText("Customer Sales :  "+sls_psn1);
                        text_type.setText("Customer Type :  "+type1);
                        text_lst_no.setText("Customer Lst No.  : "+lst_no1);

                        bottomSheetDialog.show();
                    }
                    @Override
                    public void onCancelled(@NonNull DatabaseError error)
                    {

                    }

                });



            }

        });
    }

    private void fetchData()
    {
        reference=  FirebaseDatabase.getInstance().getReference("Users").child(user_name).child("Customers");
        reference.addValueEventListener(new ValueEventListener() {
            String cust_name,cust_code;
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot)
            {
                if(snapshot.getChildrenCount()==0)
                {
                    textView.setText("Entered Customers Will Appear Here");
                }
                else {
                    for (DataSnapshot snapshot2 : snapshot.getChildren()) {

                       // cust_name = snapshot2.child("cust_name").getValue(String.class);
                       // cust_code = snapshot2.child("cust_code").getValue(String.class);
                        //Add(cust_name,cust_code);

                        exampleList.add(new ExampleItem(R.drawable.person, snapshot2.child("cust_name").getValue(String.class), snapshot2.child("cust_code").getValue(String.class)));



                    }
                    mAdapter = new ExampleAdapter(exampleList);
                    mRecyclerView.setAdapter(mAdapter);
                   BottomCardView();
                }

            }


            @Override
            public void onCancelled(@NonNull DatabaseError error)
            {

            }
        });



    }

    public void Add(String getCust_name, String getCust_code)
    {
        exampleList.add(new ExampleItem(R.drawable.person,getCust_name,getCust_code));
        mRecyclerView.setHasFixedSize(true);
        mlayoutManager = new LinearLayoutManager(ShowCustomer.this);
        mAdapter= new ExampleAdapter(exampleList);
        mRecyclerView.setLayoutManager(mlayoutManager);
        mRecyclerView.setAdapter(mAdapter);
        //BottomCardView();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater=getMenuInflater();
        inflater.inflate(R.menu.example_menu,menu);

        MenuItem searchItem=menu.findItem(R.id.action_search);
        SearchView searchView=(SearchView) searchItem.getActionView();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                mAdapter.getFilter().filter(newText);
                return false;
            }
        });

        return true;
    }
}