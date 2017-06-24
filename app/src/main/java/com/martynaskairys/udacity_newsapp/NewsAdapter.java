package com.martynaskairys.udacity_newsapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by martynaskairys on 24/06/2017.
 */

public class NewsAdapter extends ArrayAdapter<News> {


    public NewsAdapter(Context context) {
        super(context, -1, new ArrayList<News>());
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.list_item,parent, false);
        }

        TextView title = (TextView) convertView.findViewById(R.id.title);
        TextView author = (TextView) convertView.findViewById(R.id.author);
        TextView section = (TextView) convertView.findViewById(R.id.section);
        TextView date = (TextView) convertView.findViewById(R.id.date);

        News ongoingNews = getItem(position);
        title.setText(ongoingNews.getTitle());
        author.setText(ongoingNews.getAuthor());
        section.setText(ongoingNews.getSection());
        date.setText(ongoingNews.getDate());

        return convertView;

    }

}
