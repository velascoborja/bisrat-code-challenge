package com.touch_surgery.digital_surgery.domain.usecase

import com.touch_surgery.digital_surgery.domain.repository.ProcedureRepository
import com.touch_surgery.digital_surgery.domain.usecase.procedures.GetProceduresUseCase
import io.mockk.coEvery
import io.mockk.mockk
import junit.framework.TestCase.assertTrue
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Test


class GetProceduresUseCaseTest {

    @Test
    fun `load procedures from repository`(): Unit = runTest {
        val mockRepo = mockk<ProcedureRepository> {
            coEvery { getProcedures() } returns flowOf(emptyList())
        }
        val useCase = GetProceduresUseCase(mockRepo)

        useCase().collect { result ->
            assertTrue(result.isEmpty())
        }
    }
}