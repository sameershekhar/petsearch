package com.example.sameershekhar.petsearch.network;


import com.example.sameershekhar.petsearch.models.Movies;
import com.example.sameershekhar.petsearch.models.SingleMovie;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ServerEndPoints {

    @GET("discover/movie?")
    Call<Movies> getMoviesDetailsFromServer(@Query("api_key") String apiKey, @Query("language") String lan, @Query("sort_by") String sortBy, @Query("include_adult") boolean includeAdult, @Query("include_video") boolean includeVideo, @Query("page") int page);

    @GET("movie/{movie_id}?")
    Call<SingleMovie> getSingleMovieDetailsFromServer(@Path("movie_id") int movieId,@Query("api_key") String apiKey);

}
