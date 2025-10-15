package com.example.quanlychitieu.Views.AddTrade

import android.util.Log
import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AttachMoney
import androidx.compose.material.icons.filled.DriveFileRenameOutline
import androidx.compose.material.icons.filled.EditNote
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.quanlychitieu.Components.CusTomTextField
import com.example.quanlychitieu.Components.CustomButton
import com.example.quanlychitieu.Components.CustomDatePicker
import com.example.quanlychitieu.Components.CustomDropdown
import com.example.quanlychitieu.Utils.formatMillisToDB
import com.example.quanlychitieu.Utils.listKhoanChiConst.listKhoanChi
import com.example.quanlychitieu.domain.model.ChiTieuModel
import com.example.quanlychitieu.domain.model.KhoanChiModel
import com.example.quanlychitieu.domain.model.TaiKhoanModel
import com.example.quanlychitieu.ui.ViewModels.ChiTieuViewModel
import com.example.quanlychitieu.ui.theme.Dimens.SpaceMedium
import formatCurrency

@Composable
fun AddTradeTab(
    listKhoanChi: List<KhoanChiModel>,
    taikhoanchinh : TaiKhoanModel,
    userId : Int
){
    val tabs = listOf("Chi tiêu", "Thu nhập")
    var selectedTabIndex by remember { mutableStateOf(0) }


    Column(
        modifier = Modifier
            .fillMaxHeight()
    ) {
        Box(
            modifier = Modifier
                .padding(horizontal = 20.dp)
                .fillMaxWidth()
                .background(
                    color = Color.White.copy(alpha = 0.2f),
                    shape = RoundedCornerShape(25)
                )
                .padding(4.dp)
        ) {
            Row(
                modifier = Modifier.wrapContentWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                tabs.forEachIndexed { index, title ->
                    val isSelected = selectedTabIndex == index
                    val backgroundColor =
                        if (isSelected) Color.White else Color.Transparent
                    val textColor =
                        if (isSelected) Color(0xFF1C94D5) else Color.Black.copy(alpha = 0.8f)

                    Box(
                        modifier = Modifier
                            .weight(1f)
                            .clip(RoundedCornerShape(25))
                            .background(backgroundColor)
                            .clickable { selectedTabIndex = index }
                            .padding(vertical = 8.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = title,
                            color = textColor,
                            fontSize = 15.sp,
                            fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Normal
                        )
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(10.dp))

        AnimatedContent(
            targetState = selectedTabIndex,
            transitionSpec = {
                if (targetState > initialState) {
                    slideInHorizontally { it } + fadeIn() togetherWith slideOutHorizontally { -it } + fadeOut()
                } else {
                    slideInHorizontally { -it } + fadeIn() togetherWith slideOutHorizontally { it } + fadeOut()
                }
            },
            label = ""
        ) { index ->
            when (index) {
                0 -> AddChiTieuPage(listKhoanChi, taikhoanchinh, userId)
                1 -> AddThuNhapPage()
            }
        }

    }
}

@Composable
fun AddChiTieuPage(
    listKhoanChi: List<KhoanChiModel>,
    taikhoanchinh : TaiKhoanModel,
    userId : Int,
    chiTieuViewModel: ChiTieuViewModel = hiltViewModel()
) {
    var sotien by remember { mutableStateOf(0) }
    var mota by remember { mutableStateOf("") }

    var selectedKhoanChi by remember { mutableStateOf(listKhoanChi.firstOrNull()) }
    var selectedDate by remember { mutableStateOf<Long?>(null) }


    Column(
        modifier = Modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(SpaceMedium)
    ) {
        CusTomTextField(
            value = if (sotien == 0) "" else formatCurrency(sotien),
            onValueChange = { newValue ->
                val digits = newValue.filter { it.isDigit() }
                sotien = if (digits.isNotEmpty()) digits.toInt() else 0
            },
            leadingIcon = {
                Icon(Icons.Default.AttachMoney, contentDescription = null, tint = Color.Gray)
            },
            placeholder = "Số tiền",
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number, imeAction = ImeAction.Next),
            modifier = Modifier.fillMaxWidth()
        )

        CustomDropdown(
            items = listKhoanChi,
            leadingIcon = {
                Text(selectedKhoanChi?.emoji ?: "", fontSize = 20.sp)
            },
            selectedItem = selectedKhoanChi,
            itemLabel = { it.ten_khoanchi },
            onSelect = { selectedKhoanChi = it }
        )

        CustomDatePicker(
            selectedDate = selectedDate,
            placeholder = "Ngày giao dịch",
            onDateSelected = { selectedDate = it }
        )

        CusTomTextField(
            value = mota,
            onValueChange = { mota = it },
            placeholder = "Nhập ghi chú",
            leadingIcon = {
                Icon(Icons.Default.EditNote, contentDescription = null, tint = Color.Gray)
            },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text, imeAction = ImeAction.Done),
            modifier = Modifier.fillMaxWidth().height(200.dp)
        )

        CustomButton(
            modifier = Modifier.fillMaxWidth(),
            onClick = {
                var chiTieu = ChiTieuModel(
                    id = 0,
                    id_nguoidung = userId,
                    id_khoanchi = selectedKhoanChi!!.id,
                    id_taikhoan = taikhoanchinh.id,
                    so_tien = sotien,
                    ngay_tao = formatMillisToDB(selectedDate),
                    ghi_chu = mota
                )
                chiTieuViewModel.createChiTieu(chiTieu)
                    
                Log.d("Ngay tao","${formatMillisToDB(selectedDate)}" )

            },
            title = "Thêm chi tiêu"
        )
    }
}




@Composable
fun AddThuNhapPage() {
    var tenThuNhap by remember { mutableStateOf("") }
    var sotien by remember { mutableStateOf(0) }
    var selectedDate by remember { mutableStateOf<Long?>(null) }

    Column(
        modifier = Modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(SpaceMedium)
    ) {
        CusTomTextField(
            value = tenThuNhap,
            onValueChange = { tenThuNhap = it },
            leadingIcon = {
                Icon(Icons.Default.DriveFileRenameOutline, contentDescription = null, tint = Color.Gray)
            },
            placeholder = "Tên thu nhập",
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number, imeAction = ImeAction.Next),
            modifier = Modifier.fillMaxWidth()
        )

        CusTomTextField(
            value = if (sotien == 0) "" else formatCurrency(sotien),
            onValueChange = { newValue ->
                val digits = newValue.filter { it.isDigit() }
                sotien = if (digits.isNotEmpty()) digits.toInt() else 0
            },
            leadingIcon = {
                Icon(Icons.Default.AttachMoney, contentDescription = null, tint = Color.Gray)
            },
            placeholder = "Số tiền",
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number, imeAction = ImeAction.Next),
            modifier = Modifier.fillMaxWidth()
        )

        CustomDatePicker(
            selectedDate = selectedDate,
            placeholder = "Ngày thu nhập",
            onDateSelected = { selectedDate = it }
        )

        CustomButton(
            modifier = Modifier.fillMaxWidth(),
            onClick = {
                Log.d("so tien",sotien.toString())
            },
            title = "Thêm thu nhập"
        )
    }
}

@Composable
@Preview
fun Preview(){

//    AddThuNhapPage()
}