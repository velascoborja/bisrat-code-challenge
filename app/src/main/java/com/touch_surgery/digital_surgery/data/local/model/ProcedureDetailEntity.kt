package com.touch_surgery.digital_surgery.data.local.model

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity
data class ProcedureDetailEntity(
    @PrimaryKey
    val uuid: String,
    val name: String,
    val phases: List<PhaseEntity>?,
    val card: CardEntity?
)

@Entity
data class PhaseEntity(
    @PrimaryKey
    val uuid: String,
    val name: String,
    val icon: PhaseIconEntity?
)

@Entity
data class PhaseIconEntity(
    @PrimaryKey
    val uuid: String,
    val url: String?
)

@Entity
data class CardEntity(
    @PrimaryKey
    val uuid: String,
    val url: String?
)