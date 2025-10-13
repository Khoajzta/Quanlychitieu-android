package com.example.quanlychitieu.Views.AddKhoanChi

import Header
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AttachMoney
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.emoji2.text.EmojiCompat
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.quanlychitieu.Components.CusTomTextField
import com.example.quanlychitieu.Components.CustomButton
import com.example.quanlychitieu.Components.CustomDatePicker
import com.example.quanlychitieu.Components.EmojiPickerGrid
import com.example.quanlychitieu.Utils.formatMillisToDB
import com.example.quanlychitieu.models.KhoanChiModel
import com.example.quanlychitieu.ui.theme.BackgroundColor
import com.example.quanlychitieu.ui.theme.Dimens.PaddingBody
import com.example.quanlychitieu.ui.theme.Dimens.RadiusFull
import formatCurrency

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddKhoanChiScreen(
    navController: NavController
) {
    var sotien by remember { mutableStateOf(0) }
    var tenKhoanChiInput by remember { mutableStateOf("") }
    var selectedColor by remember { mutableStateOf("blue") }

    val suggestedEmojis = listOf("🍔", "☕", "🛒", "🎁", "✈️","⚽")
    var emojiInput by remember { mutableStateOf(suggestedEmojis.first()) }

    var showEmojiDialog by remember { mutableStateOf(false) }

    var ngayBatDau by remember { mutableStateOf<Long?>(null) }
    var ngayKetThuc by remember { mutableStateOf<Long?>(null) }

    Scaffold(
        containerColor = BackgroundColor,
        topBar = {
            Header(navController, Modifier.windowInsetsPadding(WindowInsets.statusBars), title = "Thêm khoản chi")
        },
        contentWindowInsets = WindowInsets(0, 0, 0, 0)
    ) {inerPadding ->
        Column(
            modifier = Modifier
                .padding(inerPadding)
                .fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(20.dp)
        ) {
            // Tên khoản chi (emoji only)
            CusTomTextField(
                value = tenKhoanChiInput,
                onValueChange = { tenKhoanChiInput = it },
                leadingIcon = {
                    Text(
                        text = EmojiCompat.get().process(emojiInput).toString(),
                        fontSize = MaterialTheme.typography.headlineMedium.fontSize
                    )

                },
                placeholder = "Tên khoản chi",
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text, imeAction = ImeAction.Next),
                modifier = Modifier.fillMaxWidth()
            )

            CustomDatePicker(
                selectedDate = ngayBatDau,
                placeholder = "Ngày bắt đầu",
                onDateSelected = { ngayBatDau = it }
            )

            CustomDatePicker(
                selectedDate = ngayKetThuc,
                placeholder = "Ngày kết thúc",
                onDateSelected = { ngayKetThuc = it }
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



            // Gợi ý emoji
            Row(
                modifier = Modifier .padding(horizontal = PaddingBody) .fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(12.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                suggestedEmojis.forEach { emoji ->
                    Box(
                        modifier = Modifier
                            .size(50.dp)
                            .clip(RoundedCornerShape(RadiusFull))
                            .background(color = Color.White),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = emoji,
                            fontSize = MaterialTheme.typography.headlineMedium.fontSize,
                            modifier = Modifier .clickable { emojiInput = emoji }
                        )
                    }

                }

                if (showEmojiDialog) {
                    ModalBottomSheet(
                        onDismissRequest = { showEmojiDialog = false },
                        containerColor = Color.White,
                        shape = RoundedCornerShape(topStart = 20.dp, topEnd = 20.dp)
                    ) {
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(16.dp)
                        ) {
                            EmojiPickerGrid { selected ->
                                emojiInput = selected
                                showEmojiDialog = false
                            }
                        }
                    }
                }

            }
            CustomButton(title = "Thêm icon", onClick = {showEmojiDialog = true})

            val colorOptions = listOf("red", "blue", "green", "yellow")
            Row(
                modifier = Modifier
                    .padding(horizontal = PaddingBody)
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {
                colorOptions.forEach { color ->
                    val gradient = when(color) {
                        "red" -> listOf(Color(0xFFE57373), Color(0xFFF06292).copy(alpha = 0.35f))
                        "blue" -> listOf(Color(0xFF64B5F6), Color(0xFF4FC3F7).copy(alpha = 0.35f))
                        "green" -> listOf(Color(0xFF81C784), Color(0xFF4DB6AC).copy(alpha = 0.35f))
                        "yellow" -> listOf(Color(0xFFFFB74D), Color(0xFFFF8A65).copy(alpha = 0.35f))
                        else -> listOf(Color.LightGray, Color.Gray)
                    }

                    Box(
                        modifier = Modifier
                            .padding(horizontal = 5.dp)
                            .size(40.dp)
                            .background(
                                Brush.horizontalGradient(colors = gradient),
                                shape = CircleShape
                            )
                            .clickable { selectedColor = color }
                            .border(
                                width = if (selectedColor == color) 3.dp else 0.dp,
                                color = Color.White,
                                shape = CircleShape
                            )
                    )
                }
            }
            Spacer(modifier = Modifier.height(20.dp))

            CustomButton(
                title = "Lưu khoản chi",
                onClick = {
                    val khoanchinew = KhoanChiModel(
                        id = 1,
                        ten_khoanchi = tenKhoanChiInput,
                        id_nguoidung =1,
                        mausac = selectedColor,
                        ngay_batdau = formatMillisToDB(ngayBatDau),
                        ngay_ketthuc = formatMillisToDB(ngayKetThuc),
                        so_tien_du_kien = sotien,
                        emoji = "🤣"
                    )


                    Log.d("KhoanChiModel", khoanchinew.toString())
                }
            )
        }
    }

}

@Composable
@Preview
fun AddKhoanChiPreview(){
    var navController = rememberNavController()
    AddKhoanChiScreen(navController)
}
