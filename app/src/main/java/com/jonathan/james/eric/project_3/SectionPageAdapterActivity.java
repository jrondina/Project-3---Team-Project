package com.jonathan.james.eric.project_3;

import android.app.job.JobInfo;
import android.app.job.JobScheduler;
import android.content.ComponentName;
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
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.jonathan.james.eric.project_3.interfaces.APICallback;
import com.jonathan.james.eric.project_3.interfaces.APIFetcher;
import com.jonathan.james.eric.project_3.interfaces.ArticleListener;
import com.jonathan.james.eric.project_3.interfaces.SectionCardListener;
import com.jonathan.james.eric.project_3.interfaces.SwipeListener;
import com.jonathan.james.eric.project_3.interfaces.ToolbarLoadedCallback;
import com.jonathan.james.eric.project_3.presenters.SectionsPagerAdapter;

import java.util.ArrayList;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmList;

public class SectionPageAdapterActivity extends AppCompatActivity implements APIFetcher, SwipeListener,
        NavigationView.OnNavigationItemSelectedListener, ArticleListener, SectionCardListener, ToolbarLoadedCallback {

    private static final String TAG = "MainActivity";


    private SectionsPagerAdapter mSectionsPagerAdapter;

    private ViewPager mViewPager;
    private TabLayout mTabLayout;

    private FragmentManager mManager;

    long NOTIFICATION_INTERVAL = 10_800_000; //3 hours in milliseconds

    private APIServices mAPIServices;
    private ArrayList<Article> mCurrentSection;
    private ArrayList<Article> mCurrentQuery;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_section_page_adapter);

        //JobScheduler for jobs
        JobScheduler jobScheduler =
                (JobScheduler) getSystemService(JOB_SCHEDULER_SERVICE);
        jobScheduler.schedule(noteScheduler()); //schedules notifications at intervals




        mAPIServices = new APIServices(); //instantiates an API Service
        Log.d(TAG, "onCreate: getting API services");

        mViewPager = (ViewPager) findViewById(R.id.section_fragment_container);
        mTabLayout = (TabLayout) findViewById(R.id.section_tabs);

        //Set up the Fragment Manager
        mManager = getSupportFragmentManager();

        //init Realm Default Instance
        RealmConfiguration config = new RealmConfiguration.Builder(this.getApplicationContext()).build();
        Realm.setDefaultConfiguration(config);

        //initialize the UserPreferences object
        if(new RealmUtility().getUserPreferences() == null) {
            initUserPrefs();
        } else{
            new RealmUtility().deleteUserPreferences(new RealmUtility().getUserPreferences());
            initUserPrefs();
        }


    }

    //TODO remove once method in Realm Utility done
    private void initUserPrefs() {
        UserPreferences userPreferences = new UserPreferences();
        RealmList<Section> sections = new RealmList<>();
        Section home = new Section();
        home.setActive(true);
        home.setKey("home");
        home.setSectionName("Home");
        sections.add(home);

        Section world = new Section();
        world.setActive(true);
        world.setKey("world");
        world.setSectionName("World");
        sections.add(world);

        Section technology = new Section();
        technology.setActive(true);
        technology.setKey("technology");
        technology.setSectionName("Technology");
        sections.add(technology);

        Section business = new Section();
        business.setActive(true);
        business.setKey("business");
        business.setSectionName("Business");
        sections.add(world);

        RealmList<Source> sources = new RealmList<>();
        Source s = new Source();
        s.setName("New York Times");
        s.setActive(true);
        sources.add(s);

        userPreferences.setSectionList(sections);
        userPreferences.setSourceList(sources);
        userPreferences.setUserName("default");

        new RealmUtility().insertUserPreferences(userPreferences);
        Log.d(TAG, "initUserPrefs: saving user preferences to the database" );
    }

    @Override
    protected void onResume() {
        super.onResume();

        Log.d(TAG, "onResume: Creating the manager and Fragments");
        mManager.beginTransaction().add(R.id.main_content_container, SectionViewPagerFragment.getInstance(mManager,
                this, this, this, this)).commit();
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

        //these are no longer in use
        //fbShare(a.getUrl());
        //sendTweet(a.getUrl());

        shareLink(a.getUrl());

    }

    //Open the article detail view
    @Override
    public void onCardClick(int position) {
        Log.d(TAG, "onCardClick: opening article detail");
        mManager.beginTransaction().addToBackStack("Sections").add(R.id.section_fragment_container,
                ArticleDetailFragment.getInstance(this, this, this, mCurrentSection.get(position))).commit();

    }

    private void shareLink(String url) {

        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("text/plain");
        intent.putExtra(Intent.EXTRA_TEXT, url);
        startActivity(intent);

    }

    public JobInfo noteScheduler() {
        JobInfo job = new JobInfo.Builder(1,
                new ComponentName(getPackageName(),
                        NotifyService.class.getName()))
                .setPeriodic(NOTIFICATION_INTERVAL) // in milliseconds
                .build();

        return job;
    }
    //get the article list for the current section
    @Override
    public void getArticles(String sectionName, APICallback callback) {
        mCurrentSection = new ArrayList(mAPIServices.topNews(sectionName, mAPIServices.retrofitInit(this), callback));

        //test code
//        mCurrentSection = new ArrayList<Article>();
//        Article a = new Article();
//        a.setHeadline("Test Headline");
//        a.setByline("By Test Author");
//        a.setSection("world");
//        a.setUrl("http://www.nytimes.com/2016/08/13/sports/olympics/a-closer-look-at-simone-manuel-olympic-medalist-history-maker.html");
//        Multimedia m = new Multimedia();
//        m.setThumbnailImage("https://static01.nyt.com/images/2016/08/17/magazine/17mag-cholera-1/17mag-cholera-1-thumbLarge.jpg");
//        m.setRegularImage("https://static01.nyt.com/images/2016/08/17/magazine/17mag-cholera-1/17mag-cholera-1-superJumbo.jpg");
//        m.setCaption("Test Image");
//        a.setLeadimage(m);
//        mCurrentSection.add(a);
//        mCurrentSection.add(a);
    }

    @Override
    public Article onSwipe(int direction) {
        return null;
    }

    @Override
    public void ToolbarLoaded(Toolbar toolbar) {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }
}
