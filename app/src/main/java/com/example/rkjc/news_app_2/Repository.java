package com.example.rkjc.news_app_2;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;
import android.util.Log;

import java.io.IOException;
import java.util.List;

public class Repository {

    private NewsItemDao mNewsDao;
    private LiveData<List<NewsItem>> mAllNews;
    private NewsRoomDatabase mAppDatabase;

    public Repository(Application application)
    {
        NewsRoomDatabase mAppDatabase =NewsRoomDatabase.getDatabase(application);
        mNewsDao= mAppDatabase.NewsDao();
        mAllNews = mNewsDao.getAllNews();
    }

    public LiveData<List<NewsItem>> getAllNews() {
        return mAllNews;
    }

    public void insert() {
        new InsertNewsAsyncTask(mNewsDao).execute();
    }

    private static class InsertNewsAsyncTask extends AsyncTask<Void, Void, Void> {

        private NewsItemDao newsItemDao;

        private InsertNewsAsyncTask(NewsItemDao newsItemDao) {
            this.newsItemDao = newsItemDao;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            String result = null;
            try {
                result = NetworkUtils.getResponseFromHttpUrl(NetworkUtils.buildURL());
                List<NewsItem> newsList = JsonUtils.parseNews(result);
                newsItemDao.insert(newsList);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }
    }

    public void deleteAll() {
        new DeleteNewsAsyncTask(mNewsDao).execute();
    }


    private static class DeleteNewsAsyncTask extends AsyncTask<Void, Void, Void> {

        private NewsItemDao newsItemDao;

        private DeleteNewsAsyncTask(NewsItemDao newsItemDao) {
            this.newsItemDao = newsItemDao;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            newsItemDao.deleteAll();
            return null;
        }
    }
}
