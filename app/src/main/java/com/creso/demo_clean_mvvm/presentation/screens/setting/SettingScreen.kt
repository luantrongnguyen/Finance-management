package com.creso.demo_clean_mvvm.presentation.screens.setting

import android.content.IntentFilter
import android.net.ConnectivityManager
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.creso.demo_clean_mvvm.data.receiver.NetworkReceiver
import com.creso.demo_clean_mvvm.presentation.screens.payouttype.PayoutTypeViewModel

@Composable
fun SettingScreen(
    navController: NavController,
) {
    val context = LocalContext.current
    val isConnected = remember { mutableStateOf(true) }

    DisposableEffect(Unit) {
        val receiver = NetworkReceiver { connected ->
            isConnected.value = connected
        }
        val intentFilter = IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION)
        context.registerReceiver(receiver, intentFilter)
        onDispose {
            context.unregisterReceiver(receiver)
        }
    }
    Scaffold { padding ->
        Column(modifier = Modifier.padding(padding)) {
            Text(
                text = if(isConnected.value) "Kết nối mạng" else "Không có kết nối",
                modifier = Modifier.fillMaxWidth(),
                color = MaterialTheme.colorScheme.onSurface,
            )
        }
    }
}