package com.touch_surgery.utils

object Const {

    const val BASE_URL = "https://staging.touchsurgery.com/api/v3/"

    const val DATABASE_NAME = "surgery-db"

    const val GET_ALL_PROCEDURES = "SELECT * FROM ProcedureEntity"
    const val UPDATE_FAVOURITE_PROCEDURE = "UPDATE ProcedureEntity SET isFavourite = :isFavourite WHERE uuid = :uuid"

    const val ISO_8601_DATE_FORMAT = "yyyy-MM-dd'T'HH:mm:ss.SSSSSS"
    const val SURGERY_APP_DATE_FORMAT = "dd/MM/yyyy"

    const val NO_NETWORK_ERROR = "Please check network connection"
    const val UNKNOWN_ERROR = "unknown error occurred"
    const val INVALID_SERVER_ERROR = "invalid server response"
    const val EMPTY_SERVER_RESPONSE = "api response is null or empty"

    const val INVALID_DATE_ERROR = "Invalid Date"
}