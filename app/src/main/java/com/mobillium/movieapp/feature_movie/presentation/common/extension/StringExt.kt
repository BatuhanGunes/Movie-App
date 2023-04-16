package com.mobillium.movieapp.feature_movie.presentation.common.extension

import com.mobillium.movieapp.core.utils.EndPoints
import java.text.SimpleDateFormat
import java.util.Locale

private const val DATE_FORMAT_REMOTE = "yyyy-MM-dd"
private const val DATE_FORMAT_LOCAL = "dd.MM.yyyy"

fun String.getBasePosterPath(): String {
    return EndPoints.BASE_POSTER_PATH.plus(this)
}

fun String.getBaseBackdropPath(): String {
    return EndPoints.BASE_BACKDROP_PATH.plus(this)
}

fun String.reformatDate(): String {
    val remoteDateFormat = SimpleDateFormat(DATE_FORMAT_REMOTE, Locale.getDefault())
    val localDateFormat = SimpleDateFormat(DATE_FORMAT_LOCAL, Locale.getDefault())

    val remoteDate = remoteDateFormat.parse(this)
    return if (remoteDate != null)
        localDateFormat.format(remoteDate)
    else this
}