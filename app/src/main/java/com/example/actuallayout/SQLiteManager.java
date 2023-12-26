package com.example.actuallayout;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import android.database.sqlite.SQLiteOpenHelper;


public class SQLiteManager extends SQLiteOpenHelper {
    private static SQLiteManager sqLiteManager;
    private static final String DATABASE_NAME = "StepCounterDB";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_NAME = "StepCounter";
    private static final String COUNTER = "Counter";
    private static final String ID_FIELD = "id";
    private static final String STEPS_FIELD = "steps";
    public static final String DATE_FIELD = "date";
    public static final String HOUR_FIELD = "hour";


    private static final String DATABASE_CREATE = "create table " + TABLE_NAME
            + "(" + ID_FIELD + " integer primary key autoincrement, "
            + STEPS_FIELD + " integer not null, "
            // + HOUR_FIELD + " text not null, "
            + DATE_FIELD + " text not null);";
    public SQLiteManager(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(DATABASE_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(sqLiteDatabase);
    }

    public long insertSteps(int stepCount, String date) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(STEPS_FIELD, stepCount);
        values.put(DATE_FIELD, date);
        //values.put(HOUR_FIELD, hour);

        return sqLiteDatabase.insert(TABLE_NAME, null, values);
    }

    public String getAllSteps() {
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        // String[] allColumns = {ID_FIELD, STEPS_FIELD, DATE_FIELD};
        //return (sqLiteDatabase.rawQuery("SELECT steps, date FROM " + TABLE_NAME + "GROUP BY date", null));
        return (TABLE_NAME + ID_FIELD + STEPS_FIELD + DATE_FIELD);
    }

}