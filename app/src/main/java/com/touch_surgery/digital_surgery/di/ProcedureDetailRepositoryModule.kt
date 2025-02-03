package com.touch_surgery.digital_surgery.di

import com.touch_surgery.digital_surgery.data.repository.ProcedureDetailRepositoryImpl
import com.touch_surgery.digital_surgery.domain.repository.ProcedureDetailRepository
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module


val procedureDetailRepositoryModule = module {
    singleOf(::ProcedureDetailRepositoryImpl) {bind<ProcedureDetailRepository>()}
}