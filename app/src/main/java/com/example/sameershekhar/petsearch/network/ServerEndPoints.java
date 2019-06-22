package com.example.sameershekhar.petsearch.network;

import androidx.lifecycle.LiveData;

import com.example.sameershekhar.petsearch.models.Movies;

import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ServerEndPoints {

    @GET("discover/movie?")
    LiveData<Movies> getMoviesDetailsFromServer(@Query("api_key") String apiKey,@Query("language") String lan,@Query("sort_by") String sortBy, @Query("include_adult") String includeAdult,@Query("include_video") String includeVideo,@Query("page") int page);
}
