package com.mobillium.movieapp.core.di

import com.mobillium.movieapp.feature_movie.data.movie_api.remote.MovieService
import com.mobillium.movieapp.feature_movie.data.movie_api.repository.MovieRepositoryImpl
import com.mobillium.movieapp.feature_movie.domain.MovieRepository
import com.mobillium.movieapp.feature_movie.domain.use_case.GetMovieDetail
import com.mobillium.movieapp.feature_movie.domain.use_case.GetNowPlayingMovies
import com.mobillium.movieapp.feature_movie.domain.use_case.GetUpcomingMovies
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideMovieRepository(movieService: MovieService): MovieRepository {
        return MovieRepositoryImpl(movieService)
    }

    @Provides
    @Singleton
    fun provideGetMovieDetailUseCases(repository: MovieRepository) = GetMovieDetail(repository)

    @Provides
    @Singleton
    fun provideGetNowPlayingMoviesUseCases(repository: MovieRepository) = GetNowPlayingMovies(repository)

    @Provides
    @Singleton
    fun provideGetUpcomingMoviesUseCases(repository: MovieRepository) = GetUpcomingMovies(repository)

}