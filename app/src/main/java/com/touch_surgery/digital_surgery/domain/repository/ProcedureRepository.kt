package com.touch_surgery.digital_surgery.domain.repository

import com.touch_surgery.digital_surgery.domain.model.Procedure
import com.touch_surgery.utils.ResultWrapper
import kotlinx.coroutines.flow.Flow

interface ProcedureRepository {
    fun getProcedures(): Flow<List<Procedure>>
    suspend fun fetchProceduresFromApi() : ResultWrapper<Any>
    suspend fun updateFavourite(uuid: String, isFavorite: Boolean)
}