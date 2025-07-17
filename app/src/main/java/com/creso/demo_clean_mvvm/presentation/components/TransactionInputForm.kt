package com.creso.demo_clean_mvvm.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import com.creso.demo_clean_mvvm.domain.model.Collect
import com.creso.demo_clean_mvvm.domain.model.CollectType
import com.creso.demo_clean_mvvm.domain.model.Payout
import com.creso.demo_clean_mvvm.domain.model.PayoutType
import com.creso.demo_clean_mvvm.presentation.screens.collecttype.CollectTypeViewModel
import com.creso.demo_clean_mvvm.presentation.screens.payouttype.PayoutTypeViewModel
import java.util.Locale

@Composable
fun TransactionInputForm(
  modifier: Modifier,
  amount: String,
  onAmountChange: (String) -> Unit,
  note: String,
  onNoteChange: (String) -> Unit,
  selectedTypeId: Int?,
  onTypeSelected: (Int) -> Unit,
  viewModelType: ViewModel? = null, // ViewModel để load danh sách loại (nếu cần)
  onExpandChange: (Boolean) -> Unit = {}, // Callback khi mở dropdown
  isExpanded: Boolean = false, // Trạng thái mở rộng dropdown
  typeList: List<*>,
  isIncome: Boolean = false // Thêm tham số để chỉ thu hay chi (false = chi, true = thu)
) {

  Surface(
    modifier = modifier,
    shape = RoundedCornerShape(12.dp),
    tonalElevation = 4.dp
  ) {
    Column(
      modifier = Modifier
        .padding(16.dp)
        .fillMaxWidth(),
      verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
      // Số tiền thu
      CurrencyTextField(
        label = if (isIncome) "Số tiền thu" else "Số tiền chi",
        value = amount,
        onValueChange = onAmountChange,
        modifier = Modifier
          .fillMaxWidth()
          .background(MaterialTheme.colorScheme.surfaceVariant, RoundedCornerShape(8.dp))
      )

      // Ghi chú
      TextField(
        value = note,
        onValueChange = onNoteChange,
        label = { Text("Ghi chú") },
        modifier = Modifier
          .fillMaxWidth()
          .background(MaterialTheme.colorScheme.surfaceVariant, RoundedCornerShape(8.dp)),
        shape = RoundedCornerShape(8.dp),
        colors = OutlinedTextFieldDefaults.colors(
          focusedBorderColor = MaterialTheme.colorScheme.primary,
          unfocusedBorderColor = MaterialTheme.colorScheme.outline
        )
      )

      // Loại
      Text(
        text = "Loại",
        style = MaterialTheme.typography.bodyMedium,
        color = MaterialTheme.colorScheme.onSurface,
        textAlign = TextAlign.Start
      )

      var expanded by remember { mutableStateOf(isExpanded) }
      Box {
        OutlinedTextField(
          value =
          if (isIncome)
            (viewModelType as CollectTypeViewModel).collecTypes.find { it.id == selectedTypeId }?.name ?: "Chọn loại thu"
          else
            (viewModelType as PayoutTypeViewModel).PayoutTypes.find { it.id == selectedTypeId }?.name ?: "Chọn loại chi",
          onValueChange = {},
          modifier = Modifier
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.surfaceVariant, RoundedCornerShape(8.dp))
            .clickable {
              expanded = true
              onExpandChange(true)
              if (isIncome)
                (viewModelType as CollectTypeViewModel).loadList()
              else
                (viewModelType as PayoutTypeViewModel).loadList()
            },
          enabled = false,
          readOnly = true,
          shape = RoundedCornerShape(8.dp),
          trailingIcon = {
            Icon(
              imageVector = Icons.Default.ArrowDropDown,
              contentDescription = "Dropdown",
              tint = MaterialTheme.colorScheme.onSurfaceVariant
            )
          },
          colors = OutlinedTextFieldDefaults.colors(
            focusedBorderColor = MaterialTheme.colorScheme.primary,
            unfocusedBorderColor = MaterialTheme.colorScheme.outline
          )
        )

        DropdownMenu(
          expanded = expanded,
          onDismissRequest = {
            expanded = false
            onExpandChange(false)
          },
          modifier = Modifier
            .background(MaterialTheme.colorScheme.surface)
            .padding(4.dp)
        ) {
          typeList.forEach { type ->
            DropdownMenuItem(
              text = {
                Text(
                  text = if (isIncome)
                    (type as CollectType).name
                  else
                    (type as PayoutType).name,
                  style = MaterialTheme.typography.bodyMedium,
                  color = MaterialTheme.colorScheme.onSurface
                )
              },
              onClick = {
                if (isIncome)
                  onTypeSelected((type as CollectType).id)
                else
                  onTypeSelected((type as PayoutType).id)
                expanded = false
                onExpandChange(false)
              },
              modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp)
            )
          }
        }
      }
    }
  }
}