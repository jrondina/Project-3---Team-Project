package com.jonathan.james.eric.project_3;

import java.util.ArrayList;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.RealmModel;

/**
 * Created by erickivet on 8/15/16.
 */
public class Section extends RealmObject{

    private String sectionName;
    private String key;
    private boolean isActive;

    public String getSectionName() {
        return sectionName;
    }

    public void setSectionName(String sectionName) {
        this.sectionName = sectionName;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }
}
