package com.jonathan.james.eric.project_3;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.WebView;
import android.widget.Toast;

import java.util.ArrayList;

public class ArticleDetailActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    private static final String TAG = "ArticleDetailActivity";

    private WebView mWebView;

    private Article mArticle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_article_detail);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        mWebView = (WebView) findViewById(R.id.article_detail_web_view);


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
            this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        Intent intent = getIntent();
        int index = intent.getIntExtra(SectionPageAdapterActivity.ARTICLE_DETAIL_INDEX_EXTRA, -1);
        if(index < 0){
            Toast.makeText(ArticleDetailActivity.this, "Invalid Article ID", Toast.LENGTH_SHORT).show();
            finish();
        } else if(intent.getAction().equals(SectionPageAdapterActivity.FROM_SECTION)){
            //get the article from the singleton? with the index from the intent
            mArticle = SectionHolderSingleton.getInstance().getSectionArticle(index);
        } else if(intent.getAction().equals(SectionPageAdapterActivity.FROM_QUERY)){
            mArticle = SectionHolderSingleton.getInstance().getQueryArticle(index);
        } else {
            Log.d(TAG, "onResume: unable to find article. Invalid Action - " + intent.getAction());
            finish();
        }

        //TODO change this to process the HTML and show the article
        mWebView.loadUrl(mArticle.getUrl());
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.article_detail, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        switch(id){
            case R.id.action_settings:
                //TODO add intent for going to settings activity
                break;
            case R.id.action_share:
                //TODO link to James's share method
                break;
            case R.id.action_search:
                //TODO put in search?
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        //TODO make a copy of the SectionActivity Drawer
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        switch(id){
            case R.id.
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
