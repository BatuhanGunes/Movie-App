package com.mobillium.movieapp.feature_movie.domain.use_case

class MovieUseCases(
    val getMovieDetail: GetMovieDetail,
    val getUpcomingMovies: GetUpcomingMovies,
    val getNowPlayingMovies: GetNowPlayingMovies,
)