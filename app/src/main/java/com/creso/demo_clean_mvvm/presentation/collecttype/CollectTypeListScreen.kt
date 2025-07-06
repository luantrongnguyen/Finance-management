package com.creso.demo_clean_mvvm.presentation.collecttype

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CollectTypeListScreen(
    navController: NavController,
    viewModel: CollectTypeViewModel = hiltViewModel()
) {
    val collectTypes = viewModel.collecTypes

    LaunchedEffect(Unit) {
        viewModel.loadList()
    }

    Scaffold(
        topBar = {
            TopAppBar(title = { Text("Danh sách loại thu") })
        },
        floatingActionButton = {
            FloatingActionButton(onClick = {
                navController.navigate("collecttype/form")
            }) {
                Icon(Icons.Default.Add, contentDescription = "Thêm")
            }
        }
    ) { padding ->
        LazyColumn(contentPadding = padding) {
            items(collectTypes.size) { i ->
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp),
                    elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
                ) {
                    Column(Modifier.padding(16.dp)) {
                        Text("Tên: ${collectTypes[i].name}")
                    }
                }
            }
        }
    }
}
