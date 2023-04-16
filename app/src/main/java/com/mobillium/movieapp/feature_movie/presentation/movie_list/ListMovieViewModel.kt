package com.mobillium.movieapp.feature_movie.presentation.movie_list

import androidx.lifecycle.ViewModel
import com.mobillium.movieapp.feature_movie.domain.use_case.GetNowPlayingMovies
import com.mobillium.movieapp.feature_movie.domain.use_case.GetUpcomingMovies
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ListMovieViewModel @Inject constructor(
    private val getNowPlayingMovies: GetNowPlayingMovies,
    private val getUpcomingMovies: GetUpcomingMovies
) : ViewModel() {

}