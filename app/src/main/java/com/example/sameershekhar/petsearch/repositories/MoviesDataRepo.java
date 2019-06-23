package com.example.sameershekhar.petsearch.repositories;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.sameershekhar.petsearch.models.Movies;
import com.example.sameershekhar.petsearch.models.SingleMovie;
import com.example.sameershekhar.petsearch.network.RetrofitClient;
import com.example.sameershekhar.petsearch.utils.Constant;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MoviesDataRepo {
   private RetrofitClient retrofitClient;
   private SingleMovie singleMovie;

    public MoviesDataRepo() {
        this.retrofitClient = RetrofitClient.getRetrofitClient();
    }

    public MutableLiveData<Movies> getMoviesDataFromServer(int pageOffSet){
        Log.v("Test3",pageOffSet+"");
         final MutableLiveData<Movies> moviesLiveData=new MutableLiveData<>();
         retrofitClient.getServerEndPoints().getMoviesDetailsFromServer(Constant.API_KEY,"en-US","popularity.desc",false,false,pageOffSet)
                 .enqueue(new Callback<Movies>() {
                     @Override
                     public void onResponse(Call<Movies> call, Response<Movies> response) {
                         Log.v("Test4",response.body()+"");
                          moviesLiveData.setValue(response.body());

                     }

                     @Override
                     public void onFailure(Call<Movies> call, Throwable t) {
                         Log.v("Test5",t.getMessage()+"");
                     }
                 });
         return moviesLiveData;
    }

    public LiveData<SingleMovie> getSingleMovieDataFromServer(int movieId){
        final MutableLiveData<SingleMovie> singleMovieLiveData=new MutableLiveData<>();
        retrofitClient.getServerEndPoints().getSingleMovieDetailsFromServer(movieId, Constant.API_KEY)
                .enqueue(new Callback<SingleMovie>() {
                    @Override
                    public void onResponse(Call<SingleMovie> call, Response<SingleMovie> response) {
                         singleMovieLiveData.setValue(response.body());
                        Log.v("Test6",response.body()+"");
                    }

                    @Override
                    public void onFailure(Call<SingleMovie> call, Throwable t) {
                        Log.v("Test7",t.getMessage()+"");
                    }
                });
        return singleMovieLiveData;
    }
}
