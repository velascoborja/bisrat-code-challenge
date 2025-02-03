package com.touch_surgery.utils



sealed class ResultWrapper<out T: Any> {
    data object Loading: ResultWrapper<Nothing>()
    data class Success<out T: Any>(val data: T): ResultWrapper<T>()
    data class Error(val message: String? = null, val exception : Exception? = null): ResultWrapper<Nothing>()
}