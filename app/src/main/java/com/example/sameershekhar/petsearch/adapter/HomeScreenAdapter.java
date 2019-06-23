package com.example.sameershekhar.petsearch.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.paging.PagedListAdapter;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.sameershekhar.petsearch.R;
import com.example.sameershekhar.petsearch.models.Result;
import com.example.sameershekhar.petsearch.utils.Constant;
import com.example.sameershekhar.petsearch.views.activities.HomeScreen;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HomeScreenAdapter extends PagedListAdapter<Result,HomeScreenAdapter.MyViewHolder> {

    private Context context;
    //private List<Result> resultList;
    private HomeScreen onMovieClickListner;

    private static DiffUtil.ItemCallback<Result> DIFF_CALLBACK = new DiffUtil.ItemCallback<Result>() {
        @Override
        public boolean areItemsTheSame(@NonNull Result oldItem, @NonNull Result newItem) {
            return oldItem.getId()==newItem.getId();
        }

        @Override
        public boolean areContentsTheSame(@NonNull Result oldItem, @NonNull Result newItem) {
            return true;
        }
    };


    public HomeScreenAdapter(Context context) {
        super(DIFF_CALLBACK);
        this.context = context;
        onMovieClickListner=(HomeScreen)context;

    }

//    public void setData(List<Result> resultList){
//        this.resultList=resultList;
//        notifyDataSetChanged();
//    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.home_recyclerview_item,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Result result=getItem(position);

        if(result!=null){
            holder.movieTitle.setText(result.getTitle());
            holder.movieShortDescription.setText(result.getOverview());
            holder.movieRating.setText(result.getVoteAverage()+"");
            holder.movieReleaseDate.setText(Constant.Utils.getFromatedReleaseDate(result.getReleaseDate()));
            holder.movieLanguage.setText(result.getOriginalLanguage());
            Glide.with(context)
                    .load(Constant.BASE_IMAGE_URL+result.getPosterPath())
                    .centerCrop()
                    .placeholder(R.drawable.placeholder)
                    .into(holder.moviePoster);

            holder.view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    onMovieClickListner.onItemClick(result.getId());
                }
            });
        }




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
