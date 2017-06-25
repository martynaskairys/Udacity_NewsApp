package com.martynaskairys.udacity_newsapp;

/**
 * Created by martynaskairys on 24/06/2017.
 */

public class News {

    String url;
    String title;
    String author;
    String date;
    String section;
//    String thumbPic;


    public News(String url, String title, String author, String section, String date) {
        this.url = url;
        this.title = title;
        this.author = author;
        this.section = section;
        this.date = date;
//        this.thumbPic = thumbnail;

    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getSection() {
        return section;
    }

    public void setSection(String section) {
        this.section = section;
    }

//    public String getThumbPic() {return thumbPic;}
//
//    public void setThumbPic(String thumbPic) {this.thumbPic = thumbPic;}


    public String toString() {

        return "News{" + "title=" + title + '\'' +
                ", author='" + author + '\'' +
                ", url='" + url + '\'' +
                ", date='" + date + '\'' +
                ", section='" + section + '\'' +
//                ", thumbnail='" + thumbPic + '\'' +
                '}';
    }
}
