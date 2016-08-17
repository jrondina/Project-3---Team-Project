package com.jonathan.james.eric.project_3.services;

import com.jonathan.james.eric.project_3.models.TopNews.TopNewsList;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by jamesrondina on 8/13/16.
 */
public interface TopNewsNYT {

    String API_KEY = "05af8d5d9e5a4decafd7a8bc32b6b076";

    @GET("topstories/v2/{section}.json" + "?apikey=" + API_KEY)
    Call<TopNewsList> getResult(@Path("section") String section);

}