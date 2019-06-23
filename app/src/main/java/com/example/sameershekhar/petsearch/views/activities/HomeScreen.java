package com.example.sameershekhar.petsearch.views.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.ProgressBar;

import com.example.sameershekhar.petsearch.R;
import com.example.sameershekhar.petsearch.adapter.HomeScreenAdapter;
import com.example.sameershekhar.petsearch.interfaces.LoadSingleMoviesDetailFragListener;
import com.example.sameershekhar.petsearch.interfaces.OnMovieClickListner;
import com.example.sameershekhar.petsearch.models.Movies;
import com.example.sameershekhar.petsearch.models.Result;
import com.example.sameershekhar.petsearch.utils.Constant;
import com.example.sameershekhar.petsearch.viewmodels.HomeScreenViewModel;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class HomeScreen extends AppCompatActivity implements OnMovieClickListner {

   // @BindView(R.id.home_recyclerview)
    RecyclerView homeRecyclerView;
    private HomeScreenViewModel homeScreenViewModel;
    private HomeScreenAdapter homeScreenAdapter;
    boolean isLoading = false;
    private int currentPageNumber=1;
    private int pageThresold=5;
    private int totalNumberOfMovies=20;
    private ArrayList<Result> moviesArraylist=new ArrayList<>();
    private Toolbar toolbar;
    private ProgressBar progress_circular;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_screen);

        //binding adapter to recyclerview
        progress_circular=findViewById(R.id.progress_circular);
        toolbar=findViewById(R.id.home_screen_toolbar);
        setSupportActionBar(toolbar);
        homeRecyclerView=findViewById(R.id.home_recyclerview);
        homeScreenAdapter=new HomeScreenAdapter(this);
        homeRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        homeRecyclerView.setAdapter(homeScreenAdapter);

        //viewmodel object vreation
        homeScreenViewModel = ViewModelProviders.of(this).get(HomeScreenViewModel.class);
        homeScreenViewModel.setPageOffSet(currentPageNumber);
        homeScreenViewModel.getMoviesLiveData().observe(this, new Observer<Movies>() {
            @Override
            public void onChanged(Movies movies) {
//                Log.v("Test1",movies.toString());
                    progress_circular.setVisibility(View.GONE);
                    totalNumberOfMovies=movies.getResults().size();
                    moviesArraylist.addAll(movies.getResults());
                    homeScreenAdapter.setData(moviesArraylist);


            }
        });

       // initScrollListener();

    }


    @Override
    public void onItemClick(int movie_id) {
        Intent intent=new Intent(this, MovieDetailActivity.class);
        intent.putExtra(Constant.MOVIE_ID,movie_id);
        startActivity(intent);
    }

    private void initScrollListener() {
        homeRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                LinearLayoutManager linearLayoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();
                if (!isLoading) {
                    if (linearLayoutManager != null && linearLayoutManager.findLastCompletelyVisibleItemPosition()+pageThresold >totalNumberOfMovies) {
                        //bottom of list!

                        currentPageNumber++;
                       // loadMore();
                        isLoading = true;
                    }
                }
            }
        });
    }

    public void loadMore(){
        homeScreenViewModel.setPageOffSet(currentPageNumber);
        isLoading=false;
//        if(currentPageNumber==2){
//            Log.v("Test11","loadmore");
//
//        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.item, menu);
        return true;
    }
}
