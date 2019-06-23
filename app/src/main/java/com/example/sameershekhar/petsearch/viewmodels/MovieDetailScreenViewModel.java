package com.example.sameershekhar.petsearch.viewmodels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.sameershekhar.petsearch.interfaces.LoadSingleMoviesDetailFragListener;
import com.example.sameershekhar.petsearch.models.SingleMovie;
import com.example.sameershekhar.petsearch.repositories.MoviesDataRepo;

public class MovieDetailScreenViewModel extends AndroidViewModel {

    private int movieId;
    private MoviesDataRepo moviesDataRepo;
    private LiveData<SingleMovie> singleMovieLiveData=new MutableLiveData<>();
    public MovieDetailScreenViewModel(@NonNull Application application) {
        super(application);
        moviesDataRepo = new MoviesDataRepo();
    }

    public void setMovieId(int movieId){
        this.movieId=movieId;
        LoadSingleMovieData();
    }


    public LiveData<SingleMovie> getSingleMovieLiveData(){
//        if(singleMovieLiveData!=null){
//            LoadSingleMovieData();
//        }
        return singleMovieLiveData;
    }

    public void LoadSingleMovieData(){
        singleMovieLiveData=moviesDataRepo.getSingleMovieDataFromServer(movieId);
    }

}
