package com.mobillium.movieapp.feature_movie.presentation.movie_list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mobillium.movieapp.feature_movie.domain.common.base.BaseResult
import com.mobillium.movieapp.feature_movie.domain.entity.movie.MovieEntity
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

    var currentPageNumber = 1
    var isAllMoviesLoaded = false

    private fun setLoading() {
        _state.value = MovieListFragmentUIState.IsLoading(true)
    }

    private fun hideLoading() {
        _state.value = MovieListFragmentUIState.IsLoading(false)
    }

    private fun showToast(message: String) {
        _state.value = MovieListFragmentUIState.ShowToast(message)
    }

    fun fetchNowPlayingMovies() {
        viewModelScope.launch {
            val defaultUpcomingPageNumber = 1
            getNowPlayingMovies.invoke(page = defaultUpcomingPageNumber)
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
                        is BaseResult.Success -> _state.value = MovieListFragmentUIState.SuccessNowPlayingMovieList(baseResult.data.resultList)
                    }
                }
        }
    }

    fun fetchUpComingMovies() {
        viewModelScope.launch {
            getUpcomingMovies.invoke(page = currentPageNumber)
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
                        is BaseResult.Success -> {
                            _state.value = MovieListFragmentUIState.SuccessUpcomingMovieList(baseResult.data.resultList)
                            isAllMoviesLoaded = currentPageNumber >= baseResult.data.totalPages
                            currentPageNumber++
                        }
                    }
                }
        }
    }
}

sealed class MovieListFragmentUIState {
    object Init : MovieListFragmentUIState()
    data class ShowToast(val message: String) : MovieListFragmentUIState()
    data class IsLoading(val isLoading: Boolean) : MovieListFragmentUIState()
    data class SuccessUpcomingMovieList(val movieEntityList: List<MovieEntity>) : MovieListFragmentUIState()
    data class SuccessNowPlayingMovieList(val movieEntityList: List<MovieEntity>) : MovieListFragmentUIState()
    data class Error(val message: String, val errorCode: Int) : MovieListFragmentUIState()
}