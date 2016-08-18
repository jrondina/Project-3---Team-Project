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
import android.view.View;

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
import com.jonathan.james.eric.project_3.interfaces.ArticleListener;
import com.jonathan.james.eric.project_3.interfaces.SectionCardListener;
import com.jonathan.james.eric.project_3.presenters.SectionsPagerAdapter;

import java.io.IOException;
import java.util.ArrayList;

import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.auth.AccessToken;

public class SectionPageAdapterActivity extends AppCompatActivity implements
        NavigationView.OnNavigationItemSelectedListener, ArticleListener, SectionCardListener {

    private SectionsPagerAdapter mSectionsPagerAdapter;

    private ViewPager mViewPager;
    private TabLayout mTabLayout;

    private FragmentManager mManager;

    CallbackManager callbackManager;
    ShareDialog shareDialog;


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

        mSectionsPagerAdapter = new SectionsPagerAdapter(mManager, mSectionNames, this, this);
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

    @Override
    public void onCardClick(Article a) {

        //ToDo add code for setting up Fragment Pager Adapter




    }


    //Share to twitter and FB stuff not in use because we're just sharing via intent
    /*

    private void fbShare(String url) {

        if (ShareDialog.canShow(ShareLinkContent.class)) {
            ShareLinkContent linkContent = new ShareLinkContent.Builder()
                    //.setContentTitle("Hello Facebook")
                    //.setContentDescription(
                    //        "The 'Hello Facebook' sample  showcases simple Facebook integration")
                    .setContentUrl(Uri.parse(url))
                    .build();

            shareDialog.show(linkContent);
        }

    }

    private void sendTweet(String url) {

        try {
            Twitter twitter = new TwitterFactory().getInstance();

            twitter.setOAuthConsumer(CONSUMER_KEY_TW, CONSUMER_SECRET_TW);

            AccessToken accessToken = new AccessToken(ACCESS_TOKEN_TW, ACCESS_SECRET_TW);
            twitter.setOAuthAccessToken(accessToken);

            twitter.updateStatus(url);

        } catch (TwitterException te) {
            te.printStackTrace();
        }
    }

    private void loginTwitter() throws IOException {

        final OAuth10aService service = new ServiceBuilder()
                .apiKey(CONSUMER_KEY_TW)
                .apiSecret(CONSUMER_SECRET_TW)
                .build(TwitterApi.instance());

        final OAuth1RequestToken requestToken = service.getRequestToken();

        String authUrl = service.getAuthorizationUrl(requestToken);

        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("text/plain");
        intent.putExtra(Intent.EXTRA_TEXT, authUrl);
        startActivity(intent);

        String OAuthVerifier = "";

        final OAuth1AccessToken accessToken = service.getAccessToken(
                requestToken, OAuthVerifier);

        final OAuthRequest request = new OAuthRequest(Verb.GET,
                "https://api.twitter.com/1.1/account/verify_credentials.json", service);
        service.signRequest(accessToken, request); // the access token from step 4
        final Response response = request.send();
        System.out.println(response.getBody());


    }*/
}
