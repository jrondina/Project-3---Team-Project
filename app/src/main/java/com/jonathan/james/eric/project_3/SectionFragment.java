package com.jonathan.james.eric.project_3;

import android.support.v4.app.Fragment;

/**
 * Created by Jonathan Taylor on 8/15/16.
 */
public class SectionFragment extends Fragment {

    private String mSectionName;

    public static SectionFragment getInstance(String sectionName){
        SectionFragment fragment = new SectionFragment();
        fragment.mSectionName = sectionName;
        return fragment;
    }
}
