package com.example.chuks.healthpal.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by chuks on 4/16/2018.
 */

public class ReminderDBHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "reminderDB.db";

    private static final int DATABASE_VERSION = 1;

    ReminderDBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);


    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase){
        final String SQL_CREATE_REMINDER_TABLE = "CREATE TABLE " +
                ReminderContract.ReminderEntry.TABLE_NAME + " (" +
                ReminderContract.ReminderEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                ReminderContract.ReminderEntry.COLUMN_NAME_DRUG_NAME + " TEXT NOT NULL, " +
                ReminderContract.ReminderEntry.COLUMN_NAME_DESCRIPTION + " TEXT NOT NULL, " +
                ReminderContract.ReminderEntry.COLUMN_NAME_START_DATE + " DATE NOT NULL, " +
                ReminderContract.ReminderEntry.COLUMN_NAME_END_DATE + " DATE NOT NULL, " +
                ReminderContract.ReminderEntry.COLUMN_NAME_DRUG_NAME + " TEXT NOT NULL, " +
                ReminderContract.ReminderEntry.COLUMN_NAME_FREQUENCY + " INTEGER NOT NULL, " +
                ReminderContract.ReminderEntry.COLUMN_NAME_TIME_DIFF + " INTEGER NOT NULL, " +
                ReminderContract.ReminderEntry.COLUMN_NAME_CURRENT_TIME + " DEFAULT CURRENT_TIMESTAMP" + ");";

                sqLiteDatabase.execSQL(SQL_CREATE_REMINDER_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + ReminderContract.ReminderEntry.TABLE_NAME);
        onCreate(sqLiteDatabase);

    }

}
