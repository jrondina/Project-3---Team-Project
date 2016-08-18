package com.jonathan.james.eric.project_3.presenters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;


import com.jonathan.james.eric.project_3.Article;
import com.jonathan.james.eric.project_3.SectionFragment;
import com.jonathan.james.eric.project_3.interfaces.APIFetcher;
import com.jonathan.james.eric.project_3.interfaces.ArticleListener;
import com.jonathan.james.eric.project_3.interfaces.SectionCardListener;

import java.util.ArrayList;
import java.util.Arrays;

import io.realm.RealmResults;

/**
 * Created by Jonathan Taylor on 8/15/16.
 */
public class SectionsPagerAdapter extends FragmentPagerAdapter /*implements RealmChangeListener<UserPreferences>,
                                    RealmChangeListener<RealmResults>*/{

    //ToDo change to UserPreferences Object and use the Section object list
    private ArrayList<String> mSectionNames;

    private SectionCardListener mSectionCardListener;
    private ArticleListener mArticleListener;
    private APIFetcher mFetcher;


    public SectionsPagerAdapter(FragmentManager fm, ArrayList<String> sectionNames, APIFetcher fetcher,
                                SectionCardListener cardListener, ArticleListener articleListener) {
        super(fm);
        mSectionNames = sectionNames;
        mFetcher = fetcher;
        mSectionCardListener = cardListener;
        mArticleListener = articleListener;

    }

    @Override
    public Fragment getItem(int position) {
        //ToDo change to use Section object + pull article list from the API

        ArrayList<Article> articles;
        if(mSectionNames.get(position).equals("bookmarks")){
            RealmResults<Article> realmArticles = RealmUtility.getArticles();
            realmArticles.addChangeListener(this);
            //messy method to convert the RealmResults to ArrayList... Probably needs to be refactored
            articles = new ArrayList<>(Arrays.asList((Article[])realmArticles.toArray()));
        } else {
            //get an instance of the section singleton to pull the article list from the API
            articles = mFetcher.getArticles(mSectionNames.get(position));
        }

        return SectionFragment.getInstance(mSectionNames.get(position), articles,
                mSectionCardListener, mArticleListener);
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