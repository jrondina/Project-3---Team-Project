package com.jonathan.james.eric.project_3;

import android.content.Intent;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.facebook.FacebookSdk;
import com.facebook.appevents.AppEventsLogger;


import com.jonathan.james.eric.project_3.interfaces.APIFetcher;
import com.jonathan.james.eric.project_3.interfaces.ArticleListener;
import com.jonathan.james.eric.project_3.interfaces.SectionCardListener;
import com.jonathan.james.eric.project_3.presenters.SectionsPagerAdapter;

import java.util.ArrayList;

public class SectionPageAdapterActivity extends AppCompatActivity implements APIFetcher,
        NavigationView.OnNavigationItemSelectedListener, ArticleListener, SectionCardListener {

    public static final String ARTICLE_DETAIL_INDEX_EXTRA = "article_detail_index_extra";

    private SectionsPagerAdapter mSectionsPagerAdapter;

    private ViewPager mViewPager;
    private TabLayout mTabLayout;

    private FragmentManager mManager;

    private APIServices mAPIServices;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_section_page_adapter);
        FacebookSdk.sdkInitialize(getApplicationContext());
        AppEventsLogger.activateApp(this);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);



        mAPIServices = new APIServices(); //instantiates an API Service

        /* IGNORE THIS, DEBUG STUFF
        apiservice.articleSearch("harambe", apiservice.retrofitInit(this));
        apiservice.topNews("home",apiservice.retrofitInit(this));
        */


        mViewPager = (ViewPager) findViewById(R.id.section_fragment_container);
        mTabLayout = (TabLayout) findViewById(R.id.section_tabs);


        //Set up the Fragment Manager
        mManager = getSupportFragmentManager();




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

        //TestCode
        ArrayList<String> mSectionNames = new ArrayList<>();
        mSectionNames.add("top news");
        mSectionNames.add("technology");
        mSectionNames.add("science");
        mSectionNames.add("politics");
        mSectionNames.add("world");

        mSectionsPagerAdapter = new SectionsPagerAdapter(mManager, mSectionNames, this, this, this);
        mViewPager.setAdapter(mSectionsPagerAdapter);

        mTabLayout.setupWithViewPager(mViewPager);
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
        getMenuInflater().inflate(R.menu.section_page_adapter, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        //ToDo add one for each Section -- Also be sure to check if the source is active or not and spawn the fragment accordingly
        switch(id){

        }



        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onBookMarkClick(Article a) {
        RealmUtility.insertArticle(a);
    }

    @Override
    public void onShareClick(Article a) {

    }

    @Override
    public void onCardClick(ArrayList<Article> list, int position) {
        Intent articleDetailIntent = new Intent(this, ArticleDetailActivity.class);
        articleDetailIntent.putExtra(ARTICLE_DETAIL_INDEX_EXTRA, position);
        startActivity(articleDetailIntent);
    }


    //Methods for getting API calls in the ViewPager
    @Override
    public ArrayList<Article> getTopNewsArticles(String sectionName) {
        return mAPIServices.topNews(sectionName, mAPIServices.retrofitInit(this));
    }

    @Override
    public ArrayList<Article> getSearchArticles(String query) {
        return mAPIServices.articleSearch(query, mAPIServices.retrofitInit(this));
    }
}
