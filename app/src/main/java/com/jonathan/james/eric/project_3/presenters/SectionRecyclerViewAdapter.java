package com.jonathan.james.eric.project_3.presenters;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jonathan.james.eric.project_3.Article;
import com.jonathan.james.eric.project_3.R;
import com.jonathan.james.eric.project_3.interfaces.ArticleListener;
import com.jonathan.james.eric.project_3.interfaces.BookmarkChangeListener;
import com.jonathan.james.eric.project_3.interfaces.SectionCardListener;
import com.jonathan.james.eric.project_3.models.BookmarkHashtable;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jonathan Taylor on 8/15/16.
 */
public class SectionRecyclerViewAdapter extends RecyclerView.Adapter<SectionViewHolder> {

    private static final String TAG = "RecyclerViewAdapter";

    private List<Article> mArticles;

    private ArticleListener mArticleListener;
    private SectionCardListener mCardListener;
    private BookmarkChangeListener mBookmarkChangeListener;

    private int mType;

    //TODO add hashtable for checking articles
    private BookmarkHashtable mBookmarkHashtable;


    public SectionRecyclerViewAdapter(List<Article> mArticles, ArticleListener articleListener,
                                      SectionCardListener sectionCardListener,
                                      BookmarkChangeListener bookmarkChangeListener, int type) {
        this.mArticles = mArticles;
        mArticleListener = articleListener;
        mCardListener = sectionCardListener;
        mBookmarkHashtable = BookmarkHashtable.getInstance();
        mBookmarkChangeListener = bookmarkChangeListener;
        mType = type;
    }

    @Override
    public SectionViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.section_fragment_recycler_card, parent, false);
        return new SectionViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final SectionViewHolder holder, final int position) {
        if(mBookmarkHashtable.isBookmarked(mArticles.get(position).getUrl()) && mType == 0){
            mArticles.get(position).setBookmark(true);
            Log.d(TAG, "onBindViewHolder: article is " + mArticles.get(position).isBookmark());
        }
        holder.setBookmark(Boolean.valueOf(mArticles.get(position).isBookmark()));

        //on click listener to spawn a detail fragment
        holder.setCardOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mCardListener.onCardClick(position);
            }
        });

        //set listeners for the
        holder.setBookmarkListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                holder.toggleBookmark();
                mArticleListener.onBookMarkClick(mArticles.get(holder.getAdapterPosition()), mBookmarkChangeListener);

            }
        });
        holder.setShareListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mArticleListener.onShareClick(mArticles.get(holder.getAdapterPosition()));
            }
        });

        //ToDo bind ByLine to the byline text
        holder.setSectionText(mArticles.get(position).getSection());
        holder.setByLineText(mArticles.get(position).getByline());
        holder.setHeadlineText(mArticles.get(position).getHeadline());
        holder.setLeadParagraphText(mArticles.get(position).getLeadParagraph());
        holder.setDateText(mArticles.get(position).getDate());

        if(mArticles.get(position).getLeadImage().getThumbnailImage() != null){
        holder.setThumbnail(mArticles.get(position).getLeadImage().getThumbnailImage());
        }else {
            holder.hideThumbnail();
        }
    }

    @Override
    public int getItemCount() {
        return mArticles.size();
    }

    public void changeArticleList(List<Article> articles){
        mArticles = articles;
        Log.d(TAG, "changeArticleList: Reloading the article list. Now length " + mArticles.size());
        notifyDataSetChanged();
    }
}
