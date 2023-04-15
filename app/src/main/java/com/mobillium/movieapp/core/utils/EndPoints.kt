package com.mobillium.movieapp.core.utils

object EndPoints {

    private const val API_KEY = "0184dfcbaa464d6ba8e68b9a7a3ed436"

    const val BASE_URL = "https://api.themoviedb.org/"

    const val NOW_PLAYING = "3/movie/now_playing?api_key=$API_KEY"
    const val UPCOMING = "3/movie/upcoming?api_key=$API_KEY"
    const val MOVIE_DETAIL = "3/movie/{movieId}?api_key=$API_KEY"
}