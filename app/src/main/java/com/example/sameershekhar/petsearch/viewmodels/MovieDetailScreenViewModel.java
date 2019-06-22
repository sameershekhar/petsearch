package com.example.sameershekhar.petsearch.viewmodels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.sameershekhar.petsearch.models.SingleMovie;
import com.example.sameershekhar.petsearch.repositories.MoviesDataRepo;

public class MovieDetailScreenViewModel extends AndroidViewModel {

    private int movieId;
    private MoviesDataRepo moviesDataRepo;
    private MutableLiveData<SingleMovie> singleMovieLiveData;
    public MovieDetailScreenViewModel(@NonNull Application application) {
        super(application);
    }



    public LiveData<SingleMovie> getSingleMovieLiveData(){
        if(singleMovieLiveData == null){
            singleMovieLiveData=new MutableLiveData<>();
        }
        return singleMovieLiveData;
    }

    public void LoadSingleMovieData(int movieId){
        singleMovieLiveData.setValue(moviesDataRepo.getSingleMovieDataFromServer(movieId));
    }

}
