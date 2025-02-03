package com.touch_surgery.digital_surgery.data.remote.model


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ProcedureDetailResponse(
    @Json(name ="uuid")
    val uuid: String,
    @Json(name ="name")
    val name: String,
    @Json(name ="phases")
    val phases: List<PhaseResponse>?,
    @Json(name = "card")
    val card: CardResponse?)


@JsonClass(generateAdapter = true)
data class PhaseResponse(
    @Json(name ="uuid")
    val uuid: String,
    @Json(name ="name")
    val name: String,
    @Json(name ="icon")
    val icon: PhaseIconResponse?
)

@JsonClass(generateAdapter = true)
data class PhaseIconResponse(
    @Json(name ="uuid")
    val uuid: String,
    @Json(name ="url")
    val url: String?,
)

@JsonClass(generateAdapter = true)
data class CardResponse(
    @Json(name ="uuid")
    val uuid: String,
    @Json(name ="url")
    val url: String?,
)

