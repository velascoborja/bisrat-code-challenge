package com.touch_surgery.digital_surgery.data.repository

import com.touch_surgery.digital_surgery.data.local.dao.ProcedureDetailDao
import com.touch_surgery.digital_surgery.data.remote.api.ProcedureApi
import com.touch_surgery.digital_surgery.domain.model.ProcedureDetail
import com.touch_surgery.digital_surgery.domain.repository.ProcedureDetailRepository
import com.touch_surgery.utils.ResultWrapper
import com.touch_surgery.utils.toProcedureDetailDTO
import com.touch_surgery.utils.toProcedureDetailEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext

class ProcedureDetailRepositoryImpl(
    private val procedureApi: ProcedureApi,
    private val dao : ProcedureDetailDao
) : ProcedureDetailRepository {


    override suspend fun fetchProcedureDetailFromApi(procedureID : String) : ResultWrapper<Any> {
      return  withContext(Dispatchers.IO) {
            ResultWrapper.Loading
            try {
                val response = procedureApi.getProcedureDetails(procedureID)
                if (response.isSuccessful && response.body() != null){
                    val procedureDetail = response.body()?.toProcedureDetailEntity()

                    if (procedureDetail != null) {
                        dao.insertDetails(procedureDetail)
                    }
                    ResultWrapper.Success(true)
                }else {
                    ResultWrapper.Error("")
                }
            } catch (e: Exception) {
                ResultWrapper.Error(e.localizedMessage)
            }
        }
    }

    override fun getProcedureDetail(procedureID: String): Flow<ProcedureDetail?> {
        return dao.getProcedure(procedureID)
            .map {detailEntity-> detailEntity?.toProcedureDetailDTO()
            }.flowOn(Dispatchers.IO)
    }

}