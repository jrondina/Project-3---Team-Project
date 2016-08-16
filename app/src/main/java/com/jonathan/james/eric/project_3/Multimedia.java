package com.jonathan.james.eric.project_3;

import io.realm.RealmList;
import io.realm.RealmObject;

/**
 * Created by erickivet on 8/15/16.
 */
public class Multimedia extends RealmObject {

    private String thumbnailImage;
    private String regularImage;
    private String caption;

    public String getThumbnailImage() {
        return thumbnailImage;
    }

    public void setThumbnailImage(String thumbnailImage) {
        this.thumbnailImage = thumbnailImage;
    }

    public String getCaption() {
        return caption;
    }

    public void setCaption(String caption) {
        this.caption = caption;
    }

    public String getRegularImage() {
        return regularImage;
    }

    public void setRegularImage(String regularImage) {
        this.regularImage = regularImage;
    }
}
