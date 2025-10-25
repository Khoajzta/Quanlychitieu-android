package com.example.quanlychitieu.Views.home.components

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Paint
import androidx.compose.ui.graphics.nativeCanvas
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.quanlychitieu.ui.theme.Dimens.SpaceLarge
import com.example.quanlychitieu.ui.theme.Dimens.SpaceMedium
import formatMoneyShort
import kotlin.math.abs

@Composable
fun WeeklyFinanceBarChart(
    modifier: Modifier = Modifier,
    data: Map<String, Long>,
    dates: List<String>
) {
    val maxAmount = (data.values.maxOfOrNull { abs(it) } ?: 0).coerceAtLeast(1)
    val dayKeys = listOf("T2", "T3", "T4", "T5", "T6", "T7", "CN")

    // Chiều cao động: giá trị càng lớn, cột càng cao, nhưng có giới hạn
    val dynamicHeight = remember(maxAmount) {
        val baseHeight = 120.dp
        val extraHeight = (maxAmount / 1_000_000).coerceAtMost(5) * 20 // thêm 20dp mỗi triệu
        (baseHeight.value + extraHeight).dp.coerceAtMost(280.dp)       // giới hạn tối đa
    }

    Column(
        modifier = modifier
            .border(2.dp, color = Color(0xFF1C94D5), shape = RoundedCornerShape(15.dp))
            .padding(vertical = 12.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Canvas(
            modifier = Modifier
                .fillMaxWidth()
                .height(dynamicHeight)
                .padding(horizontal = SpaceMedium, vertical = SpaceLarge)
        ) {
            val barWidth = size.width / (dayKeys.size * 2)
            val centerY = size.height / 2

            // Đường giữa
            drawLine(
                color = Color.Gray.copy(alpha = 0.3f),
                start = Offset(0f, centerY),
                end = Offset(size.width, centerY),
                strokeWidth = 2f
            )

            dayKeys.forEachIndexed { index, key ->
                val amount = data[key]
                if (amount != null && amount != 0L) {
                    val barHeight = (size.height / 2) * (abs(amount).toFloat() / maxAmount)
                    val barX = (index * 2 + 1) * barWidth

                    val brush = if (amount > 0) {
                        Brush.verticalGradient(
                            colors = listOf(Color(0xFF81C784), Color(0xFF2E7D32)),
                            startY = centerY - barHeight,
                            endY = centerY
                        )
                    } else {
                        Brush.verticalGradient(
                            colors = listOf(Color(0xFFE57373), Color(0xFFC62828)),
                            startY = centerY,
                            endY = centerY + barHeight
                        )
                    }

                    drawRoundRect(
                        brush = brush,
                        topLeft = Offset(barX - barWidth / 2, centerY - if (amount > 0) barHeight else 0f),
                        size = Size(barWidth, barHeight),
                        cornerRadius = CornerRadius(20f, 20f)
                    )

                    // Hiển thị giá trị
                    val prefix = if (amount > 0) "+" else "-"
                    val displayAmount =
                        if (abs(amount) >= 1000) "$prefix${abs(amount) / 1000}k"
                        else "$prefix$amount"

                    val textPaint = Paint().asFrameworkPaint().apply {
                        isAntiAlias = true
                        textSize = 30f
                        color = if (amount > 0) android.graphics.Color.parseColor("#2E7D32")
                        else android.graphics.Color.parseColor("#C62828")
                        textAlign = android.graphics.Paint.Align.CENTER
                    }

                    val textY = if (amount > 0) {
                        centerY - barHeight - 8
                    } else {
                        centerY + barHeight + 28
                    }

                    drawContext.canvas.nativeCanvas.drawText(
                        displayAmount,
                        barX,
                        textY,
                        textPaint
                    )
                }
            }
        }

        // Nhãn thứ + ngày
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 8.dp, start = 8.dp, end = 8.dp),
            horizontalArrangement = Arrangement.SpaceAround
        ) {
            dayKeys.forEachIndexed { index, key ->
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Text(
                        text = key,
                        style = MaterialTheme.typography.labelMedium,
                        color = Color.Gray
                    )
                    Text(
                        text = dates.getOrNull(index) ?: "",
                        style = MaterialTheme.typography.labelSmall,
                        color = Color.DarkGray
                    )
                }
            }
        }
    }
}











@Composable
@Preview
fun PreviewWeeklyFinanceChart() {
    val weeklyData = mapOf(
        "T2" to 200000L,
        "T3" to -80000L,
        "T4" to 200000L,
        "T5" to 50000L,
        "T6" to -50000L,
        "T7" to -50000L,
        "CN" to 50000L,
    )
    val weekDates = listOf("07", "08", "09", "10", "11", "12", "13")

    WeeklyFinanceBarChart(data = weeklyData, dates = weekDates)
}





