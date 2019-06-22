package com.example.sameershekhar.petsearch.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.sameershekhar.petsearch.R;
import com.example.sameershekhar.petsearch.interfaces.OnMovieClickListner;
import com.example.sameershekhar.petsearch.models.Movies;
import com.example.sameershekhar.petsearch.utils.Constant;
import com.example.sameershekhar.petsearch.views.activities.HomeScreen;
import com.example.sameershekhar.petsearch.views.fragments.AllMoviesListFragment;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HomeScreenAdapter extends RecyclerView.Adapter<HomeScreenAdapter.MyViewHolder> {

    private Context context;
    private Movies movies;
    private HomeScreen onMovieClickListner;


    public HomeScreenAdapter(Context context) {
        this.context = context;
        onMovieClickListner=(HomeScreen)context;

    }

    public void setData(Movies movies){
        this.movies=movies;
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
        holder.movieTitle.setText(movies.getResults().get(position).getTitle());
        holder.movieShortDescription.setText(movies.getResults().get(position).getOverview());
        holder.movieRating.setText(movies.getResults().get(position).getVoteAverage());
        holder.movieReleaseDate.setText(movies.getResults().get(position).getReleaseDate());
        holder.movieLanguage.setText(movies.getResults().get(position).getOriginalLanguage());
        Glide.with(context)
                .load(Constant.BASE_IMAGE_URL+movies.getResults().get(position).getPosterPath())
                .centerCrop()
                .placeholder(R.drawable.placeholder)
                .into(holder.moviePoster);

        holder.view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onMovieClickListner.onItemClick(position);
            }
        });



    }

    @Override
    public int getItemCount() {
        return movies!=null ? movies.getTotalResults() : 0;
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

        View view;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            view=itemView;
            ButterKnife.bind(this, itemView);
        }
    }
}
