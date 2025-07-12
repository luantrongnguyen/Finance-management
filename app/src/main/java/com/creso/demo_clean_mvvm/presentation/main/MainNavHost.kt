package com.creso.demo_clean_mvvm.presentation.main

import androidx.annotation.DrawableRes
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.creso.demo_clean_mvvm.R
import com.creso.demo_clean_mvvm.presentation.collect.CollectFormScreen
import com.creso.demo_clean_mvvm.presentation.collect.CollectListScreen
import com.creso.demo_clean_mvvm.presentation.collecttype.CollectTypeFormScreen
import com.creso.demo_clean_mvvm.presentation.collecttype.CollectTypeListScreen
import com.creso.demo_clean_mvvm.presentation.payout.PayoutFormScreen
import com.creso.demo_clean_mvvm.presentation.payout.PayoutListScreen
import com.creso.demo_clean_mvvm.presentation.payouttype.PayoutTypeFormScreen
import com.creso.demo_clean_mvvm.presentation.payouttype.PayoutTypeListScreen
import com.creso.demo_clean_mvvm.presentation.setting.SettingScreen

@Composable
fun MainNavHost(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    NavHost(
        navController = navController,
        startDestination = Screen.Collect.route,
        modifier = modifier,
        enterTransition = {
            // you can change whatever you want transition
            EnterTransition.None
        },
        exitTransition = {
            // you can change whatever you want transition
            ExitTransition.None
        }
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
        composable(Screen.CollectForm.route, arguments = listOf(
            navArgument("id"){
                type = NavType.StringType
                nullable = true
                defaultValue = null
            }
        )) {
            CollectFormScreen(navController)
        }
        composable(Screen.PayoutTypeForm.route, arguments = listOf(
            navArgument("id"){
                type = NavType.StringType
                nullable = true
                defaultValue = null
            }
        )) {
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
        composable(Screen.Setting.route) {
            SettingScreen(navController)
        }
        // có thể thêm các route khác như CollectType, PayoutType ở đây
    }
}

sealed class Screen(val route: String, @DrawableRes val resId: Int, val label: String) {
    object Collect : Screen("collect", R.drawable.collect, "Thu")
    object Payout : Screen("payout",  R.drawable.payout, "Chi")
    object CollectForm : Screen("collect/form?id={id}",  R.drawable.collect, "Tạo Thu")
    object PayoutForm : Screen("payout/form?id={id}",  R.drawable.payout, "Tạo Chi")
    object CollectTypeForm : Screen("collecttype/form",  R.drawable.collect_type, "Tạo Loại Thu")
    object PayoutTypeForm : Screen("payouttype/form",  R.drawable.payout_type, "Tạo Loại Chi")
    object CollectType : Screen("collectlist",  R.drawable.collect_type, "Loại Thu")
    object PayoutType : Screen("payoutlist",  R.drawable.payout_type, "Loại Chi")
    object Setting : Screen("setting",  R.drawable.ic_launcher_foreground, "Setting")
}


