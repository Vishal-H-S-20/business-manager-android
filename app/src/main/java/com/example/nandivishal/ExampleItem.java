package com.example.nandivishal;

public class ExampleItem
{
    private int mImageResource;
    private String mLine1;
    private String mLine2;

    ExampleItem(){

    }

    public ExampleItem (int imageresouce,String line1,String line2)
    {
mImageResource=imageresouce;
mLine1=line1;
mLine2=line2;
    }

    public int getmImageResource()
    {
        return mImageResource;
    }
    public String getmLine1()
    {
        return mLine1;
    }
    public String getmLine2()
    {
        return mLine2;
    }
}
