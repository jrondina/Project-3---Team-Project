package com.jonathan.james.eric.project_3.presenters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;


import com.jonathan.james.eric.project_3.SectionFragment;
import com.jonathan.james.eric.project_3.interfaces.ArticleListener;
import com.jonathan.james.eric.project_3.interfaces.SectionCardListener;

import java.util.ArrayList;

import io.realm.RealmChangeListener;

/**
 * Created by Jonathan Taylor on 8/15/16.
 */
public class SectionsPagerAdapter extends FragmentPagerAdapter /*implements RealmChangeListener<UserPreferences>*/{

    //ToDo change to UserPreferences Object and use the Section object list
    private ArrayList<String> mSectionNames;

    private SectionCardListener mSectionCardListener;
    private ArticleListener mArticleListener;


    public SectionsPagerAdapter(FragmentManager fm, ArrayList<String> sectionNames,
                                SectionCardListener cardListener, ArticleListener articleListener) {
        super(fm);
        mSectionNames = sectionNames;

        mSectionCardListener = cardListener;
        mArticleListener = articleListener;

    }

    @Override
    public Fragment getItem(int position) {
        //ToDo change to use Section object
        return SectionFragment.getInstance(mSectionNames.get(position), mSectionCardListener, mArticleListener);
    }

    @Override
    public int getCount() {
        return mSectionNames.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mSectionNames.get(position);
    }

    //Listener to see if the User changed their UserPreferences for either Sections or Sources
//    @Override
//    public void onChange(UserPreferences element) {
//        notifyDataSetChanged();
//    }
}