package com.example.quanlychitieu.ui.components

import androidx.compose.foundation.background
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
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.quanlychitieu.Components.CardThuNhap
import com.example.quanlychitieu.Utils.formatDayDisplay
import com.example.quanlychitieu.domain.model.ChiTieuModel
import com.example.quanlychitieu.domain.model.ThuNhapModel
import com.example.quanlychitieu.ui.theme.Dimens.RadiusXL
import formatCurrency

@Composable
fun CardChiTieu(
    modifier: Modifier = Modifier,
    chitieu: ChiTieuModel
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .background(
                brush = Brush.linearGradient(
                    colors = listOf(Color(0xFFEA695E), Color(0xFFF35B5B)),
                    start = Offset(0f, 0f),
                    end = Offset(300f, 300f)
                ),
                shape = RoundedCornerShape(RadiusXL)
            )
            .padding(12.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {

            Column(
                modifier = Modifier
            ) {

                Text(
                    text = "${chitieu.ghi_chu}",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White.copy(alpha = 0.9f)
                )

                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = "Ngày: ${formatDayDisplay(chitieu.ngay_tao)}",
                    fontSize = 16.sp,
                    color = Color.White.copy(alpha = 0.85f),
                )
            }


            Box(
                modifier = Modifier
                    .background(
                        Color.White.copy(alpha = 0.2f),
                        shape = RoundedCornerShape(12.dp)
                    )
                    .padding(horizontal = 12.dp, vertical = 6.dp)

            ) {
                Text(
                    text = "💰 ${formatCurrency(chitieu.so_tien)}",
                    fontSize = 22.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )
            }

        }
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun CardChiTieuSwipeToDelete(
    chitieu: ChiTieuModel,
    onDelete: (ChiTieuModel) -> Unit,
) {
    var showDialog by remember { mutableStateOf(false) }

    // State điều khiển swipe
    val dismissState = rememberDismissState(
        confirmStateChange = { value ->
            if (value == DismissValue.DismissedToStart) {
                // Vuốt sang trái xong -> hiện dialog xác nhận
                showDialog = true
            }
            false // Không xóa thẻ ngay lập tức
        }
    )

    if (showDialog) {

        ThongBaoDialog(
            title = "Xác nhận xóa chi tiêu",
            message = "Bạn có chắc muốn xóa chi tiêu này không?",
            onConfirm = {onDelete(chitieu)
                showDialog = false},
            onDismiss = {showDialog = false},
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
           CardChiTieu(chitieu = chitieu)
        }
    )
}



@Composable
@Preview
fun CardChiTieuPreview(){
    var chitieu = ChiTieuModel(
        id = 1,
        id_nguoidung = 1,
        id_khoanchi = 1,
        id_taikhoan = 1,
        so_tien = 20000,
        ngay_tao = "12-09-2025",
        ghi_chu = "ăn sáng"
    )

    CardChiTieu(modifier = Modifier,chitieu)
}