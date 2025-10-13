package com.example.quanlychitieu.Components

import androidx.compose.foundation.background
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
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.quanlychitieu.models.KhoanChiModel
import com.example.quanlychitieu.ui.theme.Dimens.RadiusLarge
import com.example.quanlychitieu.ui.theme.Dimens.RadiusXL
import formatCurrency

@Composable
fun CardKhoanChiDetail(
    modifier: Modifier = Modifier,
    item: KhoanChiModel,
    onClick: ()-> Unit = {}
){
    val spentPercentage = remember(item.so_tien_du_kien, item.tong_tien_da_chi) {
        (item.tong_tien_da_chi.toFloat() / item.so_tien_du_kien.toFloat()).coerceIn(0f, 1f)
    }

    var backgroundGradientColors =
        when(item.mausac) {
            "red" -> listOf(Color(0xFFE57373),Color(0xFFF06292).copy(alpha = 0.35f))
            "blue" -> listOf(Color(0xFF64B5F6),Color(0xFF4FC3F7).copy(alpha = 0.35f))
            "green" -> listOf(Color(0xFF81C784),Color(0xFF4DB6AC).copy(alpha = 0.35f))
            "yellow" -> listOf(Color(0xFFFFB74D),Color(0xFFFF8A65).copy(alpha = 0.35f))
            else -> listOf(Color(0xFFA0C7E1),Color(0xFFB461CC).copy(alpha = 0.35f))
        }

    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(120.dp)
            .clip(RoundedCornerShape(RadiusXL))
            .background(Brush.horizontalGradient(colors = backgroundGradientColors))
            .padding(16.dp)
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.Start
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box(
                    modifier = Modifier
                        .width(130.dp)
                        .clip(RoundedCornerShape(RadiusLarge))
                        .background(color = Color.White.copy(0.5f)),
                    contentAlignment = Alignment.Center
                ){
                    Text(
                        text = "${item.emoji} ${item.ten_khoanchi}",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.Black,
                        lineHeight = 20.sp,
                        modifier = Modifier.padding(10.dp)
                    )
                }

                Text(
                    text = "Số lượng chi tiêu: ${item.so_luong_chi_tieu}",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black,
                    lineHeight = 20.sp,
                    modifier = Modifier.padding(10.dp)
                )
            }



            Spacer(modifier = Modifier.height(10.dp))

            Row(
                modifier = Modifier
                    .fillMaxWidth(),

                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = "Dự kiến: ${formatCurrency(item.so_tien_du_kien)}",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Medium,
                    color = Color.White.copy(alpha = 0.9f)
                )


                Text(
                    text = "Đã dùng: ${formatCurrency(item.tong_tien_da_chi)}",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Medium,
                    color = Color.White.copy(alpha = 0.9f)
                )
            }

            Spacer(modifier = Modifier.height(10.dp))

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(10.dp)
                    .clip(RoundedCornerShape(50))
                    .background(Color.White.copy(alpha = 0.2f))
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth(spentPercentage)
                        .fillMaxHeight()
                        .background(
                            Brush.horizontalGradient(
                                colors = listOf(
                                    Color(0xFFFFC371),
                                    Color(0xFFFF5F6D)
                                )
                            )
                        )
                )
            }
        }
    }
}

@Composable
@Preview
fun CardKhoanChiDetailPreview(){

    CardKhoanChiDetail(
        modifier = Modifier,
        KhoanChiModel(
        id = 1,
        ten_khoanchi = "Tiền ăn",
        id_nguoidung = 1,
        mausac = "red",
        ngay_batdau = "16-02-2025",
        ngay_ketthuc = "16-02-2025",
        so_tien_du_kien = 3000000,
        tong_tien_da_chi = 500000,
        so_luong_chi_tieu = 5,
        emoji = "🤣"
    ))
}