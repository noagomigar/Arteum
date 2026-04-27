package com.example.apilistapp.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val LightColorScheme = lightColorScheme(
    primary = NavyBlue,
    onPrimary = Color.White,
    primaryContainer = LightBlue,
    onPrimaryContainer = DeepNavy,

    secondary = AccentBlue,
    onSecondary = Color.White,
    secondaryContainer = WashBlue,
    onSecondaryContainer = DeepNavy,

    background = Color.White,
    onBackground = DeepNavy,

    surface = WashBlue,
    onSurface = DeepNavy,
    surfaceVariant = LightBlue,
    onSurfaceVariant = TextMuted,

    outline = LightBlue,
    outlineVariant = WashBlue,

    error = Color(0xFFB02020),
    onError = Color.White,
)

private val DarkColorScheme = darkColorScheme(
    primary = AccentBlue,
    onPrimary = DeepNavy,
    primaryContainer = NavyBlue,
    onPrimaryContainer = LightBlue,

    secondary = LightBlue,
    onSecondary = DeepNavy,
    secondaryContainer = Color(0xFF1A2D45),
    onSecondaryContainer = LightBlue,

    background = DarkBackground,
    onBackground = DarkOnSurface,

    surface = DarkSurface,
    onSurface = DarkOnSurface,
    surfaceVariant = DarkCard,
    onSurfaceVariant = DarkMuted,

    outline = DarkOutline,
    outlineVariant = DarkCard,

    error = Color(0xFFE57373),
    onError = DarkBackground,
)

@Composable
fun APIListAppTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colorScheme = if (darkTheme) DarkColorScheme else LightColorScheme

    MaterialTheme(
        colorScheme = colorScheme,
        content = content
    )
}