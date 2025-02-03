package com.touch_surgery.digital_surgery.data.repository

import com.touch_surgery.digital_surgery.data.local.dao.ProcedureDao
import com.touch_surgery.digital_surgery.data.remote.api.ProcedureApi
import com.touch_surgery.digital_surgery.domain.model.Procedure
import com.touch_surgery.digital_surgery.domain.repository.ProcedureRepository
import com.touch_surgery.utils.Const
import com.touch_surgery.utils.ResultWrapper
import com.touch_surgery.utils.toProcedureDTO
import com.touch_surgery.utils.toProcedureEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext

class ProcedureRepositoryImpl(
    private val procedureApi: ProcedureApi,
    private val dao: ProcedureDao
) : ProcedureRepository {


    override fun getProcedures(): Flow<List<Procedure>> {
        return dao.getAllProcedures().map { entities ->
            entities.map { entity -> entity.toProcedureDTO() }
        }.flowOn(Dispatchers.IO)
    }

    override suspend fun fetchProceduresFromApi() : ResultWrapper<*> {
       return withContext(Dispatchers.IO) {
           ResultWrapper.Loading
            try {
                val response = procedureApi.getProcedures()
                if (response.isSuccessful && response.body() != null) {
                    val apiProcedures = response.body()?.map {procedureResponse->
                        procedureResponse.toProcedureEntity() }
                    if (apiProcedures != null) {
                        // Get the current procedures from the database
                        val currentProcedures = dao.getAllProcedures().first()

                        // Merge API data with existing favorite statuses
                        val updatedProcedures = apiProcedures.map { apiProcedure ->
                            val existingProcedure = currentProcedures.find { it.uuid == apiProcedure.uuid }
                            apiProcedure.copy(isFavourite = existingProcedure?.isFavourite ?: false)
                        }

                        // Insert the updated procedures into the database
                        dao.insertProcedures(updatedProcedures)
                        ResultWrapper.Success(true)
                    } else {
                        ResultWrapper.Error(message = Const.EMPTY_SERVER_RESPONSE)
                    }
                }else {
                    ResultWrapper.Error(message = Const.INVALID_SERVER_ERROR)
                }
            } catch (e: Exception) {
                ResultWrapper.Error(exception = e)
            }
        }
    }

    override suspend fun updateFavourite(uuid: String, isFavorite: Boolean) {
        withContext(Dispatchers.IO) {
           dao.updateFavorite(uuid, isFavorite)
        }
    }
}