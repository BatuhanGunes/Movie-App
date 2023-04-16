package com.mobillium.movieapp.feature_movie.domain.entity.movie_details

import com.google.gson.annotations.SerializedName

data class MovieDetailEntity(
    @SerializedName("backdrop_path")
    val backdropPath: String?,
    @SerializedName("id")
    val id: Int,
    @SerializedName("imdb_id")
    val imdbId: String,
    @SerializedName("original_title")
    val originalTitle: String,
    @SerializedName("overview")
    val overview: String,
    @SerializedName("poster_path")
    val posterPath: String?,
    @SerializedName("release_date")
    val releaseDate: String,
    @SerializedName("title")
    val title: String,
    @SerializedName("vote_average")
    val voteAverage: Double
)