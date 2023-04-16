package com.mobillium.movieapp.feature_movie.presentation.movie_detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mobillium.movieapp.feature_movie.domain.common.base.BaseResult
import com.mobillium.movieapp.feature_movie.domain.entity.movie_details.MovieDetailEntity
import com.mobillium.movieapp.feature_movie.domain.use_case.GetMovieDetail
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailMovieViewModel @Inject constructor(private val getMovieDetail: GetMovieDetail) : ViewModel() {

    private val _state = MutableStateFlow<MovieDetailFragmentUIState>(MovieDetailFragmentUIState.Init)
    val state: StateFlow<MovieDetailFragmentUIState> = _state

    private fun setLoading() {
        _state.value = MovieDetailFragmentUIState.IsLoading(true)
    }

    private fun hideLoading() {
        _state.value = MovieDetailFragmentUIState.IsLoading(false)
    }

    private fun showToast(message: String) {
        _state.value = MovieDetailFragmentUIState.ShowToast(message)
    }

    fun getMovieDetail(movieId: String) {
        viewModelScope.launch {
            getMovieDetail.invoke(movieId = movieId)
                .onStart {
                    setLoading()
                }
                .catch { exception ->
                    hideLoading()
                    showToast(exception.message.toString())
                }
                .collect { baseResult ->
                    hideLoading()
                    when (baseResult) {
                        is BaseResult.Error -> _state.value = MovieDetailFragmentUIState.Error(baseResult.message, baseResult.errorCode)
                        is BaseResult.Success -> _state.value = MovieDetailFragmentUIState.SuccessMovieDetailDetail(baseResult.data)
                    }
                }
        }
    }
}

sealed class MovieDetailFragmentUIState {
    object Init : MovieDetailFragmentUIState()
    data class ShowToast(val message: String) : MovieDetailFragmentUIState()
    data class IsLoading(val isLoading: Boolean) : MovieDetailFragmentUIState()
    data class SuccessMovieDetailDetail(val movieDetailEntity: MovieDetailEntity) : MovieDetailFragmentUIState()
    data class Error(val message: String, val errorCode: Int) : MovieDetailFragmentUIState()
}