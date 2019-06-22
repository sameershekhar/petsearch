package com.example.sameershekhar.petsearch.views.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toolbar;

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
    @BindView(R.id.toolbar)
    Toolbar toolbar;
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

    private MovieDetailScreenViewModel movieDetailScreenViewModel;
    private String[] monthsOfYear= {"Jan" ," Feb" ,"March", "April" ,"May" ,"June","July" ,"August","Sep" ,"Oct" ,"Nov","Dec"};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail);
        ButterKnife.bind(this);
        movieDetailScreenViewModel = ViewModelProviders.of(this).get(MovieDetailScreenViewModel.class);

        if(getIntent()!=null && getIntent().hasExtra(Constant.MOVIE_ID)){
            movieDetailScreenViewModel.LoadSingleMovieData(getIntent().getIntExtra(Constant.MOVIE_ID,0));
        }

        movieDetailScreenViewModel.getSingleMovieLiveData().observe(this, new Observer<SingleMovie>() {
            @Override
            public void onChanged(SingleMovie singleMovie) {
                movieDescription.setText(singleMovie.getOverview());
                movieLanguage.setText(singleMovie.getOriginalLanguage());
                budgetValue.setText(singleMovie.getBudget());
                revenueValue.setText(singleMovie.getRevenue());
                movieLength.setText(singleMovie.getRuntime() + " minutes");
                movieType.setText(getFromatedMovieType(singleMovie.getGenres()));
                movieReleaseDate.setText(getFromatedReleaseDate(singleMovie.getReleaseDate()));
                Glide.with(MovieDetailActivity.this)
                        .load(Constant.BASE_IMAGE_URL+singleMovie.getPosterPath())
                        .centerCrop()
                        .placeholder(R.drawable.placeholder)
                        .into(imagePoster);
            }
        });






    }

    private String getFromatedMovieType(List<Genre> genres) {

        String movieType="";
        if(genres!=null){
            for(int i=0;i<genres.size();i++){
                movieType += genres.get(i).getName()+" ";
            }
        }
        return movieType;

    }

    private String getFromatedReleaseDate(String releaseData){
        String[] date=releaseData.split("-");
        int index=0;
        int day=1;
        if(releaseData!=null){
            if(date!=null && date.length ==3){
                if(Integer.parseInt(date[1]) < 10){
                    index=Integer.parseInt(date[1])%10;
                }else {
                    index=Integer.parseInt(date[1]);
                }

                if(Integer.parseInt(date[2]) < 10){
                    day=Integer.parseInt(date[2])%10;
                }else {
                    day=Integer.parseInt(date[2]);
                }
            }
        }

        return day+" "+monthsOfYear[index-1]+ " "+date[0];
    }

}
