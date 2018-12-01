package com.example.rkjc.news_app_2;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;
import android.content.Context;

import java.util.List;

public class NewsViewModel extends AndroidViewModel {


    private Repository mRepository;

    private LiveData<List<NewsItem>> mAllNews;

    public NewsViewModel (Application application) {
        super(application);
        mRepository = new Repository(application);
        mAllNews = mRepository.getAllNews();
    }
    public LiveData<List<NewsItem>> getListNews() {
        return mAllNews;
    }

    public void insert() {
        mRepository.insert();
    }

    public void deleteAll(){
        mRepository.deleteAll();
    }
}
