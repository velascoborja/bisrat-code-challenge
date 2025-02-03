package com.touch_surgery.digital_surgery.presentation.components

import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.Icon
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun FavouriteSwitchButton(
    isChecked: Boolean,
    onCheckedChange: (Boolean) -> Unit
) {
    Switch(
        checked = isChecked,
        onCheckedChange = { newValue ->
            onCheckedChange(newValue)
        },
        thumbContent = if (isChecked) {
            {
                Icon(
                    imageVector = Icons.Filled.Check,
                    contentDescription = null,
                    modifier = Modifier.size(SwitchDefaults.IconSize),
                )
            }
        } else {
            null
        }
    )
}