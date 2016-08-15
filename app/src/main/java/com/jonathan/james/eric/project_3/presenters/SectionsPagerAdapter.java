package com.jonathan.james.eric.project_3.presenters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;


import com.jonathan.james.eric.project_3.SectionFragment;

import java.util.ArrayList;

/**
 * Created by Jonathan Taylor on 8/15/16.
 */
public class SectionsPagerAdapter extends FragmentPagerAdapter {

    private ArrayList<String> mSectionNames;


    public SectionsPagerAdapter(FragmentManager fm, ArrayList<String> sectionNames) {
        super(fm);
        mSectionNames = sectionNames;
    }

    @Override
    public Fragment getItem(int position) {
        return SectionFragment.getInstance(mSectionNames.get(position));
    }

    @Override
    public int getCount() {
        return mSectionNames.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mSectionNames.get(position);
    }
}