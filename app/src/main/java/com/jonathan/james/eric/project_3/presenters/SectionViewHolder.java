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


}
