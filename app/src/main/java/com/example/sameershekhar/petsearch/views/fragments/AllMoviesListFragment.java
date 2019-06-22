package com.example.sameershekhar.petsearch.views.fragments;


import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.sameershekhar.petsearch.R;
import com.example.sameershekhar.petsearch.adapter.HomeScreenAdapter;
import com.example.sameershekhar.petsearch.interfaces.LoadSingleMoviesDetailFragListener;
import com.example.sameershekhar.petsearch.interfaces.OnMovieClickListner;
import com.example.sameershekhar.petsearch.models.Movies;
import com.example.sameershekhar.petsearch.viewmodels.HomeScreenViewModel;
import com.example.sameershekhar.petsearch.views.activities.HomeScreen;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class AllMoviesListFragment extends Fragment implements OnMovieClickListner {


    @BindView(R.id.home_recyclerview)
    RecyclerView homeRecyclerView;

    LoadSingleMoviesDetailFragListener loadSingleMoviesDetailFragListener;
    private HomeScreenViewModel homeScreenViewModel;
    private HomeScreenAdapter homeScreenAdapter;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        loadSingleMoviesDetailFragListener = (HomeScreen)context;
    }

    public AllMoviesListFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_all_movies_list, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(getActivity());

        //binding adapter to recyclerview
        homeScreenAdapter=new HomeScreenAdapter(getActivity(),this);
        homeRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        homeRecyclerView.setAdapter(homeScreenAdapter);

        //viewmodel object vreation
        homeScreenViewModel = ViewModelProviders.of(getActivity()).get(HomeScreenViewModel.class);
        homeScreenViewModel.setPageOffSet(1);
        homeScreenViewModel.getMoviesLiveData().observe(this, new Observer<Movies>() {
            @Override
            public void onChanged(Movies movies) {
                Log.v("Test1",movies.toString());
                homeScreenAdapter.setData(movies);
            }
        });

    }

    @Override
    public void onItemClick(int position) {
        //homeScreenViewModel.setDataForClickedMovies(position);
        //loadSingleMoviesDetailFragListener.LoadSingleMovieFragment(position);
    }
}
