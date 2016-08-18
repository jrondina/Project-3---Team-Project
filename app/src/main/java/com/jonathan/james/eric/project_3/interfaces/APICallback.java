package com.jonathan.james.eric.project_3.interfaces;

import com.jonathan.james.eric.project_3.Article;

import java.util.List;

/**
 * Created by jamesrondina on 8/18/16.
 */
public interface APICallback {
    void responseFinished(List<Article> responseList);
}
