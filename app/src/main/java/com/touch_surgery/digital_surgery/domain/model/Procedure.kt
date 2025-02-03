package com.touch_surgery.digital_surgery.domain.model


data class Procedure(
    val uuid: String,
    val name: String,
    val iconUrl: String?,
    val phases: List<String>?,
    val datePublished: String?,
    val duration: Int?,
    val isFavourite : Boolean = false
)


