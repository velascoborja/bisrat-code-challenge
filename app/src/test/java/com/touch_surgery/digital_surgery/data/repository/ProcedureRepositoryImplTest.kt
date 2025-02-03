package com.touch_surgery.digital_surgery.data.repository

import com.touch_surgery.digital_surgery.data.local.dao.ProcedureDao
import com.touch_surgery.digital_surgery.data.local.model.ProcedureEntity
import com.touch_surgery.digital_surgery.data.remote.api.ProcedureApi
import com.touch_surgery.utils.ResultWrapper
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertFalse
import junit.framework.TestCase.assertTrue
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Test
import java.io.IOException

@OptIn(ExperimentalCoroutinesApi::class)
class ProcedureRepositoryImplTest {


    private lateinit var repository: ProcedureRepositoryImpl
    private lateinit var mockApi: ProcedureApi
    private lateinit var mockDao: ProcedureDao
    private val testDispatcher = StandardTestDispatcher()


    @Before
    fun setUp() {
        Dispatchers.setMain(testDispatcher)
        mockApi = mockk()
        mockDao = mockk()
        repository = ProcedureRepositoryImpl(mockApi, mockDao)
    }

    @Test
    fun `getProcedures should return mapped procedures from database`() = runTest {

        val testEntities = listOf(
            ProcedureEntity(uuid = "1", name = "Test 1", phases = emptyList(),
                iconUrl = "", datePublished = "", duration = 0, isFavourite = false),
            ProcedureEntity(uuid = "2", name = "Test 2",phases = emptyList(),
                iconUrl = "", datePublished = "", duration = 0, isFavourite = true)
        )

        coEvery { mockDao.getAllProcedures() } returns flowOf(testEntities)
        val result = repository.getProcedures().first()

        assertEquals(2, result.size)

        assertEquals("Test 1", result[0].name)

        assertFalse(result[0].isFavourite)

        assertEquals("Test 2", result[1].name)

        assertTrue(result[1].isFavourite)

        coVerify(exactly = 1) { mockDao.getAllProcedures() }
    }

    @Test
    fun `fetchProceduresFromApi should return error on network failure`() = runTest {
        // Arrange
        val testException = IOException("Network error")
        coEvery { mockApi.getProcedures() } throws testException

        // Act
        val result = repository.fetchProceduresFromApi()

        // Assert
        assertTrue(result is ResultWrapper.Error)
        assertEquals(testException, (result as ResultWrapper.Error).exception)
    }


    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }
}