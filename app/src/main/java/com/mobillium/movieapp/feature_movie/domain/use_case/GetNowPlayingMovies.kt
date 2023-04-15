package com.mobillium.movieapp.feature_movie.domain.use_case

import com.mobillium.movieapp.feature_movie.domain.MovieRepository
import com.mobillium.movieapp.feature_movie.domain.common.base.BaseResult
import com.mobillium.movieapp.feature_movie.domain.entity.movie.ResponseEntity
import kotlinx.coroutines.flow.Flow

class GetNowPlayingMovies(private val repository: MovieRepository) {
    operator fun invoke(page: Int): Flow<BaseResult<ResponseEntity>> =
        repository.getNowPlayingMovies(page)
}