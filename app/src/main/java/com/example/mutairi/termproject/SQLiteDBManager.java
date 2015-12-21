package com.example.mutairi.termproject;

import android.content.Context;


public class SQLiteDBManager {
    private static SQLiteDBHelper dbHelper;

    private  SQLiteDBManager(Context context){
        dbHelper = new SQLiteDBHelper(context);
    }
    public static   SQLiteDBHelper   getDBSQLiteDBHelper(Context context){

        if( dbHelper != null){
            return dbHelper;
        }else{
            new SQLiteDBManager(context);
        }
        return  getDBSQLiteDBHelper(context);
    }
}
