package com.example.zenconnect.ui.theme

import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable

// Importez les couleurs définies dans Colors.kt
val DarkColorScheme = darkColorScheme(
    primary = Blue,
    secondary = BlueGrey,
    tertiary = LightBlue
)

val LightColorScheme = lightColorScheme(
    primary = Blue,
    secondary = BlueGrey,
    tertiary = LightBlue
)

@Composable
fun ZenConnectTheme(
    darkTheme: Boolean = false,
    content: @Composable () -> Unit
) {
    val colorScheme = if (darkTheme) DarkColorScheme else LightColorScheme

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}
