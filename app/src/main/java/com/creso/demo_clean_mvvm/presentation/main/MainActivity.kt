package com.creso.demo_clean_mvvm.presentation.main

import android.R
import android.content.IntentFilter
import android.net.ConnectivityManager
import android.os.Bundle
import android.view.WindowManager
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.AlignmentLine
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.WindowInsetsControllerCompat
import androidx.navigation.compose.rememberNavController
import com.creso.demo_clean_mvvm.data.receiver.NetworkReceiver
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyAppTheme  {
                val navController = rememberNavController()
                val context = LocalContext.current
                val isConnected = remember { mutableStateOf(true) }
                val snackbarHostState = remember { SnackbarHostState() }
                val scope = rememberCoroutineScope()
                DisposableEffect(Unit) {
                    val receiver = NetworkReceiver { connected ->
                        isConnected.value = connected
                        scope.launch {
                            snackbarHostState.showSnackbar(connected.toString())
                        }
                    }
                    val intentFilter = IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION)
                    context.registerReceiver(receiver, intentFilter)
                    onDispose {
                        context.unregisterReceiver(receiver)
                    }
                }
                Scaffold(
                    bottomBar =
                    { BottomBar(navController = navController) },

                    snackbarHost = {
                        SnackbarHost(hostState = snackbarHostState)
                    },
                ) { padding ->
                    MainNavHost(navController = navController, modifier = Modifier.padding(padding))
                }
            }
        }
    }
}
@Preview(widthDp = 100, heightDp = 200)
@Composable
fun SimpleComposablePreview() {
    MainActivity()
}