package com.touch_surgery.digital_surgery.data.remote.api

import com.touch_surgery.digital_surgery.data.remote.model.ProcedureDetailResponse
import com.touch_surgery.digital_surgery.data.remote.model.ProcedureResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface ProcedureApi {

    @GET("procedures")
    suspend fun getProcedures(): Response<List<ProcedureResponse>>

    @GET("procedures/{uuid}")
    suspend fun getProcedureDetails(@Path("uuid") uuid: String): Response<ProcedureDetailResponse>
}