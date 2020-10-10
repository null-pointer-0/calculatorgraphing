package com.example.calculator;
import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class myHelper extends SQLiteOpenHelper {
    private Context con;
    public myHelper(@Nullable Context context) {
        super(context, "collectedpoints",null, 1);
        con = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
         String createtable = "create table Mytable(xvalues Double,yvalues Double);";
         db.execSQL(createtable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
    public void insert(double x,double y){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("xvalues",x);
        cv.put("yvalues",y);
        db.insert("Mytable",null,cv);
        Toast.makeText(con,"DATA Inserted",Toast.LENGTH_LONG).show();
    }
}
