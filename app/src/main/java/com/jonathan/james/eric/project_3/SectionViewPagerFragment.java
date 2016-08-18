package com.jonathan.james.eric.project_3;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jonathan.james.eric.project_3.interfaces.APIFetcher;
import com.jonathan.james.eric.project_3.interfaces.ArticleListener;
import com.jonathan.james.eric.project_3.interfaces.SectionCardListener;
import com.jonathan.james.eric.project_3.presenters.SectionsPagerAdapter;

import java.util.ArrayList;

/**
 * Created by Jonathan Taylor on 8/18/16.
 */
public class SectionViewPagerFragment extends Fragment {

    private SectionsPagerAdapter mSectionsPagerAdapter;

    private ViewPager mViewPager;
    private TabLayout tabLayout;
    private Toolbar toolbar;

    private FragmentManager mManager;
    private ArrayList<String> mSectionNames;

    private APIFetcher mFetcher;
    private SectionCardListener mSectionCardListener;
    private ArticleListener mArticleListener;


    public static SectionViewPagerFragment getInstance(FragmentManager manager, ArrayList<String> sections,
                                                       APIFetcher fetcher, SectionCardListener cardListener,
                                                       ArticleListener articleListener){
        SectionViewPagerFragment fragment = new SectionViewPagerFragment();
        fragment.mManager = manager;
        fragment.mSectionNames = sections;
        fragment.mFetcher = fetcher;
        fragment.mSectionCardListener = cardListener;
        fragment.mArticleListener = articleListener;
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.app_bar_section_page_adapter, container, false);
        toolbar = (Toolbar) rootView.findViewById(R.id.toolbar);
        // Get a reference to the ViewPager
        mViewPager = (ViewPager) rootView.findViewById(R.id.section_fragment_container);

        // Get a reference to the TabLayout
        tabLayout = (TabLayout) rootView.findViewById(R.id.section_tabs);

        return rootView;

    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        toolbar.setTitle("UserInfoTabs");
        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mSectionsPagerAdapter = new SectionsPagerAdapter(getFragmentManager(),
                mFetcher, mSectionCardListener, mArticleListener);

        mViewPager.setAdapter(mSectionsPagerAdapter);

        tabLayout.setupWithViewPager(mViewPager);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.section_page_adapter, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }
}
