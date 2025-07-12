package com.creso.demo_clean_mvvm.presentation.components

import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.creso.demo_clean_mvvm.presentation.navigation.Screen

@Composable
fun BottomBar(navController: NavController) {
    val items = listOf(
        Screen.Collect,
        Screen.Payout,
        Screen.PayoutType,
        Screen.CollectType,
        Screen.Setting
    )

    NavigationBar(
        contentColor = MaterialTheme.colorScheme.onSurface,
        containerColor = MaterialTheme.colorScheme.onSurface,
        ) {
        val currentDestination = navController.currentBackStackEntryAsState().value?.destination
        items.forEach { screen ->
            val selected = currentDestination?.route == screen.route
            val iconColor = if (selected) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.secondary
            NavigationBarItem(
                icon = { Icon(painter = painterResource(screen.resId),  tint = iconColor ,contentDescription = screen.label, modifier = Modifier.size(32.dp)) },
                selected = selected,
                colors = NavigationBarItemDefaults.colors(
                    indicatorColor = Color.Transparent
                ),
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
