package com.example.nandivishal;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DatabaseHelperCustomer extends SQLiteOpenHelper
{
    public static final String DATABASE_NAME = "Customer.db";
    public static final String TABLE_NAME = "Customer_table";
    public static final String COL_1 = "ID";
    public static final String COL_2 = "cm_code";
    public static final String COL_3 = "cm_name";
    public static final String COL_4 = "cm_region";
    public static final String COL_5 = "cm_sls_psn";
    public static final String COL_6 = "cm_type";
    public static final String COL_7 = "cm_lst_no";

    public DatabaseHelperCustomer(@Nullable Context context)
    {

        super(context,DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table "+TABLE_NAME+"(ID INTEGER PRIMARY KEY AUTOINCREMENT,cm_code TEXT, cm_name TEXT,cm_region TEXT,cm_sls_psn TEXT,cm_type TEXT,cm_lst_no TEXT) ");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);

    }

    public boolean  insertData(String cm_code, String cm_name,String cm_region,String cm_sls_psn, String cm_type,String cm_lst_no)
    {
        SQLiteDatabase db= this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_2,cm_code);
        contentValues.put(COL_3,cm_name);
        contentValues.put(COL_4,cm_region);
        contentValues.put(COL_5,cm_sls_psn);
        contentValues.put(COL_6,cm_type);
        contentValues.put(COL_7,cm_lst_no);
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
