package com.creso.demo_clean_mvvm.presentation.screens.payout

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
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.creso.demo_clean_mvvm.domain.model.Collect
import com.creso.demo_clean_mvvm.domain.model.Payout
import com.creso.demo_clean_mvvm.domain.model.PayoutType
import com.creso.demo_clean_mvvm.presentation.components.CurrencyTextField
import com.creso.demo_clean_mvvm.presentation.components.TransactionInputForm
import com.creso.demo_clean_mvvm.presentation.screens.payouttype.PayoutTypeViewModel
import com.creso.demo_clean_mvvm.presentation.utils.CurrencyUtil
import kotlinx.coroutines.launch
import java.util.Locale

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PayoutFormScreen(
  navController: NavController,
  viewModel: PayoutViewModel = hiltViewModel(),
  viewModelPayoutType: PayoutTypeViewModel = hiltViewModel()
) {
  val typeList = viewModelPayoutType.PayoutTypes
  var isEditing by remember { mutableStateOf(false) }
  var payout by remember { mutableStateOf(Payout(0, "", 0.0, 0, 0)) }
  var amount by remember { mutableStateOf("") }
  var note by remember { mutableStateOf("") }
  var selectedType by remember { mutableStateOf<Int?>(null) }

  val collectId = navController.currentBackStackEntry?.arguments?.getString("id")?.toIntOrNull()
  val snackbarHostState = remember { SnackbarHostState() }
  LaunchedEffect(Unit) {
    viewModelPayoutType.loadList()
  }

  LaunchedEffect(collectId) {
    if (collectId != null) {
      viewModel.load(collectId)
      isEditing = true
    }
  }

  LaunchedEffect(viewModel.payout) {
    payout = viewModel.payout
    amount = payout.amount.toString()
    note = payout.name
    selectedType = payout.payoutType
  }

  var expanded by remember { mutableStateOf(false) }

  Text(text = "Loại", style = MaterialTheme.typography.bodyMedium)

  Scaffold(
    topBar = {
      TopAppBar(title = { Text("Thêm chi tiêu") })
    },
    snackbarHost = {
      SnackbarHost(hostState = snackbarHostState)
    },
    floatingActionButton = {
      FloatingActionButton(
        onClick = {
          val payout = Payout(
            id = if (isEditing) payout.id else 0,
            amount = CurrencyUtil.parseCurrency(amount),
            name = note,
            date = if (isEditing) payout.date else System.currentTimeMillis(),
            payoutType = selectedType ?: return@FloatingActionButton
          )
          if (isEditing) viewModel.edit(payout) else viewModel.create(payout)

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
    TransactionInputForm(
      modifier = Modifier.padding(padding),
      amount = amount,
      onAmountChange = { amount = it },
      note = note,
      onNoteChange = { note = it },
      selectedTypeId = selectedType,
      onTypeSelected = { selectedType = it },
      typeList = typeList,
      viewModelType = viewModelPayoutType, // Giả sử viewModel có loadList()
      onExpandChange = { /* Xử lý khi mở dropdown */ },
      isExpanded = false,
      isIncome = false,
    )
  }
}
