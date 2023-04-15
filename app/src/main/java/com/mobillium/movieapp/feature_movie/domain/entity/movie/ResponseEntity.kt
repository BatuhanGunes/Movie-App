package com.mobillium.movieapp.feature_movie.domain.entity.movie

import com.google.gson.annotations.SerializedName

data class ResponseEntity(
    @SerializedName("dates")
    val dates: DatesEntity,
    @SerializedName("page")
    val page: Int,
    @SerializedName("results")
    val resultList: List<MovieEntity>,
    @SerializedName("total_pages")
    val totalPages: Int,
    @SerializedName("total_results")
    val totalResults: Int
)