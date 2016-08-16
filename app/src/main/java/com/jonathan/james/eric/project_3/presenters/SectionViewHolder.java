package com.jonathan.james.eric.project_3.presenters;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.jonathan.james.eric.project_3.R;

/**
 * Created by Jonathan Taylor on 8/15/16.
 */
public class SectionViewHolder extends RecyclerView.ViewHolder {

    private TextView mSection;
    private TextView mByLine;
    private TextView mHeadline;
    private TextView mDate;
    private TextView mLeadParagraph;

    private ImageButton mBookmark;
    private ImageButton mShare;

    private CardView mContainer;

    public SectionViewHolder(View itemView) {
        super(itemView);

        mContainer = (CardView) itemView.findViewById(R.id.section_card_container);

        mSection = (TextView) itemView.findViewById(R.id.section_card_section);
        mByLine = (TextView) itemView.findViewById(R.id.section_card_byline);
        mHeadline = (TextView) itemView.findViewById(R.id.section_card_headline);
        mDate = (TextView) itemView.findViewById(R.id.section_card_updated_date);
        mLeadParagraph = (TextView) itemView.findViewById(R.id.section_card_lead_paragraph);

        mBookmark = (ImageButton) itemView.findViewById(R.id.section_card_bookmark);
        mShare = (ImageButton) itemView.findViewById(R.id.section_card_share);
    }

    public void setCardOnClickListener(View.OnClickListener listener){
        mContainer.setOnClickListener(listener);
    }
    
    public void setBookmarkListener(View.OnClickListener listener){
        mBookmark.setOnClickListener(listener);
    }
    
    public void setShareListener(View.OnClickListener listener){
        mShare.setOnClickListener(listener);
    }

    //method to set the bookmark state of the article
    public void setBookmark(Boolean b){
        if(b){
            mBookmark.setImageResource(R.drawable.ic_bookmark_black_24dp);
        } else{
            mBookmark.setImageResource(R.drawable.ic_bookmark_border_black_24dp);
        }
        mBookmark.setTag(b);
    }

    //method to toggle the bookmark button and return whether the article is bookmarked anymore
    public boolean toggleBookmark(){
        if(mBookmark.getTag().equals(Boolean.TRUE)){
            mBookmark.setTag(Boolean.FALSE);
            mBookmark.setImageResource(R.drawable.ic_bookmark_border_black_24dp);
            return false;
        } else{
            mBookmark.setTag(Boolean.TRUE);
            mBookmark.setImageResource(R.drawable.ic_bookmark_black_24dp);
            return true;
        }
    }

    
    public void setSectionText(String s){
        mSection.setText(s);
    }

    public void setByLineText(String s){
        mByLine.setText(s);
    }

    public void setHeadlineText(String s){
        mHeadline.setText(s);
    }

    public void setDateText(String s){
        mDate.setText(s);
    }

    public void setLeadParagraphText(String s){
        mLeadParagraph.setText(s);
    }


}
