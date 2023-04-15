package com.mobillium.movieapp.feature_movie.domain.entity.movie

import com.google.gson.annotations.SerializedName

data class DatesEntity(
    @SerializedName("maximum")
    val maximum: String,
    @SerializedName("minimum")
    val minimum: String
)