package com.jonathan.james.eric.project_3.interfaces;

import com.jonathan.james.eric.project_3.Article;

import java.util.ArrayList;

/**
 * Created by Jonathan Taylor on 8/18/16.
 */
public interface APIFetcher {
    void getArticles(String sectionName, APICallback callback);
}
