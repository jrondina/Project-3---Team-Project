package com.jonathan.james.eric.project_3;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jonathan Taylor on 8/18/16.
 */
public class ArticleListSingleton {

    private List<Article> mSectionArticles;
    private List<Article> mQueryResults;


    private static ArticleListSingleton sSingleton;

    private ArticleListSingleton(){}

    public static ArticleListSingleton getInstance(){
        if(sSingleton == null){
            sSingleton = new ArticleListSingleton();
        }
        return sSingleton;
    }

    public List<Article> getSectionArticles() {
        return mSectionArticles;
    }

    public void setSectionArticles(List<Article> mSectionArticles) {
        this.mSectionArticles = mSectionArticles;
    }

    public List<Article> getQueryResults() {
        return mQueryResults;
    }

    public void setQueryResults(List<Article> mQueryResults) {
        this.mQueryResults = mQueryResults;
    }
}
