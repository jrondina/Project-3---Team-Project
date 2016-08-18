package com.jonathan.james.eric.project_3;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;

import com.jonathan.james.eric.project_3.interfaces.ArticleListener;
import com.jonathan.james.eric.project_3.interfaces.SwipeListener;

/**
 * Created by Jonathan Taylor on 8/18/16.
 */
public class ArticleDetailFragment extends Fragment {

    private WebView mWebView;

    private MenuItem mShare;

    private ArticleListener mArticleListener;
    private SwipeListener mSwipeListener;

    private Article mArticle;

    public static ArticleDetailFragment getInstance(ArticleListener articleListener, SwipeListener swipeListener,
                                              Article article){
        ArticleDetailFragment fragment = new ArticleDetailFragment();
        fragment.mArticleListener = articleListener;
        fragment.mSwipeListener = swipeListener;
        fragment.mArticle = article;
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.content_article_detail, container, false);
        mWebView = (WebView) rootView.findViewById(R.id.article_detail_web_view);


        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onResume() {
        super.onResume();

        mWebView.loadUrl(mArticle.getUrl());
    }

    public void setNewArticle(Article a){
        mArticle = a;
        mWebView.loadUrl(mArticle.getUrl());
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        // Inflate the menu; this adds items to the action bar if it is present.
        inflater.inflate(R.menu.article_detail, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }


}