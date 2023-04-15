package com.mobillium.movieapp.feature_movie.presentation

import androidx.lifecycle.ViewModel
import com.mobillium.movieapp.feature_movie.domain.use_case.MovieUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MovieActivityViewModel @Inject constructor(private val movieUseCases: MovieUseCases) :
    ViewModel() {

}