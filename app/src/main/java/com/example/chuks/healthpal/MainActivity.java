package com.example.chuks.healthpal;

import android.annotation.TargetApi;
import android.content.Intent;
import android.os.Build;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {

    private TextView mHomeLabel;
    private TextView mDrugsLabel;
    private TextView mDetailsLabel;

    private ViewPager mViewPager;
    private PageViewerAdapter mPageViewerAdapter;

    private FirebaseAuth mAuth;

    @Override
    protected void onStart() {
        super.onStart();

        FirebaseUser currentUser = mAuth.getCurrentUser();

        if (currentUser == null) {
            sendUserToLogin();
        }


    }

    private void sendUserToLogin() {
        Intent sendingUserToLogin = new Intent(MainActivity.this, LogInActivity.class);
        startActivity(sendingUserToLogin);
        finish();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mAuth = FirebaseAuth.getInstance();


        mHomeLabel = (TextView) findViewById(R.id.homeLabel);
        mDrugsLabel = (TextView) findViewById(R.id.drugsLabel);
        mDetailsLabel = (TextView) findViewById(R.id.detailsLabel);

        mViewPager = (ViewPager) findViewById(R.id.mainViewPager);

        mPageViewerAdapter = new PageViewerAdapter(getSupportFragmentManager());
        mViewPager.setAdapter(mPageViewerAdapter);

        mHomeLabel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mViewPager.setCurrentItem(0);
            }
        });

        mDrugsLabel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mViewPager.setCurrentItem(1);
            }
        });

        mDetailsLabel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mViewPager.setCurrentItem(2);
            }
        });

        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                changetabs(position);
            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });



    }

    @TargetApi(Build.VERSION_CODES.M)

    private void changetabs(int position) {
        if (position == 0) {

            mHomeLabel.setTextColor(getColor(R.color.textTabBright));
            mHomeLabel.setTextSize(22);

            mDrugsLabel.setTextColor(getColor(R.color.textTabDull));
            mDrugsLabel.setTextSize(16);

            mDetailsLabel.setTextColor(getColor(R.color.textTabDull));
            mDetailsLabel.setTextSize(16);
        }

        if(position == 1){

            mHomeLabel.setTextColor(getColor(R.color.textTabDull));
            mHomeLabel.setTextSize(16);

            mDrugsLabel.setTextColor(getColor(R.color.textTabBright));
            mDrugsLabel.setTextSize(22);

            mDetailsLabel.setTextColor(getColor(R.color.textTabDull));
            mDetailsLabel.setTextSize(16);
        }

        if(position == 2){

            mHomeLabel.setTextColor(getColor(R.color.textTabDull));
            mHomeLabel.setTextSize(16);

            mDrugsLabel.setTextColor(getColor(R.color.textTabDull));
            mDrugsLabel.setTextSize(16);

            mDetailsLabel.setTextColor(getColor(R.color.textTabBright));
            mDetailsLabel.setTextSize(22);
        }
    }
}
