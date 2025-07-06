package com.creso.demo_clean_mvvm.presentation.payouttype

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PayoutTypeListScreen(
    navController: NavController,
    viewModel: PayoutTypeViewModel = hiltViewModel()
) {
    val payoutTypes = viewModel.PayoutTypes

    LaunchedEffect(Unit) {
        viewModel.loadList()
    }

    Scaffold(
        topBar = {
            TopAppBar(title = { Text("Danh sách loại chi") })
        },
        floatingActionButton = {
            FloatingActionButton(onClick = {
                navController.navigate("payouttype/form")
            }) {
                Icon(Icons.Default.Add, contentDescription = "Lưu")
            }
        }
    ) { padding ->
        LazyColumn(contentPadding = padding) {
            items(payoutTypes.size) { i ->
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp),
                    elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
                ) {
                    Column(Modifier.padding(16.dp)) {
                        Text("Tên: ${payoutTypes[i].name}")
                    }
                }
            }
        }
    }
}
