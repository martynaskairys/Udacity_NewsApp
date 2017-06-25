package com.martynaskairys.udacity_newsapp;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

public class MainActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<List<News>>,
        SwipeRefreshLayout.OnRefreshListener {

    private NewsAdapter adapter;
    private static int LOAD_ID = 0;
    SwipeRefreshLayout swipe;
    ProgressDialog progDialog;
    private static final String LOADING_MESSAGE = "Loading ... ";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        progDialog = new ProgressDialog(MainActivity.this);

        swipe = (SwipeRefreshLayout) findViewById(R.id.swipe);
        swipe.setOnRefreshListener(this);

        ConnectivityManager cm =
                (ConnectivityManager) getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        boolean isConnected = activeNetwork != null &&
                activeNetwork.isConnectedOrConnecting();

        if (isConnected) {
            ListView listView = (ListView) findViewById(R.id.list_view);
            adapter = new NewsAdapter(this);
            listView.setAdapter(adapter);
            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int i, long id) {
                    News news = adapter.getItem(i);
                    String url = news.url;
                    Intent intent = new Intent(Intent.ACTION_VIEW);
                    intent.setData(Uri.parse(url));
                    startActivity(intent);
                }
            });
            getSupportLoaderManager().initLoader(LOAD_ID, null, this);
        } else {
            Toast.makeText(MainActivity.this, getString(R.string.no_internet), Toast.LENGTH_SHORT).show();
        }
    }


    @Override
    public Loader<List<News>> onCreateLoader(int i, Bundle bundle) {

        progDialog.setMessage(LOADING_MESSAGE);
        progDialog.setIndeterminate(false);
        progDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progDialog.setCancelable(true);
        progDialog.show();

        return new LoaderNews(this);
    }

    @Override
    public void onLoadFinished(Loader<List<News>> loader, List<News> dataInfo) {
        swipe.setRefreshing(false);
        if (dataInfo != null) {
            adapter.setNotifyOnChange(false);
            adapter.clear();
            adapter.setNotifyOnChange(true);
            adapter.addAll(dataInfo);
        }
        progDialog.dismiss();

    }

    @Override
    public void onRefresh() {
        getSupportLoaderManager().restartLoader(LOAD_ID, null, this);
    }

    @Override
    public void onLoaderReset(Loader<List<News>> loader) {

    }


}
