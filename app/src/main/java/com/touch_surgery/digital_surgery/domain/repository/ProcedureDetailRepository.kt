package com.touch_surgery.digital_surgery.domain.repository

import com.touch_surgery.digital_surgery.domain.model.ProcedureDetail
import com.touch_surgery.utils.ResultWrapper
import kotlinx.coroutines.flow.Flow

interface ProcedureDetailRepository {

    suspend fun fetchProcedureDetailFromApi(procedureID : String) : ResultWrapper<Any>
    fun getProcedureDetail(procedureID: String) : Flow<ProcedureDetail?>

}