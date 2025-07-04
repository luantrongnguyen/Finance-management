package com.luannguyentrong.minipos.presentation.main

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.luannguyentrong.minipos.presentation.payout.PayoutListScreen

@Composable
fun MainNavHost(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    NavHost(
        navController = navController,
        startDestination = Screen.Collect.route,
        modifier = modifier
    ) {
        composable(Screen.Collect.route) {
            CollectListScreen(navController)
        }
        composable(Screen.Payout.route) {
            PayoutListScreen(navController)
        }
        // bạn có thể thêm các route khác như CollectType, PayoutType ở đây
    }
}

sealed class Screen(val route: String, val icon: ImageVector, val label: String) {
    object Collect : Screen("collect", Icons.Default.Add, "Collect")
    object Payout : Screen("payout", Icons.Default.ShoppingCart, "Payout")
}