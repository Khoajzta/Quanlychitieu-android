package com.example.quanlychitieu.Components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.quanlychitieu.domain.model.ThuNhapModel
import com.example.quanlychitieu.ui.theme.Dimens.RadiusLarge
import com.example.quanlychitieu.ui.theme.Dimens.RadiusXL
import com.example.quanlychitieu.ui.theme.Dimens.SpaceMedium
import formatCurrency

@Composable
fun CardThuNhap(
    thuNhap: ThuNhapModel,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(RadiusXL))
            .background(
                Brush.linearGradient(
                    colors = listOf(
                        Color(0xFF5AEA6E),  // Mint pastel
                        Color(0xFFD3FFE0)   // Nhạt dần về cuối
                    )
                )
            )

            .padding(horizontal = 16.dp, vertical = 14.dp)
    ) {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ) {
            Row(
                horizontalArrangement = Arrangement.spacedBy(SpaceMedium),
                verticalAlignment = Alignment.CenterVertically
            ) {

//                Box(
//                    modifier = Modifier
//                        .size(42.dp)
//                        .clip(CircleShape)
//                        .background(Color.White.copy(alpha = 0.15f)),
//                    contentAlignment = Alignment.Center
//                ) {
//                    Icon(
//                        Icons.Default.AttachMoney,
//                        contentDescription = null,
//                        tint = Color.White.copy(alpha = 0.9f),
//                        modifier = Modifier.size(24.dp)
//                    )
//                }

                Box(
                    modifier = Modifier
                        .wrapContentWidth()
                        .clip(RoundedCornerShape(RadiusLarge))
                        .background(color = Color.White.copy(0.5f))
                        .padding(vertical = 3.dp, horizontal = 15.dp),
                    contentAlignment = Alignment.Center
                ){
                    Column {
                        Text(
                            text = thuNhap.tenThuNhap,
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color.Black
                        )
                        Text(
                            text = thuNhap.ngayThuNhap,
                            fontSize = 14.sp,
                            color = Color.Black.copy(alpha = 0.7f)
                        )
                    }
                }

            }

            Text(
                text = "+${formatCurrency(thuNhap.soTien)}",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF008F3C) // Xanh mint để nổi bật
            )
        }
    }
}

@Composable
@Preview
fun CardThuNhapPreview() {
    CardThuNhap(thuNhap = ThuNhapModel(maThuNhap = 0, tenThuNhap = "tiền cơm", soTien = 1000000, maThang = 1,  ngayThuNhap = "23/09/2025"))
}
