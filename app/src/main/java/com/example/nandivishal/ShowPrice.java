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

public class ShowPrice extends AppCompatActivity {

    private RecyclerView prRecyclerView;
    private RecyclerView.Adapter prAdapter;
    private RecyclerView.LayoutManager prlayoutManager;
    DatabaseHelperPrice PriceDb;
    Cursor prres;
    TextView emptyText;
    ArrayList<ExampleItem> exampleListPrice = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_price);
        PriceDb=new DatabaseHelperPrice(this);
        prRecyclerView=findViewById(R.id.recycler_price);
        emptyText=findViewById(R.id.text_price);
        prres = PriceDb.getAllData();
        DeletePrice();
        ViewAll();
    }

    private void DeletePrice()
    {
        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {


            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target)
            {
                emptyText.setText("Entered Products Will Appear Here");
                return false;
            }

            @Override
            public void onSwiped(@NonNull final RecyclerView.ViewHolder viewHolder, int direction)
            {
                int positon=viewHolder.getAdapterPosition();
                prres.moveToPosition(positon);

                new AlertDialog.Builder(ShowPrice.this)
                        .setTitle("Warning!!")
                        .setMessage("Are You Sure You Want To Delete This")
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which)
                            {

                                String id = prres.getString(0);
                                Integer deletedRows=PriceDb.deleteData(id);

                                if (deletedRows>0)
                                {
                                    Toast.makeText(ShowPrice.this, "Data Deleted", Toast.LENGTH_SHORT).show();
                                }
                                else {
                                    Toast.makeText(ShowPrice.this, "Data not deleted", Toast.LENGTH_SHORT).show();
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
        }).attachToRecyclerView(prRecyclerView);
    }

    public void ViewAll()
    {
        if (prres.getCount()== 0)
        {
            emptyText.setText("Entered Price Will Appear Here");
            return;
        }
        else {
            while (prres.moveToNext())
            {
                exampleListPrice.add(new ExampleItem(R.drawable.perm,prres.getString(1),prres.getString(2)));
            }
            prRecyclerView.setHasFixedSize(true);
            prlayoutManager = new LinearLayoutManager(this);
            prAdapter= new ExampleAdapter(exampleListPrice);
            prRecyclerView.setLayoutManager(prlayoutManager);
            prRecyclerView.setAdapter(prAdapter);

        }

    }
}