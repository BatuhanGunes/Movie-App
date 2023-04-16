package com.mobillium.movieapp.feature_movie.data.movie_api.repository

import androidx.annotation.WorkerThread
import com.mobillium.movieapp.feature_movie.data.movie_api.remote.MovieService
import com.mobillium.movieapp.feature_movie.domain.MovieRepository
import com.mobillium.movieapp.feature_movie.domain.common.base.BaseResult
import com.mobillium.movieapp.feature_movie.domain.entity.movie.ResponseEntity
import com.mobillium.movieapp.feature_movie.domain.entity.movie_details.MovieDetailEntity
import dagger.hilt.android.scopes.ActivityScoped
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

@ActivityScoped
class MovieRepositoryImpl(private val movieService: MovieService) : MovieRepository {

    @WorkerThread
    override fun getNowPlayingMovies(page: Int): Flow<BaseResult<ResponseEntity>> {
        return flow {
            val response = movieService.getNowPlayingMovies(page = page);
            if (response.isSuccessful) {
                val body = response.body()!!
                val responseEntity = ResponseEntity(
                    dates = body.dates,
                    page = body.page,
                    resultList = body.resultList,
                    totalPages = body.totalPages,
                    totalResults = body.totalResults
                )

                emit(BaseResult.Success(responseEntity))
            } else {
                emit(BaseResult.Error(response.message(), response.code()))
            }
        }.flowOn(Dispatchers.IO)
    }

    @WorkerThread
    override fun getUpcomingMovies(page: Int): Flow<BaseResult<ResponseEntity>> {
        return flow {
            val response = movieService.getUpcomingMovies(page = page);
            if (response.isSuccessful) {
                val body = response.body()!!
                val responseEntity = ResponseEntity(
                    dates = body.dates,
                    page = body.page,
                    resultList = body.resultList,
                    totalPages = body.totalPages,
                    totalResults = body.totalResults
                )

                emit(BaseResult.Success(responseEntity))
            } else {
                emit(BaseResult.Error(response.message(), response.code()))
            }
        }.flowOn(Dispatchers.IO)
    }

    @WorkerThread
    override fun getMovieDetails(movieId: String): Flow<BaseResult<MovieDetailEntity>> {
        return flow {
            val response = movieService.getMovieDetails(movieId = movieId);
            if (response.isSuccessful) {
                val body = response.body()!!
                val responseEntity = MovieDetailEntity(
                    backdropPath = body.backdropPath,
                    id = body.id,
                    imdbId = body.imdbId,
                    originalTitle = body.originalTitle,
                    overview = body.overview,
                    posterPath = body.posterPath,
                    releaseDate = body.releaseDate,
                    title = body.title,
                    voteAverage = body.voteAverage
                )

                emit(BaseResult.Success(responseEntity))
            } else {
                emit(BaseResult.Error(response.message(), response.code()))
            }
        }.flowOn(Dispatchers.IO)
    }
}