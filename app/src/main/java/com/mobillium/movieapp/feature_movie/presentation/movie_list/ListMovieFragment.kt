package com.mobillium.movieapp.feature_movie.presentation.movie_list

import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.mobillium.movieapp.R
import com.mobillium.movieapp.feature_movie.presentation.MovieActivityViewModel

class ListMovieFragment : Fragment(R.layout.fragment_movie_list) {

    private val viewModel by viewModels<MovieActivityViewModel>()
}