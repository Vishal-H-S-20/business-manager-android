package com.example.nandivishal;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DatabaseHelperPrice extends SQLiteOpenHelper

{
    public static final String DATABASE_NAME = "Price.db";
    public static final String TABLE_NAME = "Price_table";
    public static final String COL_1 = "ID";
    public static final String COL_2 = "pr_code";
    public static final String COL_3 = "pr_date";
    public static final String COL_4 = "pr_pt_unit";
    public static final String COL_5 = "pr_pr";
    public static final String COL_6 = "pr_mrp";
    public static final String COL_7 = "pr_cm_type";
    public static final String COL_8 = "pr_default";

    public DatabaseHelperPrice(@Nullable Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db)
    {
        db.execSQL("create table "+TABLE_NAME+"(ID INTEGER PRIMARY KEY AUTOINCREMENT,pr_code TEXT, pr_date TEXT,pr_pt_unit TEXT,pr_pr TEXT,pr_mrp TEXT,pr_cm_type TEXT,pr_default TEXT) ");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
    }

    public boolean  insertData(String pr_code , String pr_date,String pr_pt_unit ,String pr_pr ,String pr_mrp ,String pr_cm_type,String pr_default )
    {
        SQLiteDatabase db= this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_2,pr_code);
        contentValues.put(COL_3,pr_date);
        contentValues.put(COL_4,pr_pt_unit);
        contentValues.put(COL_5,pr_pr);
        contentValues.put(COL_6,pr_mrp);
        contentValues.put(COL_7,pr_cm_type);
        contentValues.put(COL_8,pr_default);
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
