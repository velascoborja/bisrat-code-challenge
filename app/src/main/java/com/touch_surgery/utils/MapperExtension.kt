package com.touch_surgery.utils

import com.touch_surgery.digital_surgery.data.local.model.CardEntity
import com.touch_surgery.digital_surgery.data.local.model.PhaseEntity
import com.touch_surgery.digital_surgery.data.local.model.PhaseIconEntity
import com.touch_surgery.digital_surgery.data.local.model.ProcedureDetailEntity
import com.touch_surgery.digital_surgery.data.local.model.ProcedureEntity
import com.touch_surgery.digital_surgery.data.remote.model.CardResponse
import com.touch_surgery.digital_surgery.data.remote.model.PhaseIconResponse
import com.touch_surgery.digital_surgery.data.remote.model.PhaseResponse
import com.touch_surgery.digital_surgery.data.remote.model.ProcedureDetailResponse
import com.touch_surgery.digital_surgery.data.remote.model.ProcedureResponse
import com.touch_surgery.digital_surgery.domain.model.Card
import com.touch_surgery.digital_surgery.domain.model.Phase
import com.touch_surgery.digital_surgery.domain.model.PhaseIcon
import com.touch_surgery.digital_surgery.domain.model.Procedure
import com.touch_surgery.digital_surgery.domain.model.ProcedureDetail

// Conversion: API Response → Entity
fun ProcedureResponse.toProcedureEntity(): ProcedureEntity = ProcedureEntity(
    uuid = uuid,
    name = name,
    phases = phases,
    iconUrl = icon?.url,
    datePublished = datePublished,
    duration = duration
)

// Conversion: Entity → Data transfer object
fun ProcedureEntity.toProcedureDTO(): Procedure = Procedure(
    uuid = uuid,
    name = name,
    iconUrl = iconUrl,
    phases = phases,
    datePublished = datePublished,
    duration = duration,
    isFavourite = isFavourite
)

// Conversion: ProcedureDetailResponse → Entity
fun ProcedureDetailResponse.toProcedureDetailEntity() = ProcedureDetailEntity(
    uuid = uuid,
    name = name,
    phases = phases?.map { it.toPhaseEntity() },
    card = card?.toCardEntity()
)

private fun PhaseResponse.toPhaseEntity() = PhaseEntity(
    uuid = uuid,
    name = name,
    icon = icon?.toPhaseIconEntity()
)

private fun PhaseIconResponse.toPhaseIconEntity() = PhaseIconEntity(
    uuid = uuid,
    url = url
)

private fun CardResponse.toCardEntity() = CardEntity(
    uuid = uuid,
    url = url
)

// Conversion: ProcedureDetailEntity → Data transfer object
fun ProcedureDetailEntity.toProcedureDetailDTO() = ProcedureDetail(
    uuid = uuid,
    name = name,
    phases = phases?.map { it.toPhaseDTO() },
    card = card?.toCardDTO()
)

private fun PhaseEntity.toPhaseDTO() = Phase(
    uuid = uuid,
    name = name,
    icon = icon?.toPhaseIconDTO()
)

private fun PhaseIconEntity.toPhaseIconDTO() = PhaseIcon(
    uuid = uuid,
    url = url
)

private fun CardEntity.toCardDTO() = Card(
    uuid = uuid,
    url = url
)