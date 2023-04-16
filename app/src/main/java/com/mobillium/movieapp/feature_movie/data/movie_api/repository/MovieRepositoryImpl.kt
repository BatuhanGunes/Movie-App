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
                    adult = body.adult,
                    backdropPath = body.backdropPath,
                    belongsToCollection = body.belongsToCollection,
                    budget = body.budget,
                    genres = body.genres,
                    homepage = body.homepage,
                    id = body.id,
                    imdbId = body.imdbId,
                    originalLanguage = body.originalLanguage,
                    originalTitle = body.originalTitle,
                    overview = body.overview,
                    popularity = body.popularity,
                    posterPath = body.posterPath,
                    productionCompanies = body.productionCompanies,
                    productionCountries = body.productionCountries,
                    releaseDate = body.releaseDate,
                    revenue = body.revenue,
                    runtime = body.runtime,
                    spokenLanguages = body.spokenLanguages,
                    status = body.status,
                    tagline = body.tagline,
                    title = body.title,
                    video = body.video,
                    voteAverage = body.voteAverage,
                    voteCount = body.voteCount,
                )

                emit(BaseResult.Success(responseEntity))
            } else {
                emit(BaseResult.Error(response.message(), response.code()))
            }
        }.flowOn(Dispatchers.IO)
    }
}