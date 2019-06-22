package com.example.sameershekhar.petsearch.views.fragments;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.sameershekhar.petsearch.R;
import com.example.sameershekhar.petsearch.models.Result;
import com.example.sameershekhar.petsearch.utils.Constant;
import com.example.sameershekhar.petsearch.viewmodels.HomeScreenViewModel;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class SingleMovieDetailFragment extends Fragment {
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.image_poster)
    ImageView imagePoster;
    @BindView(R.id.movie_description)
    TextView movieDescription;
    @BindView(R.id.movie_length)
    TextView movieLength;
    @BindView(R.id.movie_release_date)
    TextView movieReleaseDate;
    @BindView(R.id.movie_rating)
    TextView movieRating;
    @BindView(R.id.movie_type)
    TextView movieType;
    @BindView(R.id.movie_language)
    TextView movieLanguage;
    @BindView(R.id.budget_value)
    TextView budgetValue;
    @BindView(R.id.revenue_value)
    TextView revenueValue;

    private HomeScreenViewModel homeScreenViewModel;

    public SingleMovieDetailFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_single_movie_detail, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(getActivity());

        homeScreenViewModel = ViewModelProviders.of(getActivity()).get(HomeScreenViewModel.class);
//        homeScreenViewModel.getDataForClickedMovies().observe(getActivity(), new Observer<Result>() {
//            @Override
//            public void onChanged(Result result) {
//                movieDescription.setText(result.getOverview());
//                //movieReleaseDate.setText(getFormatedReleaseDate(result.getReleaseDate()));
//                movieLanguage.setText(result.getOriginalLanguage());
//                movieRating.setText(result.getVoteAverage());
//
//                Glide.with(getActivity())
//                        .load(Constant.BASE_IMAGE_URL+result.getPosterPath())
//                        .centerCrop()
//                        .placeholder(R.drawable.placeholder)
//                        .into(imagePoster);
//
//            }
//        });

    }
}
