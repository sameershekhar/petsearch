package com.example.sameershekhar.petsearch.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sameershekhar.petsearch.R;
import com.example.sameershekhar.petsearch.models.Movies;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HomeScreenAdapter extends RecyclerView.Adapter<HomeScreenAdapter.MyViewHolder> {

    private Context context;
    private ArrayList<Movies> moviesArrayList;

    public HomeScreenAdapter(Context context) {
        this.context = context;
    }

    public void setData(ArrayList<Movies> moviesArrayList){
        this.moviesArrayList=moviesArrayList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.home_recyclerview_item,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return moviesArrayList!=null ? moviesArrayList.size() : 0;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.movie_poster)
        ImageView moviePoster;
        @BindView(R.id.movie_title)
        TextView movieTitle;
        @BindView(R.id.movie_short_description)
        TextView movieShortDescription;
        @BindView(R.id.movie_rating)
        TextView movieRating;
        @BindView(R.id.movie_release_date)
        TextView movieReleaseDate;
        @BindView(R.id.movie_language)
        TextView movieLanguage;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
