package com.touch_surgery.digital_surgery.data.remote.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass


@JsonClass(generateAdapter = true)
data class ProcedureResponse(
    @Json(name = "uuid")
    val uuid: String,
    @Json(name = "name")
    val name: String,
    @Json(name = "phases")
    val phases: List<String>?,
    @Json(name = "icon")
    val icon: IconResponse?,
    @Json(name ="date_published")
    val datePublished: String?,
    @Json(name ="duration")
    val duration: Int?,
)


@JsonClass(generateAdapter = true)
data class IconResponse(
    @Json(name = "url") val url: String?
)
