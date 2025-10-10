package com.example.quanlychitieu.Views.home.components

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kotlin.math.abs

@Composable
fun WeeklyFinanceBarChart(
    modifier: Modifier = Modifier,
    data: Map<String, Int>,              // dữ liệu thứ → số tiền
    dates: List<String> = listOf("07", "08", "09", "10", "11", "12", "13") // ngày tương ứng
) {
    val maxAmount = (data.values.maxOfOrNull { abs(it) } ?: 0).coerceAtLeast(1)

    val dayKeys = listOf("T2", "T3", "T4", "T5", "T6", "T7", "CN")

    Column(
        modifier = Modifier
            .border(2.dp, color = Color(0xFF1C94D5), shape = RoundedCornerShape(15.dp))
            .padding(vertical = 12.dp)
    ) {
        Canvas(
            modifier = modifier
                .fillMaxWidth()
                .height(220.dp)
                .padding(horizontal = 16.dp)
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

            // Cột
            dayKeys.forEachIndexed { index, key ->
                val amount = data[key]
                if (amount != null && amount != 0) {
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
                }
            }
        }

        // Hiển thị thứ + ngày
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 8.dp, start = 8.dp, end = 8.dp),
            horizontalArrangement = Arrangement.SpaceAround
        ) {
            dayKeys.forEachIndexed { index, key ->
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Text(
                        text = key, // thứ
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
        "T2" to 200000,
        "T3" to -80000,
        "T4" to 200000,
        "T5" to 50000,
        "T6" to -50000,
        "T7" to -50000,
        "CN" to 50000,
    )
    val weekDates = listOf("07", "08", "09", "10", "11", "12", "13")

    WeeklyFinanceBarChart(data = weeklyData, dates = weekDates)
}





