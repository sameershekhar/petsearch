package com.example.sameershekhar.petsearch.viewmodels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.paging.LivePagedListBuilder;
import androidx.paging.PagedList;

import com.example.sameershekhar.petsearch.models.Result;
import com.example.sameershekhar.petsearch.network.RetrofitClient;
import com.example.sameershekhar.petsearch.repositories.MovieDataSource;
import com.example.sameershekhar.petsearch.repositories.MovieDataSourceFactory;
import com.example.sameershekhar.petsearch.repositories.MoviesDataRepo;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class HomeScreenViewModel extends AndroidViewModel {

//    private MoviesDataRepo moviesDataRepo;
//    private MutableLiveData<Movies> moviesLiveData=new MutableLiveData<>();
//    private int pageOffset=1;



    //paging library component
    private Executor executor;
    LiveData<MovieDataSource> movieDataSourceLiveData;
    LiveData<PagedList<Result>> pagedListLiveData;

    public HomeScreenViewModel(@NonNull Application application) {
        super(application);
       // moviesDataRepo = new MoviesDataRepo();
        MovieDataSourceFactory movieDataSourceFactory =new MovieDataSourceFactory();
        movieDataSourceLiveData=movieDataSourceFactory.getMovieDataSourceMutableLiveData();

        PagedList.Config config = new PagedList.Config.Builder()
                .setEnablePlaceholders(true)
                .setInitialLoadSizeHint(10)
                .setPageSize(20)
                 .setPrefetchDistance(10)
                .build();
        executor = Executors.newFixedThreadPool(5);

        pagedListLiveData = new LivePagedListBuilder<Integer, Result>(movieDataSourceFactory,config)
                .setFetchExecutor(executor)
                .build();


    }

    public LiveData<PagedList<Result>> getPagedListLiveData() {
        return pagedListLiveData;
    }

//    public void setPageOffSet(int pageOffset){
//        this.pageOffset=pageOffset;
//        LoadMoviesData(pageOffset);
//    }
//
//    public LiveData<Movies> getMoviesLiveData(){
//        if(moviesLiveData != null){
//            //LoadMoviesData(pageOffset);
//           // moviesDataRepo.getMoviesDataFromServer(pageOffset);
//        }
//        return moviesLiveData;
//    }
//
//    private void LoadMoviesData(int pageOffset) {
//        Log.v("Test2",pageOffset+"");
//        moviesLiveData=moviesDataRepo.getMoviesDataFromServer(pageOffset);
//
//    }
//
//    //
//
//

}
