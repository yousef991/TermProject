package com.example.mutairi.termproject;


import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.database.Cursor;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.widget.CursorAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.List;


public class MainActivity extends FragmentActivity {
    public static Context mContext;
    public static MyAdapter myAdapter;
    public static Cursor dbCursor;

    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mContext = this;
        SQLiteDBManager.getDBSQLiteDBHelper(mContext);


        listView = (ListView)findViewById(R.id.listView);

        dbCursor = SQLiteDAO.getTraceInfoCursor();
        myAdapter = new MyAdapter(mContext, dbCursor, false);

        listView.setAdapter(myAdapter);

    }


    public void buttonClicked(View v)
    {
        Intent intent = new Intent(mContext, RegistActivity.class);
        startActivity(intent);
    }

    public void statisticButtonClicked(View v)
    {
        Intent intent = new Intent(mContext, StatisticActivity.class);
        startActivity(intent);
    }

    public Drawable getTitleImage(String title)
    {
        Resources res = getResources();
        if(title.equals(DefineConstant.MEAL))
        {
            return res.getDrawable(R.drawable.meal);
        }
        else if(title.equals(DefineConstant.STUDY)) {
            return res.getDrawable(R.drawable.study);
        }
        else if(title.equals(DefineConstant.LECTURE)) {
            return res.getDrawable(R.drawable.lecture);
        }
        else if(title.equals(DefineConstant.REST)) {
            return res.getDrawable(R.drawable.rest);
        }
        else if(title.equals(DefineConstant.DRINK)) {
            return res.getDrawable(R.drawable.drink);
        }
        else if(title.equals(DefineConstant.SLEEP))
        {
            return res.getDrawable(R.drawable.sleep);
        }
        else if(title.equals(DefineConstant.HOBBY)) {
            return res.getDrawable(R.drawable.hobby);
        }
        else{
            return res.getDrawable(R.drawable.others);
        }
    }


    class MyAdapter extends CursorAdapter{
        public MyAdapter(Context context, Cursor c) {
            super(context, c);

        }

        public MyAdapter(Context context, Cursor c, boolean autoRequery) {
            super(context, c, autoRequery);
        }

        @Override
        public View newView(Context context, Cursor cursor, ViewGroup parent) {
            ListItemLayout listItemLayout = new ListItemLayout(mContext);
            return listItemLayout;
        }

        @Override
        public void bindView(View view, Context context, Cursor cursor) {
            ListItemLayout listItemLayout = (ListItemLayout)view;
            final int logId = cursor.getInt(cursor.getColumnIndex(SQLiteDB.TraceInfo._ID));
            final String title = cursor.getString(cursor.getColumnIndex(SQLiteDB.TraceInfo.TITLE));
            final String address = cursor.getString(cursor.getColumnIndex(SQLiteDB.TraceInfo.ADDRESS));
            final int year = cursor.getInt(cursor.getColumnIndex(SQLiteDB.TraceInfo.YEAR));
            final int month = cursor.getInt(cursor.getColumnIndex(SQLiteDB.TraceInfo.MONTH));
            final int day = cursor.getInt(cursor.getColumnIndex(SQLiteDB.TraceInfo.DAY));
            final int hour = cursor.getInt(cursor.getColumnIndex(SQLiteDB.TraceInfo.HOUR));
            final int minute = cursor.getInt(cursor.getColumnIndex(SQLiteDB.TraceInfo.MINUTE));

            final double latitude =Double.valueOf(cursor.getString(cursor.getColumnIndex(SQLiteDB.TraceInfo.LATITUDE)));
            final double longitude =Double.valueOf(cursor.getString(cursor.getColumnIndex(SQLiteDB.TraceInfo.LONGITUDE)));


            listItemLayout.titleText.setText(title);
            listItemLayout.addressText.setText(address);
            listItemLayout.dateText.setText(year+"."+month+"."+day+" "+hour+":"+minute);

            listItemLayout.imageView.setImageDrawable(getTitleImage(title));

            listItemLayout.itemLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(mContext, DoingInfoActivity.class);
                    intent.putExtra("logId", logId);
                    intent.putExtra("title", title);
                    intent.putExtra("address", address);
                    intent.putExtra("year", year);
                    intent.putExtra("month",month);
                    intent.putExtra("day", day);
                    intent.putExtra("hour", hour);
                    intent.putExtra("minute", minute);
                    intent.putExtra("latitude", latitude);
                    intent.putExtra("longitude", longitude);

                    startActivity(intent);
                }
            });

        }
    }

 }
