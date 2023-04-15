package com.mobillium.movieapp.feature_movie.presentation.movie_detail

import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.mobillium.movieapp.R
import com.mobillium.movieapp.feature_movie.presentation.MovieActivityViewModel

class DetailMovieFragment : Fragment(R.layout.fragment_movie_detail) {

    private val viewModel by viewModels<MovieActivityViewModel>()
}