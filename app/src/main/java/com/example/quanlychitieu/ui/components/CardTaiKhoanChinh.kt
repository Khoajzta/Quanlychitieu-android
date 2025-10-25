package com.example.quanlychitieu.ui.components

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AttachMoney
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.quanlychitieu.Components.CusTomTextField
import com.example.quanlychitieu.Components.CustomButton
import com.example.quanlychitieu.domain.model.TaiKhoanModel
import com.example.quanlychitieu.ui.ViewModels.TaiKhoanViewModel
import com.example.quanlychitieu.ui.theme.Dimens.RadiusXL
import formatCurrency

@Composable
fun CardTaiKhoanChinh(
    modifier: Modifier = Modifier,
    taikhoan: TaiKhoanModel,
    taiKhoanViewModel: TaiKhoanViewModel = hiltViewModel()
){
    var showDialog by remember { mutableStateOf(false) }

    if (showDialog) {
        CustomDialog(
            onDismiss = { showDialog = false },
            title = "Nạp tiền"
        ) {
            var sotien by remember { mutableStateOf(0L) }


            CusTomTextField(
                value = if (sotien == 0L) "" else formatCurrency(sotien),
                onValueChange = { newValue ->
                    val digits = newValue.filter { it.isDigit() }
                    sotien = if (digits.isNotEmpty()) digits.toLong() else 0L
                },
                leadingIcon = {
                    Icon(Icons.Default.AttachMoney, contentDescription = null, tint = Color.Gray)
                },
                placeholder = "Số tiền",
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Number,
                    imeAction = ImeAction.Next
                ),
                modifier = Modifier.fillMaxWidth()
            )

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.End,
                verticalAlignment = Alignment.CenterVertically
            ) {
                CustomButton(
                    title = "Hủy",
                    onClick = {showDialog = false},
                    containerColor = Color.Red
                )
                Spacer(modifier = Modifier.width(5.dp))
                CustomButton(
                    title = "Nạp",
                    onClick = {
                        var taikhoanUpdate = TaiKhoanModel(
                            id = taikhoan.id,
                            id_nguoidung = taikhoan.id_nguoidung,
                            ten_taikhoan = taikhoan.ten_taikhoan,
                            so_du = taikhoan.so_du + sotien,
                            loai_taikhoan = taikhoan.loai_taikhoan,
                            mo_ta = taikhoan.mo_ta
                        )

                        taiKhoanViewModel.updateTaiKhoan(taikhoanUpdate, id = taikhoan.id)
                        taiKhoanViewModel.loadTaiKhoans(taikhoan.id_nguoidung)

                        Log.d("So tien nap", sotien.toString())

                        showDialog = false
                    },
                )
            }
        }
    }

    Box(
        modifier = modifier
            .height(200.dp)
            .fillMaxWidth()
            .clip(RoundedCornerShape(RadiusXL))
            .background(
                brush = Brush.linearGradient(
                    colors = listOf(Color(0xFF232526), Color(0xFF414345)),
                    start = Offset.Zero,
                    end = Offset.Infinite
                )
            )

            .clickable { /* TODO: xử lý khi bấm vào card */ }
            .padding(20.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize(),
            verticalArrangement = Arrangement.SpaceAround
        ) {
            // Hàng trên: Chip + logo ngân hàng
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.Top
            ) {
                Column(
                    verticalArrangement = Arrangement.spacedBy(4.dp)
                ) {
                    Text(
                        text = taikhoan.ten_taikhoan.uppercase(),
                        style = TextStyle(
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color.White
                        )
                    )
                    Text(
                        text = taikhoan.mo_ta,
                        color = Color.White.copy(alpha = 0.9f),
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold
                    )
                }

                Text(
                    text = "BANK",
                    color = Color.White.copy(alpha = 0.9f),
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold
                )
            }

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column(
                    verticalArrangement = Arrangement.spacedBy(6.dp)
                ) {
                    Text(
                        text = formatCurrency(taikhoan.so_du),
                        style = TextStyle(
                            fontSize = 22.sp,
                            fontWeight = FontWeight.SemiBold,
                            color = Color.White
                        )
                    )
                    Text(
                        text = "**** **** **** ${taikhoan.id.toString().takeLast(4)}",
                        style = TextStyle(
                            fontSize = 14.sp,
                            letterSpacing = 2.sp,
                            color = Color.White.copy(alpha = 0.8f)
                        )
                    )
                }

                CustomButton(
                    modifier = Modifier
                        .wrapContentWidth(),
                    title = "Nạp tiền",
                    onClick = {
                        showDialog = true
                    }
                )
            }

        }
    }

}

@Composable
@Preview
fun CardTaiKhoanChinhPreview() {
    var taikhoan = TaiKhoanModel(
        id = 1,
        id_nguoidung = 1,
        ten_taikhoan = "Tài khoản chính",
        so_du = 2500000,
        loai_taikhoan = 1,
        mo_ta = "Dùng cho chi tiêu hàng ngày"
    )
    CardTaiKhoanChinh(taikhoan = taikhoan)
}