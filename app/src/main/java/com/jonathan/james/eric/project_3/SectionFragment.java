package com.jonathan.james.eric.project_3;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jonathan.james.eric.project_3.interfaces.APICallback;
import com.jonathan.james.eric.project_3.interfaces.ArticleListener;
import com.jonathan.james.eric.project_3.interfaces.SectionCardListener;
import com.jonathan.james.eric.project_3.presenters.SectionRecyclerViewAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jonathan Taylor on 8/15/16.
 */
public class SectionFragment extends Fragment implements APICallback{

    private static final String TAG = "SectionFragment";

    private List<Article> mArticles;

    private RecyclerView mRecyclerView;
    private SectionRecyclerViewAdapter mAdapter;

    private SectionCardListener mCardViewListener;
    private ArticleListener mArticleListener;

    public static SectionFragment getInstance(List<Article> articles,
                                              SectionCardListener sectionCardListener, ArticleListener articleListener){
        SectionFragment fragment = new SectionFragment();
        fragment.mCardViewListener = sectionCardListener;
        fragment.mArticleListener = articleListener;
        fragment.mArticles = articles;
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_section, container, false);

        mRecyclerView = (RecyclerView) rootView.findViewById(R.id.section_fragment_recycler_view);

        //setup the new adapter
        mAdapter = new SectionRecyclerViewAdapter(mArticles, mArticleListener, mCardViewListener);

        return rootView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Log.d(TAG, "onViewCreated: setting recycler view");

        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(view.getContext(), LinearLayoutManager.VERTICAL, false));
    }

    public void changeArticleList(ArrayList<Article> articles){
        mArticles = articles;
        mAdapter.changeArticleList(articles);
    }

    @Override
    public void onResume() {
        super.onResume();
        if(mArticles == null){
            mArticles = ArticleListSingleton.getInstance().getSectionArticles();
            mAdapter.changeArticleList(mArticles);
        }

    }

    @Override
    public void responseFinished(List<Article> responseList) {
        changeArticleList(new ArrayList<Article>(responseList));
    }
}
