package com.example.quanlychitieu.ui.Views.home.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.quanlychitieu.Views.home.components.WeeklyFinanceBarChart

@Composable
fun BarChartColumn(
    data: Map<String, Long>,
    dates: List<String>
){
    Column(

    ) {
        Text(
            "Thống kê trong tuần",
            fontWeight = FontWeight.Bold,
            color = Color.Black,
            fontSize = 15.sp
        )
        Spacer(Modifier.height(10.dp))
        WeeklyFinanceBarChart(data = data, dates = dates)
    }
}