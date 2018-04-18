package com.example.chuks.healthpal;


import android.app.AlarmManager;
import android.app.DatePickerDialog;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.EditText;

import com.example.chuks.healthpal.data.ReminderContract;
import com.example.chuks.healthpal.data.ReminderDBHelper;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;


/**
 * A simple {@link Fragment} subclass.
 */
public class DrugsFragment extends android.support.v4.app.Fragment {

    private EditText drugName;
    private  EditText drugDescription;
    private EditText startDate;
    private EditText endDate;
    private EditText frequency;
    private FloatingActionButton floatingActionButton;
    private SQLiteDatabase mSQLiteDatabase;

    private static String PERIOD = "CHECK";


    public DrugsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_drugs, container, false);

        drugName = (EditText) v.findViewById(R.id.drugName);
        drugDescription = (EditText) v.findViewById(R.id.drugDescription);
        startDate = (EditText) v.findViewById(R.id.datePickerStart);
        endDate = (EditText) v.findViewById(R.id.datePickerEnd);
        frequency = (EditText) v.findViewById(R.id.drugFrequency);
        floatingActionButton = (FloatingActionButton) v.findViewById(R.id.floatingActionButton);

//        ReminderDBHelper dbHelper = new ReminderDBHelper(this);
//        mSQLiteDatabase = dbHelper.getWritableDatabase();
        startDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {

                final Calendar calendar = Calendar.getInstance();
                int yy = calendar.get(Calendar.YEAR);
                int mm = calendar.get(Calendar.MONTH);
                int dd = calendar.get(Calendar.DAY_OF_MONTH);
                DatePickerDialog datePicker = new DatePickerDialog(getActivity(), new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        String date = String.valueOf(dayOfMonth) + "/" + String.valueOf(monthOfYear+1)
                                + "/" + String.valueOf(year);
                        startDate.setText(date);
                    }
                }, yy, mm, dd);
                datePicker.show();
            }
        });

        endDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {

                final Calendar calendar = Calendar.getInstance();
                int yy = calendar.get(Calendar.YEAR);
                int mm = calendar.get(Calendar.MONTH);
                int dd = calendar.get(Calendar.DAY_OF_MONTH);
                DatePickerDialog datePicker = new DatePickerDialog(getActivity(), new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        String date = String.valueOf(dayOfMonth) + "/" + String.valueOf(monthOfYear+1)
                                + "/" + String.valueOf(year);
                        endDate.setText(date);
                    }
                }, yy, mm, dd);
                datePicker.show();
            }
        });

        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                drugName.getEditableText().toString();
                drugName.setText("");

                drugDescription.getEditableText().toString();
                drugDescription.setText("");

                String startDateString = startDate.getEditableText().toString();
                startDate.setText("");

                String endDateString = endDate.getEditableText().toString();
                endDate.setText("");

                int frequencyRate = Integer.valueOf(frequency.getEditableText().toString());
                frequency.setText("");

                //TODO: Set the xml input type to text number



                try {

                    SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");


                    Date startDate = dateFormat.parse(startDateString);
                    Date endDate = dateFormat.parse(endDateString);

                    AlarmManager mgr=(AlarmManager)getActivity().getSystemService(Context.ALARM_SERVICE);
                    Intent i=new Intent(getActivity(), MainActivity.class);
                    PendingIntent pi=PendingIntent.getBroadcast(getContext(), 0, i, 0);

                    mgr.setRepeating(AlarmManager.ELAPSED_REALTIME_WAKEUP, SystemClock.elapsedRealtime(), startDate.getTime(), pi);





                    Cursor cursor = getAllDrugs();


                }
                catch (ParseException e){
                    e.printStackTrace();

                }

                finally {

                }


            }
        });




        return v;
    }

    private Cursor getAllDrugs(){

        return mSQLiteDatabase.query(ReminderContract.ReminderEntry.TABLE_NAME,
                null,
                null,
                null,
                null,
                null,
               ReminderContract.ReminderEntry.COLUMN_NAME_CURRENT_TIME );

    }

   }
