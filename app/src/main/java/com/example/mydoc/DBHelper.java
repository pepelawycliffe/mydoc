package com.example.mydoc;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.icu.text.DateFormat;

import androidx.annotation.Nullable;

public class DBHelper extends SQLiteOpenHelper {
    public DBHelper(Context context) {
        super(context, "userdata",null,1);
//        Patient_Id,Registration_date,FirstName,LastName,Dob
    }

    @Override
    public void onCreate(SQLiteDatabase DB) {
        DB.execSQL("create table Userdetails(" +
                "Patient_Id TEXT primary key," +
                "Registration_date DATE," +
                "FirstName TEXT," +
                "LastName TEXT," +
                "Dob DATE)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase DB, int oldVersion, int newVersion) {
        DB.execSQL("drop table if exists Userdetails");

    }

    public Boolean saveuserdata(String Patient_Id,
                                DateFormat Registration_date,
                                String FirstName,
                                String LastName,
                                DateFormat Dob){
        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues contentValues= new ContentValues();
        contentValues.put("Patient_Id",Patient_Id);
        contentValues.put("Registration_date", String.valueOf(Registration_date));
        contentValues.put("FirstName", FirstName);
        contentValues.put("LastName", LastName);
        contentValues.put("Dob", String.valueOf(Dob));
        long result=DB.insert("Userdetails",null,contentValues);
        if (result==-1){
            return false;
        }else
        {
            return true;
        }

    }
    public Cursor gettext(){
        SQLiteDatabase DB = this.getWritableDatabase();
        Cursor cursor =DB.rawQuery("select * from userdetails",null);
        return cursor;
    }


}
