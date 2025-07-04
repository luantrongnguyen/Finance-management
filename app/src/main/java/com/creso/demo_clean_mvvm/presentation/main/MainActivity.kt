package com.creso.demo_clean_mvvm.presentation.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme  {
                val navController = rememberNavController()
                Scaffold(
                    bottomBar =
                    { BottomBar(navController = navController) }

                ) { padding ->
                    MainNavHost(navController = navController, modifier = Modifier.padding(padding))
                }
            }
        }
    }
}
