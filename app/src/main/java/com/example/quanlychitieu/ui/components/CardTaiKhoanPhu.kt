package com.example.quanlychitieu.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.quanlychitieu.domain.model.TaiKhoanModel
import com.example.quanlychitieu.ui.theme.Dimens.RadiusXL
import formatCurrency

@Composable
fun CardTaikhoanPhu(
    modifier: Modifier = Modifier,
    taikhoan: TaiKhoanModel
) {
    Box(
        modifier = modifier
            .height(200.dp)
            .width(370.dp)
            .shadow(4.dp, RoundedCornerShape(RadiusXL))
            .background(
                brush = Brush.linearGradient(listOf(Color(0xFF4CAF50), Color(0xFF81C784))),
                shape = RoundedCornerShape(RadiusXL)
            )
            .clickable { /* TODO: xử lý khi bấm vào card */ }
            .padding(16.dp)
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(6.dp),
            horizontalAlignment = Alignment.Start,
            modifier = Modifier.fillMaxWidth()
        ) {
            // Tên tài khoản
            Text(
                text = taikhoan.ten_taikhoan,
                style = TextStyle(
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )
            )

            // Số dư (định dạng tiền)
            Text(
                text = "${formatCurrency(taikhoan.so_du)}",
                style = TextStyle(
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Medium,
                    color = Color.White
                )
            )
            // Mô tả (nếu có)
            if (taikhoan.mo_ta.isNotEmpty()) {
                Text(
                    text = taikhoan.mo_ta,
                    style = TextStyle(
                        fontSize = 13.sp,
                        color = Color.White.copy(alpha = 0.8f)
                    ),
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )
            }
        }
    }
}


@Composable
@Preview
fun CardTaiKhoanPhuPreview() {
   var taikhoan = TaiKhoanModel(
        id = 1,
        id_nguoidung = 1,
        ten_taikhoan = "Tiền mua xe",
        so_du = 2500000,
        loai_taikhoan = 1,
        mo_ta = "Tiền để dành mua xe"
    )
    CardTaikhoanPhu(taikhoan = taikhoan)
}
