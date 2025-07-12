package com.creso.demo_clean_mvvm.presentation.screens.main

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Shapes
import androidx.compose.runtime.Composable
import com.creso.demo_clean_mvvm.presentation.theme.DarkColors
import com.creso.demo_clean_mvvm.presentation.theme.LightColors

@Composable
fun MyAppTheme(
    useDarkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colorScheme = if(useDarkTheme) DarkColors else LightColors
    MaterialTheme(
        colorScheme = colorScheme,
        shapes = Shapes(),
        content = content
    )
}