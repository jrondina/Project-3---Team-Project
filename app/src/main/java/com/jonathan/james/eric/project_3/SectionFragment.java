package com.jonathan.james.eric.project_3;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.jonathan.james.eric.project_3.interfaces.APICallback;
import com.jonathan.james.eric.project_3.interfaces.ArticleListener;
import com.jonathan.james.eric.project_3.interfaces.BookmarkChangeListener;
import com.jonathan.james.eric.project_3.interfaces.SectionCardListener;
import com.jonathan.james.eric.project_3.presenters.SectionRecyclerViewAdapter;

import java.util.ArrayList;
import java.util.List;

import io.realm.RealmResults;

/**
 * Created by Jonathan Taylor on 8/15/16.
 */
public class SectionFragment extends Fragment implements APICallback, BookmarkChangeListener{

    private static final String TAG = "SectionFragment";

    private List<Article> mArticles;
    //type to determine if bookmarks or API call based
    //0 - APICall
    //1 - bookmarks
    private int mType;

    private RecyclerView mRecyclerView;
    private SectionRecyclerViewAdapter mAdapter;

    private SectionCardListener mCardViewListener;
    private ArticleListener mArticleListener;

    private Context context;
    private TextView mMissingContent;

    public static SectionFragment getInstance(List<Article> articles, int type,
                                              SectionCardListener sectionCardListener, ArticleListener articleListener){
        SectionFragment fragment = new SectionFragment();
        fragment.mCardViewListener = sectionCardListener;
        fragment.mArticleListener = articleListener;
        fragment.mArticles = articles;
        fragment.mType = type;
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_section, container, false);

        mRecyclerView = (RecyclerView) rootView.findViewById(R.id.section_fragment_recycler_view);
        mMissingContent = (TextView) rootView.findViewById(R.id._section_fragment_missing_message);

        //setup the new adapter
        mAdapter = new SectionRecyclerViewAdapter(mArticles, mArticleListener, mCardViewListener, this, mType);

        return rootView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Log.d(TAG, "onViewCreated: setting recycler view");

        context = view.getContext();

        //Show a message on the screen if no content
        if(mArticles == null && mType == 1){
            mMissingContent.setVisibility(View.VISIBLE);
        }

//        mRecyclerView.setAdapter(mAdapter);
//        mRecyclerView.setLayoutManager(new LinearLayoutManager(view.getContext(), LinearLayoutManager.VERTICAL, false));
    }

    public void changeArticleList(ArrayList<Article> articles){
        mArticles = articles;
        mAdapter.changeArticleList(articles);
    }

    @Override
    public void onResume() {
        super.onResume();
        if(mArticles == null && mType == 0){
            mArticles = ArticleListSingleton.getInstance().getSectionArticles();
            mAdapter.changeArticleList(mArticles);
        } else if(mArticles == null && mType == 1){
            mArticles = new RealmUtility().getBookmarkedArticles();
        }


        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false));

        //TODO spawn dialogue if there are no bookmark articles

    }

    @Override
    public void responseFinished(List<Article> responseList) {
        changeArticleList(new ArrayList<>(responseList));
    }

    @Override
    public void bookmarksChanged() {
        if(mType == 1) {
            mArticles = new RealmUtility().getBookmarkedArticles();
            mAdapter.changeArticleList(mArticles);
        }
    }
}
