package com.jonathan.james.eric.project_3.interfaces;

import com.jonathan.james.eric.project_3.Article;

import java.util.ArrayList;

/**
 * Created by Jonathan Taylor on 8/17/16.
 */
public interface APIFetcher {
    ArrayList<Article> getTopNewsArticles(String sectionName);
    ArrayList<Article> getSearchArticles(String query);
}
