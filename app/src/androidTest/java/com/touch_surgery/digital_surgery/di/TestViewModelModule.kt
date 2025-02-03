package com.touch_surgery.digital_surgery.di

import com.touch_surgery.digital_surgery.presentation.procedure.ProcedureViewModel
import io.mockk.mockk
import org.koin.dsl.module



val testViewModelModule = module{
    single { mockk<ProcedureViewModel>(relaxed = true) }
}