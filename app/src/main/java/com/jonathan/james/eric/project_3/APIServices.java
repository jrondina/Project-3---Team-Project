package com.jonathan.james.eric.project_3;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;

import com.jonathan.james.eric.project_3.models.ArticleSearch.ArticleSearchList;
import com.jonathan.james.eric.project_3.models.ArticleSearch.Doc;
import com.jonathan.james.eric.project_3.models.ArticleSearch.Multimedium;
import com.jonathan.james.eric.project_3.models.TopNews.Result;
import com.jonathan.james.eric.project_3.models.TopNews.TopNewsList;
import com.jonathan.james.eric.project_3.services.ArticleSearchNYT;
import com.jonathan.james.eric.project_3.services.TopNewsNYT;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by jamesrondina on 8/16/16.
 */
public class APIServices {

    public static final String LOG_DIVIDER = "=====================================================";

    public static final String TAG = "APIServices";
    public static final String baseUrl = "https://api.nytimes.com/svc/";

    public static final int IMG_TOP_THUMB = 1;
    public static final int IMG_TOP_LARGE = 4;

    public String section;
    public String query;
    public Map<String, String> options;

    public Retrofit retrofitInit(Context context) {

        if (section == null) {
            section = "home";
        }

        Log.i(TAG, "fetching Articles from API");

        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();

        Retrofit retrofit = null;

        if (netInfo != null && netInfo.isConnected()) {

            //create Retrofit service

            retrofit = new Retrofit.Builder()
                    .baseUrl(baseUrl)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();


        }
        else {
            Log.i(TAG, "topNews: Connection established");
        }

        return retrofit;
    }

    public void articleSearch(String query, Retrofit retrofit) {

        Log.i(TAG, "articleSearch: Starting search for: " + query);

        options = new HashMap<>();
        options.put("q",query);
        options.put("fq","type_of_material:(!\"Letter\")");
        options.put("sort","newest");

        //create instance of ArticleSearch and get Call

        ArticleSearchNYT service = retrofit.create(ArticleSearchNYT.class);

        Call<ArticleSearchList> call = service.getResult(options);

        //make API call

        call.enqueue(new Callback<ArticleSearchList>() {
            @Override
            public void onResponse(Call<ArticleSearchList> call,
                                   Response<ArticleSearchList> ArticleSearchResponse) {
                List<Doc> docs = ArticleSearchResponse.body().getResponse().getDocs();

                String imgThumb = "";
                String imgLarge = "";

                for (Doc article: docs
                        ) {

                    Log.i(TAG, LOG_DIVIDER);

                    Log.i(TAG, "Headline: " + article.getHeadline().getMain());
                    Log.i(TAG, "Published on: " + article.getPubDate());
                    try{
                            Log.i(TAG, "By: " + article.getByline().getOriginal());

                    }catch (NullPointerException e) {
                        Log.e(TAG, "No byline" );
                    }
                    Log.i(TAG, "URL: " + article.getWebUrl());
                    if (article.getMultimedia().size() > 2) {

                        for (Multimedium image: article.getMultimedia()
                             ) {
                            if (image.getSubtype().equals("thumbnail") || image.getSubtype().equals("wide")) {
                                imgThumb = image.getUrl();
                            }
                            if (image.getSubtype().equals("xlarge")) {
                                imgLarge = image.getUrl();
                            }

                        }
                        if (!imgThumb.equals("")) {
                            Log.i(TAG, "ThumbURL: " + "https://static01.nyt.com/" + imgThumb);
                        }
                        if (!imgLarge.equals("")) {
                            Log.i(TAG, "LargeURL: " + "https://static01.nyt.com/" +
                                    imgLarge);
                        }

                    }

                    Log.i(TAG, LOG_DIVIDER);
                }
            }

            @Override
            public void onFailure(Call<ArticleSearchList> call, Throwable t) {
                Log.e(TAG, "onFailure: Could not establish Connection", t);


            }
        });

    }

    public void topNews(String section, Retrofit retrofit) {


        //create instance of TopNewsNYT and get Call

        TopNewsNYT service = retrofit.create(TopNewsNYT.class);

        Call<TopNewsList> call = service.getResult(section);

        //make API call

        call.enqueue(new Callback<TopNewsList>() {
            @Override
            public void onResponse(Call<TopNewsList> call, Response<TopNewsList> topNewsListResponse) {

                List<Result> list = topNewsListResponse.body().getResults();

                for (Result article:list) {

                    Log.i(TAG, LOG_DIVIDER);

                    Log.i(TAG, "Headline: " + article.getTitle());
                    Log.i(TAG, "By: " + article.getByline());
                    Log.i(TAG, "Abstract: " + article.getAbstract());
                    Log.i(TAG, "URL: " + article.getUrl());
                    if (article.getMultimedia().size() != 0) {
                        Log.i(TAG, "ThumbURL: " +
                                article.getMultimedia().get(IMG_TOP_THUMB).getUrl());
                        Log.i(TAG, "LargeURL: " +
                                article.getMultimedia().get(IMG_TOP_LARGE).getUrl());
                    }

                    Log.i(TAG, LOG_DIVIDER);
                }
            }

            @Override
            public void onFailure(Call<TopNewsList> call, Throwable t) {

                Log.e(TAG, "onFailure: Could not establish Connection", t);


            }
        });

    }

}
