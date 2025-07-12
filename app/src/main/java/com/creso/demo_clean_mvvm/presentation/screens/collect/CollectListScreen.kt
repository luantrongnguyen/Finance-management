package com.creso.demo_clean_mvvm.presentation.screens.collect

import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.BasicAlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.creso.demo_clean_mvvm.domain.model.Collect
import com.creso.demo_clean_mvvm.presentation.screens.collecttype.CollectTypeViewModel
import com.creso.demo_clean_mvvm.presentation.components.CustomSnackbar
import kotlinx.coroutines.launch
import java.util.Date

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CollectListScreen(
    navController: NavController,
    viewModel: CollectViewModel = hiltViewModel(),
    typeViewModel: CollectTypeViewModel = hiltViewModel()
) {
    val collects = viewModel.collects
    val types = typeViewModel.collecTypes
    var selectedCollect by remember { mutableStateOf<Collect?>(null)}
    var isDialogOpen by remember { mutableStateOf<Boolean>(false) }
    val snackbarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()

    LaunchedEffect(Unit) {
        viewModel.loadList()
        typeViewModel.loadList()
    }
    Box(modifier = Modifier.fillMaxSize()) {
        Scaffold(
            topBar = {
                TopAppBar(title = { Text("Danh sách thu") })
            },
            floatingActionButton = {
                FloatingActionButton(
                    onClick = {
                        navController.navigate("collect/form")
                    },
                    shape = RoundedCornerShape(32.dp),
                    containerColor = MaterialTheme.colorScheme.primary,
                    contentColor = Color.White,
                    elevation = FloatingActionButtonDefaults.elevation(defaultElevation = 6.dp),
                ) {
                    Icon(Icons.Default.Add, contentDescription = "Lưu")
                }
            },
        ) { padding ->
            LazyColumn(contentPadding = padding) {
                items(collects.size) { i ->
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(8.dp)
                            .pointerInput(Unit) {
                                detectTapGestures(
                                    onLongPress = {
                                        selectedCollect = collects[i]
                                        isDialogOpen = true
                                    }
                                )
                            },
                        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),

                        ) {

                        Column(Modifier.padding(16.dp)) {
                            Text("Số tiền thu: ${collects[i].amount}")
                            Text("Ghi chú: ${collects[i].name}")
                            Text("Ngày: ${Date(collects[i].date)}")
                            Text("Loại: ${types.find { it.id == collects[i].collectType }?.name}")
                        }
                    }
                }
            }
            if (isDialogOpen && selectedCollect != null) {
                BasicAlertDialog(

                    onDismissRequest = { isDialogOpen = false },
                    content = {
                        Surface(
                            shape = RoundedCornerShape(16.dp), color = Color.White,
                        ) {

                            Column(
                                modifier = Modifier.padding(bottom = 16.dp, top = 16.dp),
//                        verticalArrangement = Arrangement.Top,
                                horizontalAlignment = Alignment.CenterHorizontally
                            ) {
                                Text(
                                    text = selectedCollect!!.name,
                                    color = MaterialTheme.colorScheme.surface
                                )
                                Spacer(modifier = Modifier.height(16.dp))
                                Button(
                                    onClick = {
                                        viewModel.delete(selectedCollect!!.id)
                                        isDialogOpen = false
                                        scope.launch {
                                            val result = snackbarHostState.showSnackbar(
                                                message = "Deleted",
                                                actionLabel = "Undo",
                                                withDismissAction = true,
                                                duration = SnackbarDuration.Short
                                            )
//                                        when (result) {
//                                            SnackbarResult.ActionPerformed -> {
//                                                //Do Something
//                                            }
//                                            SnackbarResult.Dismissed -> {
//                                                //Do Something
//                                            }
//                                        }
                                        }
                                    },
                                    colors = ButtonDefaults.buttonColors(
                                        containerColor = MaterialTheme.colorScheme.errorContainer,
                                        contentColor = MaterialTheme.colorScheme.error
                                    ),
                                    modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp)
                                ) {
                                    Text(
                                        text = "Xóa",
                                        color = MaterialTheme.colorScheme.onSurface
                                    )
                                }
                                Spacer(modifier = Modifier.height(16.dp))
                                Button(
                                    onClick = {
                                        navController.navigate("collect/form?id=${selectedCollect!!.id}")
                                        isDialogOpen = false
                                    },
                                    colors = ButtonDefaults.buttonColors(
                                        containerColor = MaterialTheme.colorScheme.primaryContainer,
                                        contentColor = MaterialTheme.colorScheme.primary
                                    ),
                                    modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp)
                                ) {
                                    Text(
                                        text = "Sửa",
                                        color = MaterialTheme.colorScheme.onSurface
                                    )
                                }
                            }
                        }

                    }
                )
            }
        }
        SnackbarHost(
            hostState = snackbarHostState,
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(16.dp) // space from bottom (same as FAB)
                .zIndex(1f), // overlay above FAB
            snackbar = { CustomSnackbar(it) }
        )
    }

}
