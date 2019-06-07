package com.example.datatbase;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class database extends SQLiteOpenHelper {

    public database( Context context) {
        super(context, "student", null, 1);
        SQLiteDatabase db = this.getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
    db.execSQL("create table student(ID INT(10) PRIMARY KEY AUTOINCREMENT , NAME VARCHAR(30), SNAME VARCHAR(30), MARKS INT(10))");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    db.execSQL("DROP TABLE IF EXISTS student");
    }

    public boolean insert(String name, String sname, String marks){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues= new ContentValues();
        contentValues.put("NAME", name);
        contentValues.put("SNAME", sname);
        contentValues.put("MARKS", marks);
        long re = db.insert("student", null, contentValues);
        if(re == -1){
            return false;
        }
        else{
            return true;}
    }

    public Cursor get(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res =db.rawQuery("select * from student", null);
        return res;
    }

    public boolean update(String id,String name, String sname, String marks ){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues= new ContentValues();
        contentValues.put("ID", id);
        contentValues.put("NAME", name);
        contentValues.put("SNAME", sname);
        contentValues.put("MARKS", marks);
        db.update("student",contentValues, "ID = ?",new String[]{ id });
        return true;
    }

    public  Integer delete(String id){
        SQLiteDatabase db =this.getWritableDatabase();
        return db.delete("student","ID = ?",new String[]{ id });

    }

}
