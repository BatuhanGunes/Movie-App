package com.mobillium.movieapp.feature_movie.domain.common.base

sealed class BaseResult <out R> {
    data class Success<out T>(val data: T) : BaseResult<T>()
    data class Error(val message: String, val errorCode: Int) : BaseResult<Nothing>()
}