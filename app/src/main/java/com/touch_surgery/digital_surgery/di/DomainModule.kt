package com.touch_surgery.digital_surgery.di

import com.touch_surgery.digital_surgery.domain.usecase.favourites.UpdateFavouriteUseCase
import com.touch_surgery.digital_surgery.domain.usecase.procedureDetail.FetchProcedureDetailsFromApiUseCase
import com.touch_surgery.digital_surgery.domain.usecase.procedureDetail.GetProcedureDetailUseCase
import com.touch_surgery.digital_surgery.domain.usecase.procedures.FetchProceduresFromApiUseCase
import com.touch_surgery.digital_surgery.domain.usecase.procedures.GetProceduresUseCase
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val procedureDomainModule = module {
    singleOf(::GetProceduresUseCase)
    singleOf(::UpdateFavouriteUseCase)
    singleOf(::FetchProceduresFromApiUseCase)
}

val procedureDetailDomainModule = module {
    singleOf(::FetchProcedureDetailsFromApiUseCase)
    singleOf(::GetProcedureDetailUseCase)
}