package com.touch_surgery.digital_surgery.data.local.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class ProcedureEntity(
    @PrimaryKey
    val uuid: String,
    val name: String,
    val phases: List<String>?,
    val iconUrl: String?,
    val datePublished: String?,
    val duration: Int?,
    val isFavourite : Boolean = false
)
