package com.example.mutairi.termproject;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class SQLiteDBHelper extends SQLiteOpenHelper{
    private static final String DB_NAME = "SQLiteDB.db";
    private static final  int  DB_VERSION = 1;

    public SQLiteDBHelper(Context context)
    {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        StringBuilder TraceListTBLSQL = new StringBuilder();

        TraceListTBLSQL
                .append("CREATE TABLE ")
                .append(SQLiteDB.TraceInfo.TABLE_NAME)
                .append(" ( ")
                .append(SQLiteDB.TraceInfo._ID)
                .append(" INTEGER PRIMARY KEY AUTOINCREMENT ,")
                .append(SQLiteDB.TraceInfo.TITLE)
                .append(" TEXT , ")
                .append(SQLiteDB.TraceInfo.ADDRESS)
                .append(" TEXT , ")
                .append(SQLiteDB.TraceInfo.YEAR)
                .append(" INTEGER , ")
                .append(SQLiteDB.TraceInfo.MONTH)
                .append(" INTEGER , ")
                .append(SQLiteDB.TraceInfo.DAY)
                .append(" INTEGER , ")
                .append(SQLiteDB.TraceInfo.HOUR)
                .append(" INTEGER , ")
                .append(SQLiteDB.TraceInfo.MINUTE)
                .append(" INTEGER , ")
                .append(SQLiteDB.TraceInfo.LATITUDE)
                .append(" TEXT , ")
                .append(SQLiteDB.TraceInfo.LONGITUDE)
                .append(" TEXT ")
                .append(" ); ");

        db.execSQL(TraceListTBLSQL.toString());
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    @Override
    public void onOpen(SQLiteDatabase db) {
        super.onOpen(db);
    }
}
