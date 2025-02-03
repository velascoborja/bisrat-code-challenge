package com.touch_surgery.digital_surgery.presentation.procedure

import androidx.activity.ComponentActivity
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.touch_surgery.digital_surgery.domain.model.Procedure
import com.touch_surgery.digital_surgery.presentation.state.ProceduresState
import io.mockk.every
import io.mockk.mockk
import io.mockk.unmockkAll
import kotlinx.coroutines.flow.MutableStateFlow
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.koin.core.context.loadKoinModules
import org.koin.core.context.unloadKoinModules
import org.koin.dsl.module
import org.koin.test.KoinTest


@RunWith(AndroidJUnit4::class)
class ProceduresScreenTest : KoinTest {

    @get:Rule
    val composeTestRule = createAndroidComposeRule<ComponentActivity>()

    private lateinit var mockViewModel: ProcedureViewModel

    @Before
    fun setUp() {
        // Create a relaxed mock of ProcedureViewModel
        mockViewModel = mockk<ProcedureViewModel>(relaxed = true)

        // Load the test module with the mock ViewModel
        loadKoinModules(module {
            single { mockViewModel }
        })
    }

    @After
    fun tearDown() {
        unmockkAll()
        unloadKoinModules(module {
            single { mockViewModel }
        })
    }

    @Test
    fun whenLoading_shouldShowLoadingIndicator() {
        val testProcedures = listOf(
            Procedure(uuid = "1", name = "Procedure 1", phases = emptyList(), iconUrl = "", datePublished = "", duration = 0),
            Procedure(uuid = "2", name = "Procedure 2", phases = emptyList(), iconUrl = "", datePublished = "", duration = 0)
        )

        // Mock procedureState and snackBarMessage
        val stateFlowProceduresState = MutableStateFlow(
            ProceduresState(procedures = testProcedures, loading = false, error = null)
        )
        val stateFlowSnackBarMessage = MutableStateFlow<Int?>(null)

        every { mockViewModel.procedureState } returns stateFlowProceduresState
        every { mockViewModel.snackBarMessage } returns stateFlowSnackBarMessage

        // When
        composeTestRule.setContent {
            ProceduresScreen(viewModel = mockViewModel)
        }

        // Then
        composeTestRule.onNodeWithText("Procedure 1").assertIsDisplayed()
        composeTestRule.onNodeWithText("Procedure 2").assertIsDisplayed()
    }
}
