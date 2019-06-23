package com.example.sameershekhar.petsearch.repositories;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.paging.DataSource;

import com.example.sameershekhar.petsearch.models.Result;
import com.example.sameershekhar.petsearch.network.RetrofitClient;

public class MovieDataSourceFactory extends DataSource.Factory<Integer, Result> {
    private MovieDataSource movieDataSource;
    private MutableLiveData<MovieDataSource> movieDataSourceMutableLiveData;

    public MovieDataSourceFactory() {
        movieDataSourceMutableLiveData = new MutableLiveData<>();
    }

    @NonNull
    @Override
    public DataSource<Integer, Result> create() {
        movieDataSource = new MovieDataSource();
        movieDataSourceMutableLiveData.postValue(movieDataSource);
        return movieDataSource;
    }

    public MutableLiveData<MovieDataSource> getMovieDataSourceMutableLiveData() {
        return movieDataSourceMutableLiveData;
    }
}
