package com.touch_surgery.digital_surgery.presentation.theme

import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext

private val DarkColorScheme = darkColorScheme(
    primary = Blue40,
    onPrimary = Color.Black,
    secondary = Gray40,
    onSecondary = Color.Black,
    tertiary = Blue20,
    onTertiary = Color.Black,
    background = Gray80,
    onBackground = Color.White,
    surface = Gray60,
    onSurface = Color.White
)

private val LightColorScheme = lightColorScheme(
    primary = Blue60,
    onPrimary = Color.White,
    secondary = Gray60,
    onSecondary = Color.White,
    tertiary = Blue40,
    onTertiary = Color.Black,
    background = Color.White,
    onBackground = Gray80,
    surface = Gray20,
    onSurface = Gray80
)


@Composable
fun DigitalSurgeryTheme(
    useDarkTheme: Boolean = isSystemInDarkTheme(),
    // Dynamic color is available on Android 12+
    content: @Composable () -> Unit
) {
    val context = LocalContext.current
    val colors = when {
        (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) -> {
            if (useDarkTheme) dynamicDarkColorScheme(context)
            else dynamicLightColorScheme(context)
        }
        useDarkTheme -> DarkColorScheme
        else -> LightColorScheme
    }


    MaterialTheme(
        colorScheme = colors,
        typography = Typography,
        content = content
    )
}