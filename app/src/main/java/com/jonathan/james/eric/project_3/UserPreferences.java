package com.jonathan.james.eric.project_3;

import io.realm.RealmList;
import io.realm.RealmObject;

/**
 * Created by erickivet on 8/16/16.
 */
public class UserPreferences extends RealmObject{
    RealmList<Section> sectionList;
    RealmList<Source> sourceList;

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
