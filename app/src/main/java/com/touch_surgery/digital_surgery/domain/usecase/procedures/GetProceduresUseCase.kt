package com.touch_surgery.digital_surgery.domain.usecase.procedures

import com.touch_surgery.digital_surgery.domain.model.Procedure
import com.touch_surgery.digital_surgery.domain.repository.ProcedureRepository
import kotlinx.coroutines.flow.Flow

class GetProceduresUseCase(private val repository: ProcedureRepository
) {
     operator fun invoke(): Flow<List<Procedure>> = repository.getProcedures()
}

