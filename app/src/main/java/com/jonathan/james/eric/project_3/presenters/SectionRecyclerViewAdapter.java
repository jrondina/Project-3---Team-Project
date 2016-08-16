package com.jonathan.james.eric.project_3.presenters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jonathan.james.eric.project_3.Article;
import com.jonathan.james.eric.project_3.R;
import com.jonathan.james.eric.project_3.interfaces.ArticleListener;
import com.jonathan.james.eric.project_3.interfaces.SectionCardListener;

import java.util.ArrayList;

/**
 * Created by Jonathan Taylor on 8/15/16.
 */
public class SectionRecyclerViewAdapter extends RecyclerView.Adapter<SectionViewHolder> {

    private ArrayList<Article> mArticles;

    private ArticleListener mArticleListener;
    private SectionCardListener mCardListener;

    public SectionRecyclerViewAdapter(ArrayList<Article> mArticles, ArticleListener articleListener,
                                      SectionCardListener sectionCardListener) {
        this.mArticles = mArticles;
        mArticleListener = articleListener;
        mCardListener = sectionCardListener;

    }

    @Override
    public SectionViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.section_fragment_recycler_card, parent, false);
        return new SectionViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final SectionViewHolder holder, int position) {

        holder.setBookmark(Boolean.valueOf(mArticles.get(position).isBookmark()));

        //on click listener to spawn a detail fragment
        holder.setCardOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mCardListener.onCardClick(mArticles.get(holder.getAdapterPosition()));
            }
        });

        //set listeners for the
        holder.setBookmarkListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                holder.toggleBookmark();
                mArticleListener.onBookMarkClick(mArticles.get(holder.getAdapterPosition()));
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
//        holder.setByLineText(mArticles.get(position).get);
        holder.setHeadlineText(mArticles.get(position).getHeadline());
        holder.setLeadParagraphText(mArticles.get(position).getLeadParagraph());
        holder.setDateText(mArticles.get(position).getDate());

        holder.setThumbnail(mArticles.get(position).getLeadImage().getThumbnailImage());
    }

    @Override
    public int getItemCount() {
        return mArticles.size();
    }
}
