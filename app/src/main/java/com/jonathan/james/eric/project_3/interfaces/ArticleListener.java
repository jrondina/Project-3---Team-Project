package com.jonathan.james.eric.project_3.interfaces;

import com.jonathan.james.eric.project_3.Article;

/**
 * Created by Jonathan Taylor on 8/16/16.
 */
public interface ArticleListener {
    void onBookMarkClick(Article a);
    void onShareClick(Article a);
}
