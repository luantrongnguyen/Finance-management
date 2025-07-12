package com.creso.demo_clean_mvvm.presentation.collect

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.FloatingActionButtonDefaults
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.creso.demo_clean_mvvm.domain.model.Collect
import com.creso.demo_clean_mvvm.presentation.collecttype.CollectTypeViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CollectFormScreen(
    navController: NavController,
    viewModel: CollectViewModel = hiltViewModel(),
    viewModelCollectType: CollectTypeViewModel = hiltViewModel()
) {

    // Load CollectTypes from DB
    val typeList = viewModelCollectType.collecTypes
    var expanded by remember { mutableStateOf(false) }

    val collectId = navController.currentBackStackEntry
        ?.arguments?.getString("id")?.toIntOrNull()

    var isEditing by remember { mutableStateOf(false) }
    var collect by remember { mutableStateOf(Collect(0, "", 0.0, 0, 0)) }
    var amount by remember { mutableStateOf("") }
    var note by remember { mutableStateOf("") }
    var selectedType by remember { mutableStateOf<Int?>(null) }

    LaunchedEffect(collectId) {
        if (collectId != null) {
            viewModel.load(collectId)
            isEditing = true
        }
    }

    LaunchedEffect(viewModel.collect) {
        collect = viewModel.collect
        amount = collect.amount.toString()
        note = collect.name
        selectedType = collect.collectType
    }

    LaunchedEffect(Unit) {
        viewModelCollectType.loadList()
    }

    Scaffold(
        topBar = {
            TopAppBar(title = { Text("Thêm thu nhập") })
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    if (isEditing) {
                        val collect = Collect(
                            id = collect.id,
                            amount = amount.toDoubleOrNull() ?: 0.0,
                            name = note,
                            date = collect.date,
                            collectType = selectedType ?: return@FloatingActionButton
                        )
                        viewModel.edit(collect)
                    } else {
                        val collect = Collect(
                            id = 0,
                            amount = amount.toDoubleOrNull() ?: 0.0,
                            name = note,
                            date = System.currentTimeMillis(),
                            collectType = selectedType ?: return@FloatingActionButton
                        )
                        viewModel.create(collect)
                    }
                    navController.popBackStack()
                },
                shape = RoundedCornerShape(32.dp),
                containerColor = MaterialTheme.colorScheme.primary,
                contentColor = Color.White,
                elevation = FloatingActionButtonDefaults.elevation(defaultElevation = 6.dp),
            ) {
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
                    value = typeList.find { it.id == selectedType }?.name ?: "Chọn loại thu",
                    onValueChange = {},
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable { expanded = true; viewModelCollectType.loadList() },
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
                                selectedType = type.id
                                expanded = false
                            }
                        )
                    }
                }
            }
        }
    }
}
