package com.mobillium.movieapp.feature_movie.domain.use_case

import com.mobillium.movieapp.feature_movie.domain.MovieRepository
import com.mobillium.movieapp.feature_movie.domain.common.base.BaseResult
import com.mobillium.movieapp.feature_movie.domain.entity.movie_details.MovieDetailEntity
import kotlinx.coroutines.flow.Flow

class GetMovieDetail(private val repository: MovieRepository) {
    operator fun invoke(movieId: String): Flow<BaseResult<MovieDetailEntity>> =
        repository.getMovieDetails(movieId)
}