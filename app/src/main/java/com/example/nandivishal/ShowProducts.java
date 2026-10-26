package com.example.nandivishal;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class ShowProducts extends AppCompatActivity
{
    private RecyclerView ptRecyclerView;
    private RecyclerView.Adapter ptAdapter;
    private RecyclerView.LayoutManager ptlayoutManager;
    DatabaseHelperProduct ProductDb;
    Cursor ptres;
    TextView empty;
    ArrayList<ExampleItem> exampleListProduct = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_products);
        ProductDb=new DatabaseHelperProduct(this);
        ptRecyclerView=findViewById(R.id.recycler_products);
        empty=findViewById(R.id.text_product);
        ptres = ProductDb.getAllData();
        ViewAll();
        DeleteProducts();
    }

    private void DeleteProducts()
    {
        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {


            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target)
            {
                empty.setText("Entered Products Will Appear Here");
                return false;
            }

            @Override
            public void onSwiped(@NonNull final RecyclerView.ViewHolder viewHolder, int direction)
            {
                int positon=viewHolder.getAdapterPosition();
                ptres.moveToPosition(positon);

                new AlertDialog.Builder(ShowProducts.this)
                        .setTitle("Warning!!")
                        .setMessage("Are You Sure You Want To Delete This")
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which)
                            {

                                String id = ptres.getString(0);
                                Integer deletedRows=ProductDb.deleteData(id);

                                if (deletedRows>0)
                                {
                                    Toast.makeText(ShowProducts.this, "Data Deleted", Toast.LENGTH_SHORT).show();
                                }
                                else {
                                    Toast.makeText(ShowProducts.this, "Data not deleted", Toast.LENGTH_SHORT).show();
                                }
                            }
                        })
                        .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which)
                            {
                                dialog.dismiss();
                            }
                        }).create().show();

            }
        }).attachToRecyclerView(ptRecyclerView);
    }

    public void ViewAll()
    {
       // ArrayList<ExampleItem> exampleListProduct = new ArrayList<>();
        //Cursor res = CustomerDb.getAllData();

        if (ptres.getCount()== 0)
        {
            return;
        }
        else {
            while (ptres.moveToNext())
            {
                exampleListProduct.add(new ExampleItem(R.drawable.person,ptres.getString(2),ptres.getString(1)));
            }
            ptRecyclerView.setHasFixedSize(true);
            ptlayoutManager = new LinearLayoutManager(this);
            ptAdapter= new ExampleAdapter(exampleListProduct);
            ptRecyclerView.setLayoutManager(ptlayoutManager);
            ptRecyclerView.setAdapter(ptAdapter);

        }

    }
}