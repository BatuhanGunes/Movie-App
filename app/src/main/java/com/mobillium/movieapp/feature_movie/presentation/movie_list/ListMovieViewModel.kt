package com.mobillium.movieapp.feature_movie.presentation.movie_list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mobillium.movieapp.feature_movie.domain.common.base.BaseResult
import com.mobillium.movieapp.feature_movie.domain.entity.movie.ResponseEntity
import com.mobillium.movieapp.feature_movie.domain.use_case.GetNowPlayingMovies
import com.mobillium.movieapp.feature_movie.domain.use_case.GetUpcomingMovies
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ListMovieViewModel @Inject constructor(
    private val getNowPlayingMovies: GetNowPlayingMovies,
    private val getUpcomingMovies: GetUpcomingMovies
) : ViewModel() {

    private val _state = MutableStateFlow<MovieListFragmentUIState>(MovieListFragmentUIState.Init)
    val state: StateFlow<MovieListFragmentUIState> = _state

    private fun setLoading() {
        _state.value = MovieListFragmentUIState.IsLoading(true)
    }

    private fun hideLoading() {
        _state.value = MovieListFragmentUIState.IsLoading(false)
    }

    private fun showToast(message: String) {
        _state.value = MovieListFragmentUIState.ShowToast(message)
    }


    fun getNowPlayingMovies(page: Int) {
        viewModelScope.launch {
            getNowPlayingMovies.invoke(page = page)
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
                        is BaseResult.Error -> _state.value = MovieListFragmentUIState.Error(baseResult.message, baseResult.errorCode)
                        is BaseResult.Success -> _state.value = MovieListFragmentUIState.SuccessNowPlayingMovieList(baseResult.data)
                    }
                }
        }
    }

    fun getUpComingMovies(page: Int) {
        viewModelScope.launch {
            getUpcomingMovies.invoke(page = page)
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
                        is BaseResult.Error -> _state.value = MovieListFragmentUIState.Error(baseResult.message, baseResult.errorCode)
                        is BaseResult.Success -> _state.value = MovieListFragmentUIState.SuccessUpcomingMovieList(baseResult.data)
                    }
                }
        }
    }
}

sealed class MovieListFragmentUIState {
    object Init : MovieListFragmentUIState()
    data class ShowToast(val message: String) : MovieListFragmentUIState()
    data class IsLoading(val isLoading: Boolean) : MovieListFragmentUIState()
    data class SuccessUpcomingMovieList(val responseEntity: ResponseEntity) : MovieListFragmentUIState()
    data class SuccessNowPlayingMovieList(val responseEntity: ResponseEntity) : MovieListFragmentUIState()
    data class Error(val message: String, val errorCode: Int) : MovieListFragmentUIState()
}