package com.example.vs00481543.phonecallrecorder;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by VS00481543 on 01-11-2017.
 */

public class DatabaseHandler extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 2;
    public static final String DATABASE_NAME = "callRecords";
    public static final String TABLE_RECORD = "callRecord";
    public static final String SERIAL_NUMBER= "serialNumber";
    public static final String PHONE_NUMBER ="phoneNumber";
   // public static final String CONTACT_NAME="contactName";
    public static final String TIME = "time";
    public static final String DATE = "date";


    public DatabaseHandler(Context context)
    {
        super(context,DATABASE_NAME,null,DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String CREATE_LOG_TABLE="CREATE TABLE " + TABLE_RECORD + "("
                + SERIAL_NUMBER + " INTEGER PRIMARY KEY,"+ PHONE_NUMBER + " TEXT," + TIME + " TEXT,"
                + DATE + " TEXT" + ")";

        db.execSQL(CREATE_LOG_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS " + TABLE_RECORD);
        onCreate(db);
    }
}
