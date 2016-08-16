package com.jonathan.james.eric.project_3;


import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by erickivet on 8/14/16.
 */

public class Article extends RealmObject {

    @PrimaryKey
    private String url;

    private String leadParagraph;
    private String headline;
    private String source;
    private String section;
    private String date;
    private Multimedia leadImage;
    private boolean bookmark;


    public String getUrl(){
        return url;
    }

    public void setUrl(String url){
        this.url = url;
    }

    public String getLeadParagraph(){
        return leadParagraph;
    }

    public void setLeadParagraph(String leadParagraph){
        this.leadParagraph = leadParagraph;
    }

    public String getHeadline(){
        return headline;
    }

    public void setHeadline(String headline){
        this.headline = headline;
    }

    public String getArticleSource(){
        return source;
    }

    public void setArticleSource(String source){
        this.source = source;
    }

    public String getSection(){
        return section;
    }

    public void setSection(String section){
        this.section = section;
    }

    public String getDate(){
        return date;
    }

    public void setDate(String date){
        this.date = date;
    }

    public Multimedia getLeadImage() {
        return leadImage;
    }

    public void setLeadimage(Multimedia leadImage) {
        this.leadImage = leadImage;
    }

    public boolean isBookmark() {
        return bookmark;
    }

    public void setBookmark(boolean bookmark){
        this.bookmark = bookmark;
    }




/*
    public static final String URL = "url";
    public static final String HEADLINE = "headline";
    public static final String SNIPPET = "snippet";

    @PrimaryKey
    @SerializedName("web_url")
    private String webUrl;

    @SerializedName("main")
    private String headline;

    @SerializedName("snippet")
    private String snippet;

    @SerializedName("multimedia")
    private RealmList <NYTimesMultimedia> multimedia;

    public String getUrl(){
        return webUrl;
    }

    public void setUrl(String webUrl){
        this.webUrl = webUrl;
    }

    public String getHeadline(){
        return headline;
    }

    public void setHeadline(String headline){
        this.headline = headline;
    }

    public String getSnippet () {
        return snippet;
    }

    public void setSnippet(String snippet){
        this.snippet = snippet;
    }

*/





}
