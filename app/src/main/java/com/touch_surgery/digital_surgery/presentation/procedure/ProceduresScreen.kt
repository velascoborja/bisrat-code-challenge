package com.touch_surgery.digital_surgery.presentation.procedure

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.touch_surgery.digital_surgery.R
import com.touch_surgery.digital_surgery.presentation.components.ErrorScreen
import com.touch_surgery.digital_surgery.presentation.components.LoadingScreen
import com.touch_surgery.digital_surgery.presentation.procedureDetails.ProcedureDetailsBottomSheetScreen
import org.koin.androidx.compose.koinViewModel


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProceduresScreen(
    viewModel: ProcedureViewModel = koinViewModel(),
) {
    val state by viewModel.procedureState.collectAsState()
    var selectedProcedure by remember { mutableStateOf<String?>(null) }
    val snackBarHostState = remember { SnackbarHostState() }
    val snackBarMessage by viewModel.snackBarMessage.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(title = { Text(text = stringResource(R.string.surgical_title)) })
        },
        snackbarHost = { SnackbarHost(hostState = snackBarHostState) }
    ) { padding ->
        Box(modifier = Modifier.padding(padding)) {
            when {
                state.loading -> LoadingScreen()
                state.error != null -> ErrorScreen(state.error)
                else -> LazyColumn {
                    items(
                        items = state.procedures,
                        key = { it.uuid }
                    ) { procedure ->
                        ProcedureCard(
                            procedure = procedure,
                            onFavoriteClick = {newState->
                                viewModel.updateFavouriteProcedure(procedure.uuid, newState)
                            },
                            onClick = {
                                selectedProcedure = procedure.uuid
                            }
                        )
                    }
                }
            }

            selectedProcedure?.let { procedureID ->
                ProcedureDetailsBottomSheetScreen(
                    procedureId = procedureID,
                    onDismiss = { selectedProcedure = null },
                )

            }
        }

        snackBarMessage?.let { resId ->
            val message = stringResource(resId)
            LaunchedEffect(snackBarMessage) {
                snackBarHostState.showSnackbar(
                    message = message,
                    duration = SnackbarDuration.Short
                )
                viewModel.snackBarShown()
            }
        }

    }
}
