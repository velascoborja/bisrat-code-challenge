package com.touch_surgery.digital_surgery.domain.usecase.procedureDetail

import com.touch_surgery.digital_surgery.domain.model.Procedure
import com.touch_surgery.digital_surgery.domain.model.ProcedureDetail
import com.touch_surgery.digital_surgery.domain.repository.ProcedureDetailRepository
import kotlinx.coroutines.flow.Flow

class GetProcedureDetailUseCase(private val procedureDetailRepository: ProcedureDetailRepository) {

    operator fun invoke(procedureID: String) : Flow<Pair<Procedure?, ProcedureDetail?>>{
        return procedureDetailRepository.getProcedureWithDetail(procedureID)
    }
}