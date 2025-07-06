package com.creso.demo_clean_mvvm.presentation.main

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.creso.demo_clean_mvvm.presentation.collect.CollectFormScreen
import com.creso.demo_clean_mvvm.presentation.collect.CollectListScreen
import com.creso.demo_clean_mvvm.presentation.collecttype.CollectTypeFormScreen
import com.creso.demo_clean_mvvm.presentation.collecttype.CollectTypeListScreen
import com.creso.demo_clean_mvvm.presentation.payout.PayoutFormScreen
import com.creso.demo_clean_mvvm.presentation.payout.PayoutListScreen
import com.creso.demo_clean_mvvm.presentation.payouttype.PayoutTypeFormScreen
import com.creso.demo_clean_mvvm.presentation.payouttype.PayoutTypeListScreen

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
        composable(Screen.PayoutForm.route) {
            PayoutFormScreen(navController)
        }
        composable(Screen.CollectForm.route) {
            CollectFormScreen(navController)
        }
        composable(Screen.PayoutTypeForm.route) {
            PayoutTypeFormScreen(navController)
        }
        composable(Screen.CollectTypeForm.route) {
            CollectTypeFormScreen(navController)
        }
        composable(Screen.PayoutType.route) {
            PayoutTypeListScreen(navController)
        }
        composable(Screen.CollectType.route) {
            CollectTypeListScreen(navController)
        }
        // có thể thêm các route khác như CollectType, PayoutType ở đây
    }
}

sealed class Screen(val route: String, val icon: ImageVector, val label: String) {
    object Collect : Screen("collect", Icons.Default.Add, "Thu")
    object Payout : Screen("payout", Icons.Default.ShoppingCart, "Chi")
    object CollectForm : Screen("collect/form", Icons.Default.Info, "Tạo Thu")
    object PayoutForm : Screen("payout/form", Icons.Default.Info, "Tạo Chi")
    object CollectTypeForm : Screen("collecttype/form", Icons.Default.Info, "Tạo Loại Thu")
    object PayoutTypeForm : Screen("payouttype/form", Icons.Default.Info, "Tạo Loại Chi")
    object CollectType : Screen("collectlist", Icons.Default.Info, "Loại Thu")
    object PayoutType : Screen("payoutlist", Icons.Default.Info, "Loại Chi")
}