package com.touch_surgery.utils

import java.net.UnknownHostException
import java.text.SimpleDateFormat
import java.util.Locale

fun String.formatDate(): String {

    val inputFormat = SimpleDateFormat(Const.ISO_8601_DATE_FORMAT, Locale.getDefault())
    val outputFormat = SimpleDateFormat(Const.SURGERY_APP_DATE_FORMAT, Locale.getDefault())

    return try {
        val date = inputFormat.parse(this)
        outputFormat.format(date!!)
    } catch (_: Exception) {
        Const.INVALID_DATE_ERROR
    }
}

fun getErrorResult(error : ResultWrapper.Error) : String {
   return when{
        error.exception is UnknownHostException -> Const.NO_NETWORK_ERROR
        else -> error.message ?: Const.UNKNOWN_ERROR
    }
}