package com.jonathan.james.eric.project_3;

import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.jonathan.james.eric.project_3.interfaces.ArticleListener;
import com.jonathan.james.eric.project_3.interfaces.SectionCardListener;
import com.jonathan.james.eric.project_3.presenters.SectionRecyclerViewAdapter;

/**
 * Created by Jonathan Taylor on 8/15/16.
 */
public class SectionFragment extends Fragment {

    private String mSectionName;

    private RecyclerView mRecyclerView;
    private SectionRecyclerViewAdapter mAdapter;

    private SectionCardListener mCardViewListener;
    private ArticleListener mArticleListener;

    public static SectionFragment getInstance(String sectionName, SectionCardListener sectionCardListener,
                                              ArticleListener articleListener){
        SectionFragment fragment = new SectionFragment();
        fragment.mSectionName = sectionName;
        fragment.mCardViewListener = sectionCardListener;
        fragment.mArticleListener = articleListener;
        return fragment;
    }

    @Override
    public void onResume() {
        super.onResume();

    }
}
