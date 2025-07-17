package com.creso.demo_clean_mvvm.presentation.utils

import java.text.DecimalFormat
import java.text.DecimalFormatSymbols
import java.util.Currency
import java.util.Locale

object CurrencyUtil {
  fun formatCurrency(amount: Double): String {
    val symbols = DecimalFormatSymbols(Locale("vi", "VN")).apply {
      groupingSeparator = ','
      decimalSeparator = '.'
    }
    val formatter = DecimalFormat("#,###", symbols)
    return formatter.format(amount)
  }

  fun parseCurrency(formattedAmount: String): Double {
    val cleanString = formattedAmount.replace(",", "").replace(" ", "")
    if(cleanString.isNotBlank())
      return cleanString.toDouble()
    return 0.00
  }


  fun getCurrencySymbol(locale: Locale): String {
    return Currency.getInstance(locale).currencyCode
  }

}