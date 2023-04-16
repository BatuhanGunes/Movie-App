package com.mobillium.movieapp.feature_movie.data.movie_api.remote

import com.mobillium.movieapp.core.utils.EndPoints
import com.mobillium.movieapp.feature_movie.domain.entity.movie_details.MovieDetailEntity
import com.mobillium.movieapp.feature_movie.domain.entity.movie.ResponseEntity
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieService {

  @GET(EndPoints.NOW_PLAYING)
  suspend fun getNowPlayingMovies(@Query("page") page : Int): Response<ResponseEntity>

  @GET(EndPoints.UPCOMING)
  suspend fun getUpcomingMovies(@Query("page") page : Int): Response<ResponseEntity>

  @GET(EndPoints.MOVIE_DETAIL)
  suspend fun getMovieDetails(@Path("movieId") movieId: String): Response<MovieDetailEntity>

}
