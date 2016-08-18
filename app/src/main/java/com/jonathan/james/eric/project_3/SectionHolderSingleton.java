package com.jonathan.james.eric.project_3;

import android.content.Context;

import java.util.ArrayList;

/**
 * Created by Jonathan Taylor on 8/18/16.
 */
public class SectionHolderSingleton {

    private static SectionHolderSingleton ourInstance = new SectionHolderSingleton();
    private APIServices mAPIServices;
    private Context mContext;

    private ArrayList<Article> mCurrentSection;
    private ArrayList<Article> mQueryResult;

    public static SectionHolderSingleton getInstance() {
        return ourInstance;
    }

    public void setAPIService(APIServices services){
        mAPIServices = services;
    }

    private SectionHolderSingleton() {
    }

    public ArrayList<Article> setSection(Section section){
        mCurrentSection = new ArrayList(mAPIServices.topNews(section., mAPIServices.retrofitInit(mContext)));
        return mCurrentSection;
    }

    public ArrayList<Article> setQuery(String query){
        mCurrentSection = new ArrayList<>(mAPIServices.articleSearch(query, mAPIServices.retrofitInit(mContext)));
        return mCurrentSection;
    }

    public Article getSectionArticle(int position){
        if(mCurrentSection != null) {
            return mCurrentSection.get(position);
        }
        return null;
    }

    public Article getQueryArticle(int position){
        if(mQueryResult != null) {
            return mQueryResult.get(position);
        }
        return null;
    }
}
