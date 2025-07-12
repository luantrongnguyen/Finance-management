package com.creso.demo_clean_mvvm.presentation

import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.ui.graphics.Color

val LightColors = lightColorScheme(
    primary = Color(0xFFBB86FC),        // purple_200
    onPrimary = Color.White,
    primaryContainer = Color(0xFF6200EE), // purple_500

    secondary = Color(0xFF3700B3),      // purple_700
    onSecondary = Color.White,
    secondaryContainer = Color(0xFF03DAC5), // teal_200

    tertiary = Color(0xFF018786),       // teal_700
    onTertiary = Color.White,

    background = Color.White,
    onBackground = Color.Black,

    surface = Color.White,
    onSurface = Color.Black
)

// Dark mode color scheme (reversed for dark background)
val DarkColors = darkColorScheme(
    primary = Color(0xFFBB86FC),
    onPrimary = Color.Black,
    primaryContainer = Color(0xFF6200EE),
    onPrimaryContainer = Color.White,

    secondary = Color(0xFF03DAC5),
    onSecondary = Color.Black,
    secondaryContainer = Color(0xFF3700B3),
    onSecondaryContainer = Color.White,

    tertiary = Color(0xFF018786),
    onTertiary = Color.White,

    background = Color.Black,
    onBackground = Color.White,
    surface = Color.Black,
    onSurface = Color.White
)
