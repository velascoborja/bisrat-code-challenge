package com.touch_surgery.digital_surgery.presentation.state

import com.touch_surgery.digital_surgery.domain.model.Procedure
import com.touch_surgery.digital_surgery.domain.model.ProcedureDetail

data class ProceduresState(
    val procedures: List<Procedure> = emptyList(),
    val loading: Boolean = false,
    val error: String? = null,
)

data class ProcedureDetailState(
    val procedureDetail: ProcedureDetail? = null,
    val loading: Boolean = false,
    val success : Boolean = false,
    val error: String? = null
)
