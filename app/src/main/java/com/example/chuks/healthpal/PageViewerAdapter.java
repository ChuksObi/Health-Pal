package com.example.chuks.healthpal;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

/**
 * Created by chuks on 4/11/2018.
 */

class PageViewerAdapter extends FragmentPagerAdapter {
    public PageViewerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                HomeFragment homeFragment = new HomeFragment();
                return homeFragment;
            case 1:
                DrugsFragment drugsFragment = new DrugsFragment();
                return drugsFragment;
            case 2:
                DetailsFragment detailsFragment = new DetailsFragment();
                return detailsFragment;
            default:
                return null;
        }
//        return null;
    }

    @Override
    public int getCount() {
        return 3;
    }
}
