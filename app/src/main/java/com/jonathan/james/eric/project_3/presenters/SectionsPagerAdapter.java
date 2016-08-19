package com.jonathan.james.eric.project_3.presenters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;


import com.jonathan.james.eric.project_3.Article;
import com.jonathan.james.eric.project_3.RealmUtility;
import com.jonathan.james.eric.project_3.Section;
import com.jonathan.james.eric.project_3.SectionFragment;
import com.jonathan.james.eric.project_3.UserPreferences;
import com.jonathan.james.eric.project_3.interfaces.APIFetcher;
import com.jonathan.james.eric.project_3.interfaces.ArticleListener;
import com.jonathan.james.eric.project_3.interfaces.SectionCardListener;

import java.util.ArrayList;
import java.util.Arrays;

import io.realm.RealmChangeListener;
import io.realm.RealmList;
import io.realm.RealmResults;

/**
 * Created by Jonathan Taylor on 8/15/16.
 */
public class SectionsPagerAdapter extends FragmentPagerAdapter implements RealmChangeListener {

    //ToDo change to UserPreferences Object and use the Section object list
    private RealmList<Section> mSectionNames;

    private SectionCardListener mSectionCardListener;
    private ArticleListener mArticleListener;
    private APIFetcher mFetcher;

    private UserPreferences mUserPreferences;


    public SectionsPagerAdapter(FragmentManager fm, APIFetcher fetcher,
                                SectionCardListener cardListener, ArticleListener articleListener) {
        super(fm);
        mUserPreferences = new RealmUtility().getUserPreferences();
        mSectionNames = mUserPreferences.getSectionList();
        mFetcher = fetcher;
        mSectionCardListener = cardListener;
        mArticleListener = articleListener;

    }

    @Override
    public Fragment getItem(int position) {
        //ToDo change to use Section object + pull article list from the API

        ArrayList<Article> articles;
        if(mSectionNames.get(position).equals("bookmarks")){
            RealmUtility realmUtility = new RealmUtility();
            RealmResults<Article> realmArticles = realmUtility.getBookmarkedArticles();
            realmArticles.addChangeListener(this);
            //messy method to convert the RealmResults to ArrayList... Probably needs to be refactored
            articles = new ArrayList<>(Arrays.asList((Article[])realmArticles.toArray()));

            return SectionFragment.getInstance(articles,
                    mSectionCardListener, mArticleListener);
        } else {
            //get an instance of the section singleton to pull the article list from the API
            SectionFragment sectionFragment = SectionFragment.getInstance(
                    new ArrayList<Article>(), mSectionCardListener, mArticleListener);
            mFetcher.getArticles(mSectionNames.get(position).getKey(), sectionFragment);

            return sectionFragment;
        }


    }

    @Override
    public int getCount() {
        if(mSectionNames != null) {
            return mSectionNames.size();
        }
        else{
            return 0;
        }
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mSectionNames.get(position).getSectionName();
    }

    //Listener to see if the User changed their UserPreferences for either Sections or Sources
    @Override
    public void onChange(Object element) {
        if(element instanceof UserPreferences){
            mSectionNames = ((UserPreferences) element).getSectionList();
            notifyDataSetChanged();
        }
    }
}