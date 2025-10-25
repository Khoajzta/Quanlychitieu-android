package com.example.quanlychitieu.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.DismissDirection
import androidx.compose.material.DismissValue
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Icon
import androidx.compose.material.SwipeToDismiss
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.rememberDismissState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.Offset
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
            .wrapContentHeight()
            .fillMaxWidth()
            .clip(RoundedCornerShape(RadiusXL))
            .background(
                brush = Brush.linearGradient(
                    colors = listOf(Color(0xFF11998e), Color(0xFF38ef7d)),
                    start = Offset.Zero,
                    end = Offset.Infinite
                )
            )

            .clickable { /* TODO: xử lý khi bấm vào card */ }
            .padding(20.dp)
    ) {
        Column(
            modifier = Modifier,
            verticalArrangement = Arrangement.SpaceAround
        ) {
            // Hàng trên: Chip + logo ngân hàng
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.Top
            ) {
                Column {
                    Text(
                        text = taikhoan.ten_taikhoan.uppercase(),
                        style = TextStyle(
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color.White
                        )
                    )
                    Spacer(modifier = Modifier.height(4.dp))
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

            Spacer(modifier = Modifier.height(20.dp))

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
        }
    }
}


@OptIn(ExperimentalMaterialApi::class)
@Composable
fun CardTaiKhoanSwipeToDelete(
    taikhoan: TaiKhoanModel,
    onDelete: (TaiKhoanModel) -> Unit
) {
    var showDialog by remember { mutableStateOf(false) }

    // Trạng thái swipe
    val dismissState = rememberDismissState(
        confirmStateChange = { value ->
            if (value == DismissValue.DismissedToStart) {
                showDialog = true
            }
            false // Không xóa ngay
        }
    )

    // Dialog xác nhận xóa
    if (showDialog) {
        ThongBaoDialog(
            title = "Xác nhận xóa tài khoản",
            message = "Bạn có chắc muốn xóa tài khoản '${taikhoan.ten_taikhoan}' không?",
            onConfirm = {
                onDelete(taikhoan)
                showDialog = false
            },
            onDismiss = { showDialog = false },
            confirmText = "Đồng ý",
            dismissText = "Hủy",
            confirmButtonColor = Color.Red
        )
    }

    SwipeToDismiss(
        state = dismissState,
        directions = setOf(DismissDirection.EndToStart),
        background = {
            // Nền đỏ khi vuốt sang trái
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.Red, shape = RoundedCornerShape(RadiusXL))
                    .padding(horizontal = 20.dp),
                contentAlignment = Alignment.CenterEnd
            ) {
                Icon(
                    imageVector = Icons.Default.Delete,
                    contentDescription = "Xóa",
                    tint = Color.White
                )
            }
        },
        dismissContent = {
            CardTaikhoanPhu(
                taikhoan = taikhoan,
            )
        }
    )
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
    CardTaiKhoanSwipeToDelete(taikhoan = taikhoan, onDelete = {})
}
