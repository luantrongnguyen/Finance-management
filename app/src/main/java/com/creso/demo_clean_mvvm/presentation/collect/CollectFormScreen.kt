package com.creso.demo_clean_mvvm.presentation.collect

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.creso.demo_clean_mvvm.domain.model.Collect
import com.creso.demo_clean_mvvm.domain.model.CollectType
import com.creso.demo_clean_mvvm.presentation.collecttype.CollectTypeViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CollectFormScreen(
    navController: NavController,
    viewModel: CollectViewModel = hiltViewModel(),
    viewModelPayoutType: CollectTypeViewModel = hiltViewModel()
) {
    var amount by remember { mutableStateOf("") }
    var note by remember { mutableStateOf("") }

    // Load CollectTypes from DB
    val typeList = viewModelPayoutType.collecTypes
    var expanded by remember { mutableStateOf(false) }
    var selectedType by remember { mutableStateOf<CollectType?>(null) }

    LaunchedEffect(Unit) {
        viewModel.loadList()
    }

    Scaffold(
        topBar = {
            TopAppBar(title = { Text("Thêm thu nhập") })
        },
        floatingActionButton = {
            FloatingActionButton(onClick = {
                val collect = Collect(
                    id = 0,
                    amount = amount.toDoubleOrNull() ?: 0.0,
                    name = note,
                    date = System.currentTimeMillis(),
                    collectType = selectedType?.id ?: return@FloatingActionButton
                )
                viewModel.create(collect)
                navController.popBackStack()
            }) {
                Icon(Icons.Default.Check, contentDescription = "Lưu")
            }
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .padding(16.dp)
        ) {
            OutlinedTextField(
                value = amount,
                onValueChange = { amount = it },
                label = { Text("Số tiền thu") },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(12.dp))

            OutlinedTextField(
                value = note,
                onValueChange = { note = it },
                label = { Text("Ghi chú") },
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(12.dp))

            Text(text = "Loại", style = MaterialTheme.typography.bodyMedium)

            Box {
                OutlinedTextField(
                    value = selectedType?.name ?: "Chọn loại thu",
                    onValueChange = {},
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable { expanded = true; viewModelPayoutType.loadList() },
                    enabled = false,
                    readOnly = true,
                    trailingIcon = {
                        Icon(Icons.Default.ArrowDropDown, contentDescription = "Dropdown")
                    }
                )

                DropdownMenu(
                    expanded = expanded,
                    onDismissRequest = { expanded = false }
                ) {
                    typeList.forEach { type ->
                        DropdownMenuItem(
                            text = { Text(type.name) },
                            onClick = {
                                selectedType = type
                                expanded = false
                            }
                        )
                    }
                }
            }
        }
    }
}
