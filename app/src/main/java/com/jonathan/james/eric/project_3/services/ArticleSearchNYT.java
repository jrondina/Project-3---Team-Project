package com.jonathan.james.eric.project_3.services;

import com.jonathan.james.eric.project_3.models.ArticleSearch.ArticleSearchList;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;

/**
 * Created by jamesrondina on 8/15/16.
 */
public interface ArticleSearchNYT {

    String API_KEY = "05af8d5d9e5a4decafd7a8bc32b6b076";

    @GET("search/v2/articlesearch.json" + "?apikey=" + API_KEY)
    Call<ArticleSearchList> getResult(@QueryMap Map<String, String> options);

}