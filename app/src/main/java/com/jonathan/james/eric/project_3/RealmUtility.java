package com.jonathan.james.eric.project_3;

import android.support.annotation.UiThread;
import android.util.Log;

import java.io.Closeable;
import java.io.IOException;

import io.realm.Realm;
import io.realm.RealmAsyncTask;
import io.realm.RealmList;
import io.realm.RealmResults;

/**
 * Created by erickivet on 8/16/16.
 */
public class RealmUtility implements Closeable{
    //private final Realm realm = Realm.getDefaultInstance();
    //Article article;
    Realm realm;
    private static final String TAG = "RealmUtility";

    @UiThread
    public RealmUtility(){
        realm = Realm.getDefaultInstance();
    }

    public Article createArticle(String url, String leadParagraph, String headline,
                              String byline, String source, String section,
                              String date, Multimedia leadImage, boolean bookmark){
        //Realm realm = Realm.getDefaultInstance();
        //realm.beginTransaction();
        //Article article = realm.createObject(Article.class);
        Article article = new Article();

        article.setUrl(url);
        article.setLeadParagraph(leadParagraph);
        article.setHeadline(headline);
        article.setByline(byline);
        article.setArticleSource(source);
        article.setSection(section);
        article.setDate(date);
        article.setLeadimage(leadImage);
        article.setBookmark(bookmark);

        return article;
        //realm.commitTransaction();
    }

    public void insertArticle(final Article a){

        RealmAsyncTask realmAsyncTask = realm.executeTransactionAsync(new Realm.Transaction() {
            @Override
            public void execute(Realm bgRealm) {
                bgRealm.insert(a);
            }


        }, new Realm.Transaction.OnError() {
            @Override
            public void onError(Throwable error) {
                Log.e(TAG,"Error Writing Article Realm Object to DB",error);

            }
        });

    }

    public void deleteArticle(final Article a){

        final RealmAsyncTask realmAsyncTask = realm.executeTransactionAsync(new Realm.Transaction() {
            @Override
            public void execute(Realm bgRealm) {
                RealmResults<Article> results = bgRealm.where(Article.class)
                        .contains("url",a.getUrl()).findAll();
                Log.d(TAG, "execute: realm results contains " + results.size());
                results.deleteAllFromRealm();
                RealmResults<Multimedia> multimediaresults = bgRealm.where(Multimedia.class)
                        .equalTo("regularImage",a.getLeadImage().getRegularImage()).findAll();
                Log.d(TAG,"multimedia object contains "+ results.size());
                multimediaresults.deleteAllFromRealm();
            }


        }, new Realm.Transaction.OnError() {
            @Override
            public void onError(Throwable error) {
                Log.e(TAG,"Error Deleting Article Realm Object from DB",error);

            }
        });
    }

    public RealmResults<Article> getBookmarkedArticles(){
        RealmResults<Article> articles = realm.where(Article.class).findAll();

        return articles;

    }

    public UserPreferences getUserPreferences(){
        UserPreferences preferences = realm.where(UserPreferences.class).findAll().first();
        return preferences;
    }

    public void createUserPreferences(RealmList<Section> sectionList ,RealmList<Source> sourceList){

        UserPreferences up = new UserPreferences();
        up.setSectionList(sectionList);
        up.setSourceList(sourceList);
    }

    public void insertUserPreferences(final UserPreferences i){

        RealmAsyncTask realmAsyncTask = realm.executeTransactionAsync(new Realm.Transaction() {
            @Override
            public void execute(Realm bgRealm) {
                bgRealm.insert(i);
            }


        }, new Realm.Transaction.OnError() {
            @Override
            public void onError(Throwable error) {
                Log.e(TAG,"Error Writing User Preferences Realm Object to DB",error);

            }
        });
    }

