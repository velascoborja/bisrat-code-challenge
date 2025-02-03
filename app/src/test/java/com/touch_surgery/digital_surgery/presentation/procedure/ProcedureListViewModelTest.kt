package com.touch_surgery.digital_surgery.presentation.procedure

import com.touch_surgery.digital_surgery.domain.repository.ProcedureRepository
import io.mockk.mockk
import org.junit.Before
import org.junit.Rule
import org.junit.Test




class ProcedureListViewModelTest {

    @get:Rule
    //val coroutineRule = MainCoroutineRule()

    private lateinit var repository: ProcedureRepository
    private lateinit var viewModel: ProcedureViewModel
    @Before
    fun setUp() {
        repository = mockk()
        //viewModel = ProcedureListViewModel(repository)
    }

    @Test
    fun getProcedureState() {
    }

    @Test
    fun getSnackbarMessage() {
    }

    @Test
    fun getSplashCondition() {
    }
}