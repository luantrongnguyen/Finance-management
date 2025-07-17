package com.creso.demo_clean_mvvm.presentation.components
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.creso.demo_clean_mvvm.presentation.utils.CurrencyUtil
import java.text.SimpleDateFormat
import java.util.*

@Composable
fun TransactionCard(
  modifier: Modifier = Modifier,
  logo: @Composable () -> Unit = { Icon(Icons.Default.AccountCircle, contentDescription = "Transaction Logo", tint = Color.Red) }, // Logo mặc định
  title: String = "Unknown Transaction", // Tên giao dịch
  amount: Double, // Số tiền
  date: Long, // Timestamp
  onLongPress: () -> Unit = {}, // Hành vi khi long press
  locale: Locale = Locale("vi", "VN"), // Locale để lấy đơn vị tiền tệ
  selectedTransaction: Any? = null, // Đối tượng giao dịch được chọn
  types: List<Any>? = null, // Danh sách loại (nếu cần)
  collectType: Any? = null, // Loại giao dịch (nếu cần)
  isIncome: Boolean = false // Thêm tham số để chỉ thu hay chi (false = chi, true = thu)
) {
  Card(
    modifier = modifier
      .fillMaxWidth()
      .padding(8.dp)
      .pointerInput(Unit) {
        detectTapGestures(
          onLongPress = {
            onLongPress()
          }
        )
      },
    elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
    shape = RoundedCornerShape(8.dp)
  ) {
    Row(
      modifier = Modifier
        .padding(16.dp)
        .fillMaxWidth(),
      verticalAlignment = Alignment.CenterVertically,
      horizontalArrangement = Arrangement.SpaceBetween
    ) {
      // Phần logo và thông tin
      Row(verticalAlignment = Alignment.CenterVertically) {
        logo()
        Spacer(modifier = Modifier.width(8.dp))
        Column {
          Text(
            text = title,
            style = MaterialTheme.typography.titleMedium,
            color = MaterialTheme.colorScheme.onSurface,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
          )
          Text(
            text = "Today, ${SimpleDateFormat("h:mm a", Locale.US).format(Date(date))}",
            style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
          )
        }
      }

      // Phần số tiền với màu sắc dựa trên isIncome
      Text(
        text = "${if (!isIncome && amount > 0) "-" else if (isIncome && amount < 0) "+" else ""}${CurrencyUtil.formatCurrency(Math.abs(amount))} ${CurrencyUtil.getCurrencySymbol(locale)}",
        style = MaterialTheme.typography.titleMedium,
        color = when {
          !isIncome && amount != 0.0 -> MaterialTheme.colorScheme.error // Chi (đỏ)
          isIncome && amount != 0.0 -> MaterialTheme.colorScheme.primary // Thu (xanh)
          else -> MaterialTheme.colorScheme.onSurface // Mặc định
        }
      )
    }
  }
}