    public void deleteUserPreferences(final UserPreferences up){

        RealmAsyncTask realmAsyncTask = realm.executeTransactionAsync(new Realm.Transaction() {
            @Override
            public void execute(Realm bgRealm) {
                RealmResults<UserPreferences> results = bgRealm.where(UserPreferences.class)
                        .contains("userName",up.getUserName())
                        .findAll();
                Log.d(TAG, "execute: realm results contains " + results.size());
                results.deleteAllFromRealm();
            }


        }, new Realm.Transaction.OnError() {
            @Override
            public void onError(Throwable error) {
                Log.e(TAG,"Error Deleting User Preferences Realm Object from DB",error);

            }
        });

    }

    public void createSource(String s, boolean i){

        Source source = new Source();
        source.setName(s);
        source.setActive(i);
    }
    /*
    public void insertSource(final Source s){

        RealmAsyncTask realmAsyncTask = realm.executeTransactionAsync(new Realm.Transaction() {
            @Override
            public void execute(Realm bgRealm) {
                bgRealm.insert(s);
            }


        }, new Realm.Transaction.OnError() {
            @Override
            public void onError(Throwable error) {
                Log.e(TAG,"Error Writing Source Realm Object to DB",error);

            }
        });

    }


    public void deleteSource(final Source s){
        final RealmAsyncTask realmAsyncTask = realm.executeTransactionAsync(new Realm.Transaction() {
            @Override
            public void execute(Realm bgRealm) {
                RealmResults<Source> results = bgRealm.where(Source.class)
                        .contains("sourceName",s.getSourceName()).findAll();
                Log.d(TAG, "execute: realm results contains " + results.size());
                results.deleteAllFromRealm();
            }


        }, new Realm.Transaction.OnError() {
            @Override
            public void onError(Throwable error) {
                Log.e(TAG,"Error Deleting Article Realm Object from DB",error);

            }
        });

    }
    */

    public void createSection(String s, String k, boolean i){

        Section section = new Section();
        section.setSectionName(s);
        section.setKey(k);
        section.setActive(i);

    }
    /*
    public void insertSection(final Section s){

        RealmAsyncTask realmAsyncTask = realm.executeTransactionAsync(new Realm.Transaction() {
            @Override
            public void execute(Realm bgRealm) {
                bgRealm.insert(s);
            }

        }, new Realm.Transaction.OnError() {
            @Override
            public void onError(Throwable error) {
                Log.e(TAG,"Error Writing Section Realm Object to DB",error);

            }
        });
    }

    public void deleteSection(final Section s){
        final RealmAsyncTask realmAsyncTask = realm.executeTransactionAsync(new Realm.Transaction() {
            @Override
            public void execute(Realm bgRealm) {
                RealmResults<Section> results = bgRealm.where(Section.class)
                        .contains("sourceName",s.getSectionName()).findAll();
                Log.d(TAG, "execute: realm results contains " + results.size());
                results.deleteAllFromRealm();
            }


        }, new Realm.Transaction.OnError() {
            @Override
            public void onError(Throwable error) {
                Log.e(TAG,"Error Deleting Article Realm Object from DB",error);

            }
        });

    }
    */

    public void createMultimedia(String t, String r, String c){

        Multimedia multimedia = new Multimedia();
        multimedia.setThumbnailImage(t);
        multimedia.setRegularImage(r);
        multimedia.setCaption(c);
    }
    /*
    public void insertMultimedia (final Multimedia m){

        RealmAsyncTask realmAsyncTask = realm.executeTransactionAsync(new Realm.Transaction() {
            @Override
            public void execute(Realm bgRealm) {
                bgRealm.insert(m);
            }

        }, new Realm.Transaction.OnError() {
            @Override
            public void onError(Throwable error) {
                Log.e(TAG,"Error Writing Multimedia Realm Object to DB",error);

            }
        });

    }

    public void deleteMultimedia(){

    }
    */



    @Override
    public void close() throws IOException {
        realm.close();
    }


}
