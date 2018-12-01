package com.example.rkjc.news_app_2;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;


public class NewsRecyclerViewAdapter  extends RecyclerView.Adapter<NewsRecyclerViewAdapter.NewsViewHolder> {

    Context mContext;
    private final LayoutInflater mInflater;
    private ArrayList<NewsItem> mNews;
    private NewsViewModel viewModel;

    public NewsRecyclerViewAdapter(Context context, NewsViewModel viewModel){
        this.mContext = context;
        this.viewModel = viewModel;
        mInflater = LayoutInflater.from(context);
    }

    @Override
    public NewsRecyclerViewAdapter.NewsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        boolean shouldAttachToParentImmediately = false;

        View view = inflater.inflate(R.layout.news_item, parent, shouldAttachToParentImmediately);
        NewsViewHolder viewHolder = new NewsViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(NewsRecyclerViewAdapter.NewsViewHolder holder, int position) {
        if (mNews != null) {
            holder.bind(position);
        } else {
            holder.title.setText("Hit Refresh");
        }
    }

    void setNews(ArrayList<NewsItem> news){
        mNews = news;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        if (mNews != null)
            return mNews.size();
        else return 0;
    }



    class NewsViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener
    {
        TextView title;
        TextView description;
        TextView date;

        public NewsViewHolder(View itemView) {
            super(itemView);
            title = (TextView) itemView.findViewById(R.id.title);
            description = (TextView) itemView.findViewById(R.id.description);
            date = (TextView) itemView.findViewById(R.id.date);
        }

        void bind(final int listIndex) {
            title.setText("Title :"+mNews.get(listIndex).getTitle());
            description.setText("Description :" + mNews.get(listIndex).getDescription());
            date.setText("Date :" + mNews.get(listIndex).getDate());
            itemView.setOnClickListener(this);
        }
        @Override
        public void onClick(View view) {
            String urlString = mNews.get(getAdapterPosition()).getUrl();
            Intent intent = new Intent(Intent.ACTION_VIEW);
            intent.setData(Uri.parse(urlString));
            mContext.startActivity(intent);
        }

    }

}
