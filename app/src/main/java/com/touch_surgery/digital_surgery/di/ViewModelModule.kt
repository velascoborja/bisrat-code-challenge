package com.touch_surgery.digital_surgery.di

import com.touch_surgery.digital_surgery.presentation.procedure.ProcedureViewModel
import com.touch_surgery.digital_surgery.presentation.procedureDetails.ProceduresDetailViewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.module


val viewModelModule = module {
    viewModelOf(::ProcedureViewModel)
    viewModelOf(::ProceduresDetailViewModel)
}