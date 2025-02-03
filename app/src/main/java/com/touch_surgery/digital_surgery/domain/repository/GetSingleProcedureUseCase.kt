package com.touch_surgery.digital_surgery.domain.repository

import com.touch_surgery.digital_surgery.domain.model.Procedure
import kotlinx.coroutines.flow.Flow

class GetSingleProcedureUseCase(private val repository: ProcedureDetailRepository) {
    operator fun invoke(procedureId : String) : Flow<Procedure> = repository.getSingleProcedure(procedureId)
}