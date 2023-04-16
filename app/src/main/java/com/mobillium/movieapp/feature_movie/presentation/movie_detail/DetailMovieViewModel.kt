package com.mobillium.movieapp.feature_movie.presentation.movie_detail

import androidx.lifecycle.ViewModel
import com.mobillium.movieapp.feature_movie.domain.use_case.GetMovieDetail
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DetailMovieViewModel @Inject constructor(private val getMovieDetail: GetMovieDetail) : ViewModel() {

}