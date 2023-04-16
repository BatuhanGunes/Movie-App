package com.mobillium.movieapp.feature_movie.presentation.movie_list

import androidx.lifecycle.ViewModel
import com.mobillium.movieapp.feature_movie.domain.entity.movie.ResponseEntity
import com.mobillium.movieapp.feature_movie.domain.use_case.GetNowPlayingMovies
import com.mobillium.movieapp.feature_movie.domain.use_case.GetUpcomingMovies
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class ListMovieViewModel @Inject constructor(
    private val getNowPlayingMovies: GetNowPlayingMovies,
    private val getUpcomingMovies: GetUpcomingMovies
) : ViewModel() {

    private val _state = MutableStateFlow<MovieListFragmentUIState>(MovieListFragmentUIState.Init)
    val state: StateFlow<MovieListFragmentUIState> = _state

}

sealed class MovieListFragmentUIState {
    object Init : MovieListFragmentUIState()
    data class ShowToast(val message: String) : MovieListFragmentUIState()
    data class IsLoading(val isLoading: Boolean) : MovieListFragmentUIState()
    data class SuccessUpcomingMovieList(val responseEntity: ResponseEntity) : MovieListFragmentUIState()
    data class SuccessNowPlayingMovieList(val responseEntity: ResponseEntity) : MovieListFragmentUIState()
    data class Error(val message: String, val errorCode: Int) : MovieListFragmentUIState()
}