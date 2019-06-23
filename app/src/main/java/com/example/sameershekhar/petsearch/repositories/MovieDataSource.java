package com.example.sameershekhar.petsearch.repositories;


import android.util.Log;

import androidx.annotation.NonNull;
import androidx.paging.PageKeyedDataSource;

import com.example.sameershekhar.petsearch.models.Movies;
import com.example.sameershekhar.petsearch.models.Result;
import com.example.sameershekhar.petsearch.network.RetrofitClient;
import com.example.sameershekhar.petsearch.utils.Constant;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

//acting as data source for the pagedList
public class MovieDataSource extends PageKeyedDataSource<Integer , Result> {
    private RetrofitClient retrofitClient;

    public MovieDataSource() {
        this.retrofitClient = RetrofitClient.getRetrofitClient();
    }

    @Override
    public void loadInitial(@NonNull LoadInitialParams<Integer> params, @NonNull LoadInitialCallback<Integer, Result> callback) {

        retrofitClient.getServerEndPoints().getMoviesDetailsFromServer(Constant.API_KEY,"en-US","popularity.desc",false,false,1)
                .enqueue(new Callback<Movies>() {
                    @Override
                    public void onResponse(Call<Movies> call, Response<Movies> response) {
                        Log.v("Test4",response.body()+"");
                        Movies movies=response.body();
                        List<Result> resultArrayList=new ArrayList<>();

                        if(movies != null && movies.getResults()!=null){
                            resultArrayList = movies.getResults();
                        }
                        callback.onResult(resultArrayList,null,2);

                    }

                    @Override
                    public void onFailure(Call<Movies> call, Throwable t) {
                        Log.v("Test5",t.getMessage()+"");
                    }
                });
    }

    @Override
    public void loadBefore(@NonNull LoadParams<Integer> params, @NonNull LoadCallback<Integer, Result> callback) {

    }

    @Override
    public void loadAfter(@NonNull LoadParams<Integer> params, @NonNull LoadCallback<Integer, Result> callback) {

        retrofitClient.getServerEndPoints().getMoviesDetailsFromServer(Constant.API_KEY,"en-US","popularity.desc",false,false,params.key)
                .enqueue(new Callback<Movies>() {
                    @Override
                    public void onResponse(Call<Movies> call, Response<Movies> response) {
                        Log.v("AfterTest4",response.body()+"");

                        Movies movies=response.body();
                        List<Result> resultList=new ArrayList<>();
                        if(movies!=null && movies.getResults()!=null){
                            resultList=movies.getResults();
                            callback.onResult(resultList,params.key+1);
                        }

                    }

                    @Override
                    public void onFailure(Call<Movies> call, Throwable t) {
                        Log.v("AfterTest5",t.getMessage()+"");
                    }
                });


    }
}
