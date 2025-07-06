package com.creso.demo_clean_mvvm.presentation.payout

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
import com.creso.demo_clean_mvvm.domain.model.Payout
import com.creso.demo_clean_mvvm.domain.model.PayoutType
import com.creso.demo_clean_mvvm.presentation.payouttype.PayoutTypeViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PayoutFormScreen(
    navController: NavController,
    viewModel: PayoutViewModel = hiltViewModel(),
    viewModelPayoutType: PayoutTypeViewModel = hiltViewModel()
) {
    var amount by remember { mutableStateOf("") }
    var note by remember { mutableStateOf("") }
    var listType = viewModelPayoutType.PayoutTypes
    LaunchedEffect(Unit) {
        viewModelPayoutType.loadList()
    }
    var expanded by remember { mutableStateOf(false) }
    var selectedType by remember { mutableStateOf<PayoutType?>(null) }

    Text(text = "Loại", style = MaterialTheme.typography.bodyMedium)

    Scaffold(
        topBar = {
            TopAppBar(title = { Text("Thêm chi tiêu") })
        },
        floatingActionButton = {
            FloatingActionButton(onClick = {
                val payout = Payout(
                    id = 0,
                    amount = amount.toDoubleOrNull() ?: 0.0,
                    name = note,
                    date = System.currentTimeMillis(),
                    payoutType = selectedType?.id ?: return@FloatingActionButton  // đảm bảo đã chọn
                )
                viewModel.create(payout)
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
                label = { Text("Số tiền chi") },
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

            Text(text = "Loại", style = MaterialTheme.typography.bodyMedium)

            Box {
                OutlinedTextField(
                    value = selectedType?.name ?: "Chọn loại chi tiêu",
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
                    listType.forEach { type ->
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
