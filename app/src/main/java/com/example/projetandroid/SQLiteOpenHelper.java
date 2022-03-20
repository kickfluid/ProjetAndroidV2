package com.example.projetandroid;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
class DatabaseHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "Student_db";
    public static final String TABLE_NAME = "student_table";
    public static final String COL_1 = "ID";
    public static final String COL_2 = "Nom";
    public static final String COL_3 = "Description";
    public static final String COL_4 = "Favoris";
    public static final String COL_5 = "latittude";
    public static final String COL_6 = "longitude";
    public DatabaseHelper(Context context){
        super(context, DATABASE_NAME, null, 1);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + TABLE_NAME +" (ID INTEGER PRIMARY KEY AUTOINCREMENT,Nom TEXT,Description TEXT,Favoris INTEGER,latittude FLOAT, longitude FLOAT)");
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int
            newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
        onCreate(db);
    }
    public boolean insertData(String Nom, String Description, String Favoris){
        SQLiteDatabase db= this.getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put(COL_2, Nom);
        values.put(COL_3, Description);
        values.put(COL_4, Favoris);
        long result=db.insert(TABLE_NAME, null, values);
        if (result == -1)
            return false;
        else
            return true;
    }
    //read all data
    public Cursor getAllData(){
        SQLiteDatabase db= this.getWritableDatabase();
        Cursor res=db.rawQuery("select * from "+ TABLE_NAME, null);
        return res;
    }
    public Cursor affichageAP(String id) {
        SQLiteDatabase db = this.getWritableDatabase();
        String selectquery=String.format("select %s from %s  where %s = ?",COL_3,TABLE_NAME,COL_2);
        Cursor res= db.rawQuery(selectquery,new String[] {id});
        return res;
    }

    public Cursor affichageFav() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res= (db.rawQuery("select  " + COL_2 + " from " +TABLE_NAME +" where  "+ COL_4 + " = 1",null));
        return res;
    }
    //updating data
    public boolean updateData(String id, int fav){
        SQLiteDatabase db= this.getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put(COL_4, fav);
        db.update(TABLE_NAME, values,"Nom= ?", new String[] {id});
        return true;
    }
    //deleting data
    public Integer deleteData(String id){
        SQLiteDatabase db=this.getWritableDatabase();
        return db.delete(TABLE_NAME, "ID=?", new String[] {id});
    }
 /* public boolean deleteData(int id){
 //SQLiteDatabase db =this.getWritableDatabase();
 //return db.delete(TABLE_NAME, "ID = ?", new String[] {id});
 boolean result = false;
 String query = "Select*FROM" + TABLE_NAME + "WHERE" + COL_1 + "= '"
+ String.valueOf(id) + "'";
 SQLiteDatabase db = this.getWritableDatabase();
 Cursor cursor = db.rawQuery(query, null);
 Student student = new Student();
 if (cursor.moveToFirst()) {
 student.setID(Integer.parseInt(cursor.getString(0)));
 db.delete(TABLE_NAME, COL_1 + "=?",
 new String[] {
 String.valueOf(student.getID())
 });
 cursor.close();
 result = true;
 }
 db.close();
 return result;
 }*/
}
