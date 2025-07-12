package com.creso.demo_clean_mvvm.presentation.main

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Shapes
import androidx.compose.runtime.Composable
import com.creso.demo_clean_mvvm.presentation.DarkColors
import com.creso.demo_clean_mvvm.presentation.LightColors

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