package com.creso.demo_clean_mvvm.presentation.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextRange
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import com.creso.demo_clean_mvvm.presentation.utils.CurrencyUtil

@Composable
fun CurrencyTextField(
  label: String,
  value: String,
  onValueChange: (String) -> Unit,
  modifier: Modifier = Modifier
) {
  // Internal state for TextFieldValue to manage cursor position
  var textFieldValue by remember {
    mutableStateOf(TextFieldValue(text = value))
  }

  // Sync internal TextFieldValue with external String value
  LaunchedEffect(value) {
    if (value != textFieldValue.text) {
      textFieldValue = TextFieldValue(
        text = if(value.isNotBlank()) CurrencyUtil.formatCurrency(value.toDouble()) else "",
        selection = TextRange(value.length) // Place cursor at the end
      )
    }
  }

  TextField(
    value = textFieldValue,
    onValueChange = { newTextFieldValue ->
      val newValue = newTextFieldValue.text
      var mValue = newValue
      if (newValue.toDoubleOrNull() == 0.00) {
        mValue = ""
      }
      CurrencyUtil.parseCurrency(mValue).let { doubleValue ->
        if(doubleValue != 0.00) {
          val formattedValue = CurrencyUtil.formatCurrency(doubleValue)
          val newCursorPosition = calculateCursorPosition(
            oldValue = newValue,
            newValue = formattedValue,
            oldCursorPosition = newTextFieldValue.selection.start
          )
          textFieldValue = TextFieldValue(
            text = formattedValue,
            selection = TextRange(newCursorPosition.coerceIn(0, formattedValue.length))
          )
          onValueChange(formattedValue)
        }else
          onValueChange("")
      }
    },
    label = { Text(label) },
    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
    modifier = modifier
  )
}

fun calculateCursorPosition(
  oldValue: String,
  newValue: String,
  oldCursorPosition: Int
): Int {
  // Count commas before the cursor in old and new values
  val oldCommasBeforeCursor = oldValue.take(oldCursorPosition).count { it == ',' }
  val mNewValue = oldCursorPosition + (newValue.length - oldValue.length)

  if (mNewValue >= 0) {
    val newCommasBeforeCursor = newValue.take(mNewValue).count { it == ',' }
    return oldCursorPosition + (newCommasBeforeCursor - oldCommasBeforeCursor)
  } else
    return 0
  // Adjust cursor position by the difference in commas
}
