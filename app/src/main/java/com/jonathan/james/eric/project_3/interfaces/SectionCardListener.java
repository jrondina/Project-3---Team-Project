package com.jonathan.james.eric.project_3.interfaces;

import com.jonathan.james.eric.project_3.Article;

import java.util.ArrayList;

/**
 * Created by Jonathan Taylor on 8/16/16.
 */
public interface SectionCardListener {
    void onCardClick(ArrayList<Article> list, int position);
}
