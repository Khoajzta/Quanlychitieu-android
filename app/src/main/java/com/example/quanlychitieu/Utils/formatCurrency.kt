import java.text.NumberFormat
import java.util.Locale
import kotlin.math.abs

fun formatCurrency(amount: Long): String {
    val formatter = NumberFormat.getInstance(Locale("vi", "VN"))
    return formatter.format(amount) + "₫"
}

fun formatMoneyShort(amount: Int): String {
    val absValue = abs(amount)
    val suffix = when {
        absValue >= 1_000_000 -> String.format("%.1fM", absValue / 1_000_000.0)
        absValue >= 1_000 -> String.format("%.0fk", absValue / 1_000.0)
        else -> absValue.toString()
    }
    return if (amount < 0) "-$suffix" else "+$suffix"
}
