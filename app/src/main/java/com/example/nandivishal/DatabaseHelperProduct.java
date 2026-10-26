package com.example.nandivishal;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DatabaseHelperProduct extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "Product.db";
    public static final String TABLE_NAME = "Product_table";
    public static final String COL_1 = "ID";
    public static final String COL_2 = "pt_code";
    public static final String COL_3 = "pt_description";
    public static final String COL_4 = "pt_description1";
    public static final String COL_5 = "pt_type";
    public static final String COL_6 = "pt_um";
    public static final String COL_7 = "pt_tax_code";

    public DatabaseHelperProduct(@Nullable Context context)
    {
        super(context,DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db)
    {
        db.execSQL("create table "+TABLE_NAME+"(ID INTEGER PRIMARY KEY AUTOINCREMENT,pt_code TEXT, pt_description TEXT,pt_description1 TEXT,pt_type TEXT,pt_um TEXT,pt_tax_code TEXT) ");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
    }

    public boolean  insertData(String pt_code, String pt_description,String pt_description1,String pt_type, String pt_um,String pt_tax_code)
    {
        SQLiteDatabase db= this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_2,pt_code);
        contentValues.put(COL_3,pt_description);
        contentValues.put(COL_4,pt_description1);
        contentValues.put(COL_5,pt_type);
        contentValues.put(COL_6,pt_um);
        contentValues.put(COL_7,pt_tax_code);
        long result= db.insert(TABLE_NAME,null,contentValues);

        if (result ==-1)
        {
            return false;
        }
        else
            return true;
    }

    public Cursor getAllData()
    {
        SQLiteDatabase db= this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from "+TABLE_NAME,null);
        return res;
    }

    public Integer deleteData(String id)
    {
        SQLiteDatabase db =this.getWritableDatabase();
        return   db.delete(TABLE_NAME,"ID = ?", new String[]{String.valueOf(id)});
    }
}
