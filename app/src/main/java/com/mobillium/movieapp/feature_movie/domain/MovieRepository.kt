package com.mobillium.movieapp.feature_movie.domain

import com.mobillium.movieapp.feature_movie.domain.common.base.BaseResult
import com.mobillium.movieapp.feature_movie.domain.entity.movie.ResponseEntity
import com.mobillium.movieapp.feature_movie.domain.entity.movie_details.MovieDetails
import kotlinx.coroutines.flow.Flow

interface MovieRepository {
    fun getNowPlayingMovies(page: Int): Flow<BaseResult<ResponseEntity>>
    fun getUpcomingMovies(page: Int): Flow<BaseResult<ResponseEntity>>
    fun getMovieDetails(movieId: String): Flow<BaseResult<MovieDetails>>
}