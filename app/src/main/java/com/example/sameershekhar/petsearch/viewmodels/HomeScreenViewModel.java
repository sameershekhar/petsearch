package com.example.sameershekhar.petsearch.viewmodels;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.sameershekhar.petsearch.models.Movies;
import com.example.sameershekhar.petsearch.models.Result;
import com.example.sameershekhar.petsearch.repositories.MoviesDataRepo;

public class HomeScreenViewModel extends AndroidViewModel {

    private MoviesDataRepo moviesDataRepo;
    private LiveData<Movies> moviesLiveData=new MutableLiveData<>();
    private int pageOffset=1;

    public HomeScreenViewModel(@NonNull Application application) {
        super(application);
        moviesDataRepo = new MoviesDataRepo();
    }

    public void setPageOffSet(int pageOffset){
        this.pageOffset=pageOffset;
    }

    public LiveData<Movies> getMoviesLiveData(){
        if(moviesLiveData != null){
            LoadMoviesData(pageOffset);
        }
        return moviesLiveData;
    }

    private void LoadMoviesData(int pageOffset) {
        Log.v("Test2",pageOffset+"");
        moviesLiveData = moviesDataRepo.getMoviesDataFromServer(pageOffset);
    }



}
