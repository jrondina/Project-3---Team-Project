package com.jonathan.james.eric.project_3;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.jonathan.james.eric.project_3.interfaces.ArticleListener;
import com.jonathan.james.eric.project_3.interfaces.BookmarkChangeListener;

public class ArticleDetailActivity extends AppCompatActivity implements ArticleListener, BookmarkChangeListener {

    private static final String TAG = "ArticleDetailActivity";

    private Article mArticle;
    private int articleIndex;

    private WebView mWebView;

    private Toolbar toolbar;

    private Menu mMenu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.app_bar_article_detail);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        articleIndex = getIntent().getIntExtra(SectionPageAdapterActivity.ARTICLE_INDEX, -1);
        if(articleIndex >= 0){
            mArticle = ArticleListSingleton.getInstance().getSectionArticles().get(articleIndex);
        }

        mWebView = (WebView) findViewById(R.id.article_detail_web_view);
    }

    @Override
    protected void onResume() {
        super.onResume();
        if(mArticle != null){


            mWebView.getSettings().setJavaScriptEnabled(true);
            mWebView.getSettings().setDomStorageEnabled(true);
            mWebView.setWebViewClient(new WebViewClient() {
                //TODO: add error catching here with "onReceivedError"
            });
            Log.d("WebView", mArticle.getUrl());
            mWebView.loadUrl(mArticle.getUrl());
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.article_detail, menu);
        mMenu = menu;

        if(mArticle != null && mArticle.isBookmark()){
            Log.d(TAG, "onResume: changing to bookmarked icon");
            mMenu.findItem(R.id.action_bookmark).setIcon(R.drawable.ic_bookmark_black_24dp);
        }
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            //TODO finish intent to go to Settings page
            Intent settingsIntent = new Intent();
            return true;
        }
        if (id == R.id.action_search){

        }
        if (id == R.id.action_share){
            onShareClick(mArticle);
        }
        if(id == R.id.action_bookmark){
            onBookMarkClick(mArticle, this);
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBookMarkClick(Article a, BookmarkChangeListener bookmarkChangeListener) {
        RealmUtility realmUtility = new RealmUtility();
        //toggle the boolean for is bookmarked
        realmUtility.toggleBookmark(a, bookmarkChangeListener);
    }

    @Override
    public void onShareClick(Article a) {
        shareLink(a.getUrl());
    }

    private void shareLink(String url) {
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("text/plain");
        intent.putExtra(Intent.EXTRA_TEXT, url);
        startActivity(intent);

    }

    @Override
    public void bookmarksChanged() {
        if(mArticle.isBookmark()) {
            mMenu.findItem(R.id.action_bookmark).setIcon(R.drawable.ic_bookmark_black_24dp);
        } else{
            mMenu.findItem(R.id.action_bookmark).setIcon(R.drawable.ic_bookmark_border_black_24dp);
        }
    }
}
