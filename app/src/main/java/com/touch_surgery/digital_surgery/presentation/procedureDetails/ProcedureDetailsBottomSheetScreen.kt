package com.touch_surgery.digital_surgery.presentation.procedureDetails

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.touch_surgery.digital_surgery.R
import com.touch_surgery.digital_surgery.domain.model.Phase
import com.touch_surgery.digital_surgery.domain.model.Procedure
import com.touch_surgery.digital_surgery.domain.model.ProcedureDetail
import com.touch_surgery.digital_surgery.presentation.components.ErrorScreen
import com.touch_surgery.digital_surgery.presentation.components.FavouriteSwitchButton
import com.touch_surgery.digital_surgery.presentation.components.LoadingScreen
import com.touch_surgery.utils.formatDate
import org.koin.androidx.compose.koinViewModel


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProcedureDetailsBottomSheetScreen(
    procedureId: String,
    onDismiss: () -> Unit){

    val viewModel: ProceduresDetailViewModel = koinViewModel(key = procedureId)
    val state by viewModel.procedureDetailState.collectAsState()

    DisposableEffect(Unit) {
        onDispose {
            viewModel.resetProcedureDetailState()
        }
    }

    LaunchedEffect(procedureId) {
        viewModel.getSingleProcedure(procedureId)
        viewModel.loadProcedureDetailFromDB(procedureId)
    }

    ModalBottomSheet(
        onDismissRequest = onDismiss,
        sheetState = rememberModalBottomSheetState(),
        modifier = Modifier.wrapContentHeight()
            .heightIn(min = 600.dp)
    ) {
        when {
            state.loading -> LoadingScreen()
            state.error != null -> ErrorScreen(state.error)
            state.procedureDetail != null -> {
                ProcedureDetailContent(
                    procedureDetail = state.procedureDetail,
                    procedure = state.procedure,
                    onFavoriteClick = {newState->
                        viewModel.updateFavouriteProcedure(procedureId,newState)
                    }
                )
            }
        }
    }

}

@Composable
fun ProcedureDetailContent(
    procedureDetail: ProcedureDetail?,
    procedure : Procedure?,
    onFavoriteClick: (Boolean) -> Unit,
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 1.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            AsyncImage(
                model = procedureDetail?.card?.url,
                contentDescription = "Procedure image",
                modifier = Modifier
                    .border(width = 0.2.dp, color = Color(0xFF098276))
                    .padding(0.2.dp)
                    .width(150.dp)
                    .height(150.dp),
                contentScale = ContentScale.None,
                error = painterResource(id = R.drawable.no_image_available)
            )

            Spacer(modifier = Modifier.width(16.dp))

            // Content
            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = procedureDetail?.name.toString(),
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = "Duration: ${procedure?.duration} minutes",
                    style = MaterialTheme.typography.bodyMedium
                )
                Text(
                    text = "Created on: ${procedure?.datePublished?.formatDate()}",
                    style = MaterialTheme.typography.bodyMedium
                )
                Spacer(modifier = Modifier.height(8.dp))
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = stringResource(R.string.set_favourite_procedure),
                        style = MaterialTheme.typography.bodyMedium
                    )
                    Spacer(modifier = Modifier.width(8.dp))

                    FavouriteSwitchButton(
                        isChecked = procedure?.isFavourite == true,
                        onCheckedChange = onFavoriteClick)
                }
            }
        }
    }

    PhasesList(procedureDetail?.phases)
}

@Composable
fun PhasesList(phases: List<Phase>?) {
    phases?.let {
        Text(
            text = "Phases",
            style = MaterialTheme.typography.bodyMedium,
            modifier = Modifier.padding(16.dp)
        )

        LazyRow(
            contentPadding = PaddingValues(horizontal = 16.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(phases) { phase ->
                PhaseItem(phase)
            }
        }
    }
}

@Composable
fun PhaseItem(phase: Phase) {
    Column(
        modifier = Modifier
            .width(120.dp)
            .background(MaterialTheme.colorScheme.surface)
            .padding(4.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        AsyncImage(
            model = phase.icon?.url,
            contentDescription = "Phase thumbnail",
            modifier = Modifier
                .size(64.dp)
        )

        Spacer(modifier = Modifier.height(4.dp))

        Text(
            text = phase.name,
            style = MaterialTheme.typography.bodyMedium,
            textAlign = TextAlign.Center,
            maxLines = 2,
            overflow = TextOverflow.Ellipsis
        )
    }
}


