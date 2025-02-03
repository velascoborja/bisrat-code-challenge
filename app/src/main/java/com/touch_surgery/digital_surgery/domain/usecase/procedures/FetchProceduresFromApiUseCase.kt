package com.touch_surgery.digital_surgery.domain.usecase.procedures

import com.touch_surgery.digital_surgery.domain.repository.ProcedureRepository

class FetchProceduresFromApiUseCase(private val repository: ProcedureRepository) {
    suspend operator fun invoke() = repository.fetchProceduresFromApi()
}