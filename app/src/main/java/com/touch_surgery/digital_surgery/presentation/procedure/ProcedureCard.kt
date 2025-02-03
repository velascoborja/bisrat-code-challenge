package com.touch_surgery.digital_surgery.presentation.procedure

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.touch_surgery.digital_surgery.R
import com.touch_surgery.digital_surgery.domain.model.Procedure
import com.touch_surgery.digital_surgery.presentation.components.FavouriteSwitchButton

@Composable
fun ProcedureCard(
    procedure: Procedure,
    onFavoriteClick: (Boolean) -> Unit,
    onClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable { onClick() },
        elevation = CardDefaults.cardElevation(
            defaultElevation = 6.dp
        )
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                AsyncImage(
                    model = procedure.iconUrl,
                    contentDescription = "Procedure thumbnail",
                    modifier = Modifier
                        .size(64.dp)
                        .clip(RoundedCornerShape(8.dp)),
                    error = painterResource(id = R.drawable.no_image_available),

                    )

                Spacer(modifier = Modifier.width(16.dp))

                Column(modifier = Modifier.weight(2f)) {
                    Text(
                        text = procedure.name,
                        style = MaterialTheme.typography.labelMedium
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    Text(
                        text = if ((procedure.phases?.size ?: 0) > 1)
                            "${procedure.phases?.size} ${stringResource(R.string.phases)}"
                        else "1 ${stringResource(R.string.phase)}",
                        style = MaterialTheme.typography.bodyMedium
                    )
                }
                Column(modifier = Modifier.weight(1f)) {
                    Text(
                        text = stringResource(R.string.set_favourite_procedure),
                        style = MaterialTheme.typography.labelMedium
                    )
                    Spacer(modifier = Modifier.height(12.dp))
                    FavouriteSwitchButton(
                        isChecked = procedure.isFavourite,
                        onCheckedChange = onFavoriteClick
                    )
                }

            }
        }
    }
}