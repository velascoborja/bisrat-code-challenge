package com.touch_surgery.digital_surgery.domain.usecase

import com.touch_surgery.digital_surgery.domain.repository.ProcedureRepository
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Test


class ToggleFavoriteUseCaseTest {

    @Test
    fun `toggle favourite procedures from repository`() = runTest {
        val mockRepo = mockk<ProcedureRepository>()
       // coEvery { mockRepo.toggleFavorite("id",true) } returns
    }
}