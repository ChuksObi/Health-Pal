package com.example.chuks.healthpal.Adapter;

import android.content.Context;
import android.database.Cursor;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.chuks.healthpal.Model.Card;
import com.example.chuks.healthpal.data.ReminderContract;

import java.util.List;

/**
 * Created by chuks on 4/18/2018.
 */

public class RecyclerAdapter extends RecyclerView.Adapter <RecyclerAdapter.CardListViewHolder> {


    private Context mContext;
    private Cursor mCursor;

    public RecyclerAdapter(Context mContext, Cursor Cursor) {

        this.mContext = mContext;
        this.mCursor = Cursor;
    }

    @Override
    public CardListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(mContext);
        View view = inflater.inflate(R.Layout.activity_drugs, parent, false);
        return new CardListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(CardListViewHolder holder, int position) {

       if(mCursor.moveToPosition(position))
           return;
       String drugName = mCursor.getString(mCursor.getColumnIndex(ReminderContract.ReminderEntry.COLUMN_NAME_DRUG_NAME));
        String drugDescription = mCursor.getString(mCursor.getColumnIndex(ReminderContract.ReminderEntry.COLUMN_NAME_DESCRIPTION));
        String drugStartDate = mCursor.getString(mCursor.getColumnIndex(ReminderContract.ReminderEntry.COLUMN_NAME_START_DATE));
        String drugDate = mCursor.getString(mCursor.getColumnIndex(ReminderContract.ReminderEntry.COLUMN_NAME_END_DATE));
        String drugTimeDiff = mCursor.getString(mCursor.getColumnIndex(ReminderContract.ReminderEntry.COLUMN_NAME_TIME_DIFF));
        String drugFrequency = mCursor.getString(mCursor.getColumnIndex(ReminderContract.ReminderEntry.COLUMN_NAME_FREQUENCY));

    }

    @Override
    public int getItemCount() {
        return mCursor.getCount();
    }

    public class CardListViewHolder extends RecyclerView.ViewHolder {


        public CardListViewHolder(View itemView) {
            super(itemView);

            
        }
    }

}