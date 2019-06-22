package com.example.sameershekhar.petsearch.views;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toolbar;

import com.example.sameershekhar.petsearch.R;

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


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail);
        ButterKnife.bind(this);
    }
}
