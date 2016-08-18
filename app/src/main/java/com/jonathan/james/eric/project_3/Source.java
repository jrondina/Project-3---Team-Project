package com.jonathan.james.eric.project_3;

import io.realm.RealmObject;

/**
 * Created by erickivet on 8/16/16.
 */
public class Source extends RealmObject{
    private String sourceName;
    private boolean isActive;



    public String getSourceName() {
        return sourceName;
    }

    public void setName(String name) {
        this.sourceName = name;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }
}
