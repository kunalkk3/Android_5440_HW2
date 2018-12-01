package com.example.rkjc.news_app_2;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

@Entity(tableName = "news_item")
public class NewsItem {

    @PrimaryKey(autoGenerate = true)
    @NonNull
    private int id;
    private String title;
    private String description;
    private String date;
    private String url;

    public NewsItem(int id,String title,String description,String date, String url)
    {
        this.id=id;
        this.title=title;
        this.description=description;
        this.date=date;
        this.url=url;
    }
    @Ignore
    public NewsItem(String title,String description,String date, String url)
    {
        this.title=title;
        this.description=description;
        this.date=date;
        this.url=url;
    }
    @NonNull
    public int getId() {
        return id;
    }

    public void setId(@NonNull int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String title) {
        this.description = description;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String title) {
        this.date = date;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }


}
