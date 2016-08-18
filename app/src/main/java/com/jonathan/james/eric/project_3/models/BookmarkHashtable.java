package com.jonathan.james.eric.project_3.models;

import java.util.HashMap;

/**
 * Created by erickivet on 8/16/16.
 */
public class BookmarkHashtable {
    HashMap<String, Boolean> urlHashmap;

    private static BookmarkHashtable ourInstance = new BookmarkHashtable();

    public static BookmarkHashtable getInstance() {
        return ourInstance;
    }

    public void setUrlHashmap(HashMap<String, Boolean> urlHashmap){
        this.urlHashmap = urlHashmap;
    }

    public boolean isBookmarked(String url){
        return urlHashmap.containsKey(url);
    }

    private BookmarkHashtable() {
    }
}
