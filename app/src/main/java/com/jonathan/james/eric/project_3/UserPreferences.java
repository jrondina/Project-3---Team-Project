package com.jonathan.james.eric.project_3;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by erickivet on 8/16/16.
 */
public class UserPreferences extends RealmObject{

    @PrimaryKey
    String userName;
    RealmList<Section> sectionList;
    RealmList<Source> sourceList;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }



    public RealmList<Section> getSectionList() {
        return sectionList;
    }

    public void setSectionList(RealmList<Section> sectionList) {
        this.sectionList = sectionList;
    }

    public RealmList<Source> getSourceList() {
        return sourceList;
    }

    public void setSourceList(RealmList<Source> sourceList) {
        this.sourceList = sourceList;
    }
}
