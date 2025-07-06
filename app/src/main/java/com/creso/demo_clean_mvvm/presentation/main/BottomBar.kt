package com.creso.demo_clean_mvvm.presentation.main

import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState


@Composable
fun BottomBar(navController: NavController) {
    val items = listOf(
        Screen.Collect,
        Screen.Payout,
        Screen.PayoutType,
        Screen.CollectType
    )
    NavigationBar {
        val currentDestination = navController.currentBackStackEntryAsState().value?.destination
        items.forEach { screen ->
            val selected = currentDestination?.route == screen.route
            NavigationBarItem(
                icon = { Icon(screen.icon, contentDescription = screen.label) },
                label = {Text(screen.label) },
                selected = selected,
                onClick = {
                    if (!selected) {
                        navController.navigate(screen.route) {
                            popUpTo(navController.graph.startDestinationId) { saveState = true }
                            launchSingleTop = true
                            restoreState = true
                        }
                    }
                },
            )
        }
    }
}
