package com.example.mutairi.termproject;


import android.provider.BaseColumns;

public class SQLiteDB{
    public static final class TraceInfo implements BaseColumns {
        private TraceInfo(){}

        public static final String TABLE_NAME = "trace_tbl";
        public static final String TITLE = "title";
        public static final String ADDRESS = "address";
        public static final String YEAR = "year";
        public static final String MONTH = "month";
        public static final String DAY = "day";
        public static final String HOUR = "hour";
        public static final String MINUTE = "minute";
        public static final String LATITUDE = "latitude";
        public static final String LONGITUDE = "longitude";
    }

}
