package com.touch_surgery.digital_surgery.data.repository

import com.touch_surgery.digital_surgery.data.local.dao.ProcedureDetailDao
import com.touch_surgery.digital_surgery.data.remote.api.ProcedureApi
import com.touch_surgery.digital_surgery.domain.model.Procedure
import com.touch_surgery.digital_surgery.domain.model.ProcedureDetail
import com.touch_surgery.digital_surgery.domain.repository.ProcedureDetailRepository
import com.touch_surgery.utils.Const
import com.touch_surgery.utils.ResultWrapper
import com.touch_surgery.utils.toProcedureDTO
import com.touch_surgery.utils.toProcedureDetailDTO
import com.touch_surgery.utils.toProcedureDetailEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext

class ProcedureDetailRepositoryImpl(
    private val procedureApi: ProcedureApi,
    private val dao: ProcedureDetailDao
) : ProcedureDetailRepository {


    override suspend fun fetchProcedureDetailFromApi(procedureID: String): ResultWrapper<Any> {
        return withContext(Dispatchers.IO) {
            ResultWrapper.Loading
            try {
                val response = procedureApi.getProcedureDetails(procedureID)
                if (response.isSuccessful && response.body() != null) {
                    val procedureDetail = response.body()?.toProcedureDetailEntity()

                    if (procedureDetail != null) {
                        dao.insertDetails(procedureDetail)
                    }
                    ResultWrapper.Success(true)
                } else {
                    ResultWrapper.Error(Const.EMPTY_SERVER_RESPONSE)
                }
            } catch (e: Exception) {
                ResultWrapper.Error(e.localizedMessage)
            }
        }
    }


    override fun getProcedureWithDetail(procedureID: String): Flow<Pair<Procedure?, ProcedureDetail?>> {
        return combine(
            getSingleProcedure(procedureID),
            dao.getProcedureDetail(procedureID)
                .map { detailEntity -> detailEntity?.toProcedureDetailDTO() }
        ) { procedure, detail ->
            Pair(procedure, detail)
        }.flowOn(Dispatchers.IO)
    }

    override fun getSingleProcedure(procedureID: String): Flow<Procedure> {
        return dao.getSingleProcedure(procedureID)
            .map { it.toProcedureDTO() }.flowOn(Dispatchers.IO)
    }


}