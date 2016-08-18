package com.jonathan.james.eric.project_3;

import android.content.Intent;
import android.net.Uri;
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

import com.facebook.CallbackManager;
import com.facebook.FacebookSdk;
import com.facebook.appevents.AppEventsLogger;
import com.facebook.share.model.ShareLinkContent;
import com.facebook.share.widget.ShareDialog;
import com.github.scribejava.apis.TwitterApi;
import com.github.scribejava.core.builder.ServiceBuilder;
import com.github.scribejava.core.model.OAuth1AccessToken;
import com.github.scribejava.core.model.OAuth1RequestToken;
import com.github.scribejava.core.model.OAuthRequest;
import com.github.scribejava.core.model.Response;
import com.github.scribejava.core.model.Verb;
import com.github.scribejava.core.oauth.OAuth10aService;


import com.jonathan.james.eric.project_3.interfaces.APIFetcher;
import com.jonathan.james.eric.project_3.interfaces.ArticleListener;
import com.jonathan.james.eric.project_3.interfaces.SectionCardListener;
import com.jonathan.james.eric.project_3.interfaces.SwipeListener;
import com.jonathan.james.eric.project_3.presenters.SectionsPagerAdapter;

import java.io.IOException;
import java.util.ArrayList;

import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.auth.AccessToken;


public class SectionPageAdapterActivity extends AppCompatActivity implements APIFetcher, SwipeListener,
        NavigationView.OnNavigationItemSelectedListener, ArticleListener, SectionCardListener {


    private SectionsPagerAdapter mSectionsPagerAdapter;

    private ViewPager mViewPager;
    private TabLayout mTabLayout;

    private FragmentManager mManager;

    private APIServices mAPIServices;
    private ArrayList<Article> mCurrentSection;
    private ArrayList<Article> mCurrentQuery;

    private CallbackManager callbackManager;
    private ShareDialog shareDialog;


    /*Twiter OAuth stuff no longer in use since we're doing intent for sharing
    String CONSUMER_KEY_TW = "6FCKnOeCVFehIiunrWnL6itSO";
    String CONSUMER_SECRET_TW = " c7kGW8TLiHywj367U1b8ScCSE1g86NZp2H1A3FHasfXrXlNf5u";
    String ACCESS_TOKEN_TW = "";
    String ACCESS_SECRET_TW = "";
    */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_section_page_adapter);
        FacebookSdk.sdkInitialize(getApplicationContext());
        callbackManager = CallbackManager.Factory.create();
        shareDialog = new ShareDialog(this);
        //AppEventsLogger.activateApp(this);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);



        mAPIServices = new APIServices(); //instantiates an API Service



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

        mManager.beginTransaction().add(R.id.main_content_container, SectionViewPagerFragment.getInstance(mManager,
                mSectionNames, this, this, this));
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
            //TODO finish intent to go to Settings page
            Intent settingsIntent = new Intent();
            return true;
        }
        if (id == R.id.action_search){

        }
        if (id == R.id.action_share){

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
        RealmUtility realmUtility = new RealmUtility();
        //toggle the boolean for is bookmarked
        a.setBookmark(!a.isBookmark());
        if(a.isBookmark()) {
            realmUtility.insertArticle(a);
        } else{
            realmUtility.deleteArticle(a);
        }
    }

    @Override
    public void onShareClick(Article a) {

        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("text/plain");
        intent.putExtra(Intent.EXTRA_TEXT, a.getUrl());
        startActivity(intent);

        //these are no longer in use
        //fbShare(a.getUrl());
        //sendTweet(a.getUrl());

    }

    //Open the article detail view
    @Override
    public void onCardClick(int position) {
        mManager.beginTransaction().addToBackStack("Sections").add(R.id.section_fragment_container,
                ArticleDetailFragment.getInstance(this, this, mCurrentSection.get(position)));
        //TODO
    }

    //get the article list for the current section
    @Override
    public ArrayList<Article> getArticles(String sectionName) {
        //mCurrentSection = new ArrayList(mAPIServices.topNews(sectionName, mAPIServices.retrofitInit(this)));

        //test code
        mCurrentSection = new ArrayList<Article>();
        Article a = new Article();
        a.setHeadline("Test Headline");
        a.setByline("By Test Author");
        a.setSection("world");
        a.setUrl("http://www.nytimes.com/2016/08/13/sports/olympics/a-closer-look-at-simone-manuel-olympic-medalist-history-maker.html");
        Multimedia m = new Multimedia();
        m.setThumbnailImage("https://static01.nyt.com/images/2016/08/17/magazine/17mag-cholera-1/17mag-cholera-1-thumbLarge.jpg");
        m.setRegularImage("https://static01.nyt.com/images/2016/08/17/magazine/17mag-cholera-1/17mag-cholera-1-superJumbo.jpg");
        m.setCaption("Test Image");
        a.setLeadimage(m);
        mCurrentSection.add(a);
        mCurrentSection.add(a);
        return mCurrentSection;
    }

    @Override
    public Article onSwipe(int direction) {
        return null;
    }
}
