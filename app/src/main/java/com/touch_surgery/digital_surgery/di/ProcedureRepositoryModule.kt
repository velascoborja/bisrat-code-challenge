package com.touch_surgery.digital_surgery.di

import com.touch_surgery.digital_surgery.data.repository.ProcedureRepositoryImpl
import com.touch_surgery.digital_surgery.domain.repository.ProcedureRepository
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module


val procedureRepositoryModule = module {
    singleOf(::ProcedureRepositoryImpl) {bind<ProcedureRepository>()}
}