package com.example.sameershekhar.petsearch.network;

import androidx.lifecycle.LiveData;

import com.example.sameershekhar.petsearch.models.Movies;

import retrofit2.http.GET;

public interface ServerEndPoints {

    @GET()
    LiveData<Movies> getMoviesDetailsFromServer();
}
