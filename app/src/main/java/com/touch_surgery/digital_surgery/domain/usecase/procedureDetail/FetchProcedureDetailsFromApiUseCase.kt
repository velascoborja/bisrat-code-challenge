package com.touch_surgery.digital_surgery.domain.usecase.procedureDetail

import com.touch_surgery.digital_surgery.domain.repository.ProcedureDetailRepository

class FetchProcedureDetailsFromApiUseCase(
    private val detailRepository: ProcedureDetailRepository
) {

   suspend operator fun invoke(procedureID: String) =
        detailRepository.fetchProcedureDetailFromApi(procedureID)
}