package com.example.chuks.healthpal.data;

import android.database.Cursor;
import android.net.Uri;
import android.provider.BaseColumns;

/**
 * Created by chuks on 4/16/2018.
 */

public class ReminderContract {

    // To prevent someone from accidentally instantiating the contract class,
    // make the constructor private.
    private ReminderContract() {
    }

    public static final String CONTENT_AUTHORITY = "com.example.chuks.healthpal";

    public static final Uri BASE_CONTENT_URI = Uri.parse("content://" + CONTENT_AUTHORITY);

    /* Inner class that defines the table contents */
    public static class ReminderEntry implements BaseColumns {
        public static final String TABLE_NAME = "reminderDB";
        public static final String COLUMN_NAME_DRUG_NAME = "startDate";
        public static final String COLUMN_NAME_START_DATE = "startDate";
        public static final String COLUMN_NAME_END_DATE = "endDate";
        public static final String COLUMN_NAME_CURRENT_TIME = "currentTime";
        public static final String COLUMN_NAME_FREQUENCY = "frequency";
        public static final String COLUMN_NAME_DESCRIPTION = "description";
        public static final String COLUMN_NAME_TIME_DIFF = "timeDiff";



    }

}
