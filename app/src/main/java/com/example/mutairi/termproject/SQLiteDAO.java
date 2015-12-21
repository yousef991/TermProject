package com.example.mutairi.termproject;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteQueryBuilder;
import android.util.Log;

/**
 * Created by june on 15. 12. 19.
 */
public class SQLiteDAO {

    public static void insertNewTraceInfo(String title,
                                             String address,
                                             int year,
                                             int month,
                                             int day,
                                             int hour,
                                             int minute,
                                             Double latitude,
                                             Double longitude
                                             )
    {
        SQLiteDatabase dbHandler = SQLiteDBManager.getDBSQLiteDBHelper(MainActivity.mContext).getWritableDatabase();
        ContentValues rowValues = new ContentValues();
        dbHandler.beginTransaction();
        try
        {

            rowValues.put(SQLiteDB.TraceInfo.TITLE, title);
            rowValues.put(SQLiteDB.TraceInfo.ADDRESS, address);
            rowValues.put(SQLiteDB.TraceInfo.YEAR, year);
            rowValues.put(SQLiteDB.TraceInfo.MONTH, month);
            rowValues.put(SQLiteDB.TraceInfo.DAY, day);
            rowValues.put(SQLiteDB.TraceInfo.HOUR, hour);
            rowValues.put(SQLiteDB.TraceInfo.MINUTE, minute);
            rowValues.put(SQLiteDB.TraceInfo.LATITUDE, String.valueOf(latitude));
            rowValues.put(SQLiteDB.TraceInfo.LONGITUDE, String.valueOf(longitude));
            dbHandler.insert(SQLiteDB.TraceInfo.TABLE_NAME, "NODATA",
                    rowValues);

            dbHandler.setTransactionSuccessful();
        }catch(SQLiteException sqle)
        {
            Log.e("ListenerERROR", sqle.toString());
        }
        finally{
            dbHandler.endTransaction();
        }

    }

    public static Cursor getTraceInfoCursor()
    {
        SQLiteDatabase  dbHandler = SQLiteDBManager.getDBSQLiteDBHelper(MainActivity.mContext).getWritableDatabase();
        SQLiteQueryBuilder queryBuilder = new SQLiteQueryBuilder();
        queryBuilder.setTables(SQLiteDB.TraceInfo.TABLE_NAME);

        try{
            String columnsToReturn [] = {
                    SQLiteDB.TraceInfo.TABLE_NAME + "." + SQLiteDB.TraceInfo._ID,
                    SQLiteDB.TraceInfo.TABLE_NAME + "." + SQLiteDB.TraceInfo.TITLE,
                    SQLiteDB.TraceInfo.TABLE_NAME + "." + SQLiteDB.TraceInfo.ADDRESS,
                    SQLiteDB.TraceInfo.TABLE_NAME + "." + SQLiteDB.TraceInfo.YEAR,
                    SQLiteDB.TraceInfo.TABLE_NAME + "." + SQLiteDB.TraceInfo.MONTH,
                    SQLiteDB.TraceInfo.TABLE_NAME + "." + SQLiteDB.TraceInfo.DAY,
                    SQLiteDB.TraceInfo.TABLE_NAME + "." + SQLiteDB.TraceInfo.HOUR,
                    SQLiteDB.TraceInfo.TABLE_NAME + "." + SQLiteDB.TraceInfo.MINUTE,
                    SQLiteDB.TraceInfo.TABLE_NAME + "." + SQLiteDB.TraceInfo.LATITUDE,
                    SQLiteDB.TraceInfo.TABLE_NAME + "." + SQLiteDB.TraceInfo.LONGITUDE
            };

            Cursor resultSet = queryBuilder.query(dbHandler,
                    columnsToReturn,null,null,null,null,null);

            return resultSet;
        }catch(SQLiteException e)
        {
            e.printStackTrace();
        }

        return null;

    }

    public static boolean deleteTrace(int logId)
    {	SQLiteDatabase  dbHandler = SQLiteDBManager.getDBSQLiteDBHelper(MainActivity.mContext).getWritableDatabase();
        boolean flag = false;
        try{

            dbHandler.beginTransaction();
            dbHandler.delete(SQLiteDB.TraceInfo.TABLE_NAME, SQLiteDB.TraceInfo._ID + "=?",
                    new String[]{String.valueOf(logId)});
            dbHandler.setTransactionSuccessful();
            flag = true;
        }catch(SQLiteException sql){
            Log.e("deleteAlarm_ERROR", sql.toString());
        }finally{
            dbHandler.endTransaction();
        }
        return flag;
    }


}
