package com.example.sameershekhar.petsearch.views.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.app.ActionBar;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;


import com.bumptech.glide.Glide;
import com.example.sameershekhar.petsearch.R;
import com.example.sameershekhar.petsearch.models.Genre;
import com.example.sameershekhar.petsearch.models.SingleMovie;
import com.example.sameershekhar.petsearch.utils.Constant;
import com.example.sameershekhar.petsearch.viewmodels.MovieDetailScreenViewModel;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MovieDetailActivity extends AppCompatActivity {
    @BindView(R.id.image_poster)
    ImageView imagePoster;
    @BindView(R.id.movie_description)
    TextView movieDescription;
    @BindView(R.id.movie_length)
    TextView movieLength;
    @BindView(R.id.movie_release_date)
    TextView movieReleaseDate;
    @BindView(R.id.movie_rating)
    TextView movieRating;
    @BindView(R.id.movie_type)
    TextView movieType;
    @BindView(R.id.movie_language)
    TextView movieLanguage;
    @BindView(R.id.budget_value)
    TextView budgetValue;
    @BindView(R.id.revenue_value)
    TextView revenueValue;
    @BindView(R.id.my_toolbar)
    Toolbar myToolbar;

    private MovieDetailScreenViewModel movieDetailScreenViewModel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail);

        ButterKnife.bind(this);
        movieDetailScreenViewModel = ViewModelProviders.of(this).get(MovieDetailScreenViewModel.class);
        myToolbar.setNavigationIcon(getResources().getDrawable(R.drawable.ic_arrow_back_black_24dp));
        myToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //What to do on back clicked
                finish();
            }
        });


        if(getIntent()!=null && getIntent().hasExtra(Constant.MOVIE_ID)){
            Log.v("test10",getIntent().getIntExtra(Constant.MOVIE_ID,0)+"");
            movieDetailScreenViewModel.setMovieId(getIntent().getIntExtra(Constant.MOVIE_ID,0));
        }

        movieDetailScreenViewModel.getSingleMovieLiveData().observe(this, new Observer<SingleMovie>() {
            @Override
            public void onChanged(SingleMovie singleMovie) {
                if(singleMovie!=null){
                    myToolbar.setTitle(singleMovie.getTitle());
                    movieDescription.setText(singleMovie.getOverview());
                    movieLanguage.setText(singleMovie.getOriginalLanguage());
                    movieRating.setText(String.valueOf(singleMovie.getVoteAverage()));
                    budgetValue.setText("$ "+String.valueOf(singleMovie.getBudget()));
                    revenueValue.setText("$ "+String.valueOf(singleMovie.getRevenue()));
                    movieLength.setText(String.valueOf(singleMovie.getRuntime()) + " minutes");
                    movieType.setText(Constant.Utils.getFromatedMovieType(singleMovie.getGenres()));
                    movieReleaseDate.setText(Constant.Utils.getFromatedReleaseDate(singleMovie.getReleaseDate()));
                    Glide.with(MovieDetailActivity.this)
                            .load(Constant.BASE_IMAGE_URL+singleMovie.getPosterPath())
                            .centerCrop()
                            .placeholder(R.drawable.placeholder)
                            .into(imagePoster);
                }

            }
        });
    }


}
