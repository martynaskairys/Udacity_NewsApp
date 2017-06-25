package com.martynaskairys.udacity_newsapp;

import android.content.Context;
import android.support.v4.content.AsyncTaskLoader;
import android.util.Log;

import java.io.IOException;
import java.net.URL;
import java.util.List;

/**
 * Created by martynaskairys on 24/06/2017.
 */

public class LoaderNews extends AsyncTaskLoader<List<News>> {

    public LoaderNews(Context context) {
        super(context);
    }


    @Override
    protected void onStartLoading() {
        super.onStartLoading();
        forceLoad();
    }

    @Override
    public List<News> loadInBackground() {

        List<News> listOfNews = null;
        try {
            URL url = QueryUtils.createUrl();
            String jsonResponse = QueryUtils.makeHttpRequest(url);
            listOfNews = QueryUtils.parseJson(jsonResponse);
        } catch (IOException e) {
            Log.e("Query", "Error loadInBackground", e);
        }
        return listOfNews;
    }
}
