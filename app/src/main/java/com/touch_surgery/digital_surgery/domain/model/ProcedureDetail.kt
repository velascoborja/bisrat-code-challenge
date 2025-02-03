package com.touch_surgery.digital_surgery.domain.model

data class ProcedureDetail(
    val uuid: String,
    val name: String,
    val phases: List<Phase>?,
    val card: Card?

)

data class Phase(
    val uuid: String,
    val name: String,
    val icon: PhaseIcon?
)

data class PhaseIcon(
    val uuid: String,
    val url: String?
)

data class Card(
    val uuid: String,
    val url: String?
)
