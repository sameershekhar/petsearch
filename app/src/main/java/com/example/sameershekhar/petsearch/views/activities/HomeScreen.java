package com.example.sameershekhar.petsearch.views.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.example.sameershekhar.petsearch.R;
import com.example.sameershekhar.petsearch.adapter.HomeScreenAdapter;
import com.example.sameershekhar.petsearch.interfaces.LoadSingleMoviesDetailFragListener;
import com.example.sameershekhar.petsearch.interfaces.OnMovieClickListner;
import com.example.sameershekhar.petsearch.models.Movies;
import com.example.sameershekhar.petsearch.utils.Constant;
import com.example.sameershekhar.petsearch.viewmodels.HomeScreenViewModel;

import butterknife.BindView;

public class HomeScreen extends AppCompatActivity implements OnMovieClickListner {

    @BindView(R.id.home_recyclerview)
    RecyclerView homeRecyclerView;

    LoadSingleMoviesDetailFragListener loadSingleMoviesDetailFragListener;
    private HomeScreenViewModel homeScreenViewModel;
    private HomeScreenAdapter homeScreenAdapter;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_screen);
        //binding adapter to recyclerview
        homeScreenAdapter=new HomeScreenAdapter(this);
        homeRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        homeRecyclerView.setAdapter(homeScreenAdapter);

        //viewmodel object vreation
        homeScreenViewModel = ViewModelProviders.of(this).get(HomeScreenViewModel.class);
        homeScreenViewModel.setPageOffSet(1);
        homeScreenViewModel.getMoviesLiveData().observe(this, new Observer<Movies>() {
            @Override
            public void onChanged(Movies movies) {
                Log.v("Test1",movies.toString());
                homeScreenAdapter.setData(movies);
            }
        });
    }


    @Override
    public void onItemClick(int movie_id) {
        Intent intent=new Intent(this, MovieDetailActivity.class);
        intent.putExtra(Constant.MOVIE_ID,movie_id);
        startActivity(intent);
    }
}
