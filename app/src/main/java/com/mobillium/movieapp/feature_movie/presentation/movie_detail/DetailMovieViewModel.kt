package com.mobillium.movieapp.feature_movie.presentation.movie_detail

import androidx.lifecycle.ViewModel
import com.mobillium.movieapp.feature_movie.domain.entity.movie_details.MovieDetailEntity
import com.mobillium.movieapp.feature_movie.domain.use_case.GetMovieDetail
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class DetailMovieViewModel @Inject constructor(private val getMovieDetail: GetMovieDetail) : ViewModel() {

    private val _state = MutableStateFlow<MovieDetailFragmentUIState>(MovieDetailFragmentUIState.Init)
    val state: StateFlow<MovieDetailFragmentUIState> = _state

}

sealed class MovieDetailFragmentUIState {
    object Init : MovieDetailFragmentUIState()
    data class ShowToast(val message: String) : MovieDetailFragmentUIState()
    data class IsLoading(val isLoading: Boolean) : MovieDetailFragmentUIState()
    data class SuccessMovieDetailDetail(val movieDetailEntity: MovieDetailEntity) : MovieDetailFragmentUIState()
    data class Error(val message: String, val errorCode: Int) : MovieDetailFragmentUIState()
}