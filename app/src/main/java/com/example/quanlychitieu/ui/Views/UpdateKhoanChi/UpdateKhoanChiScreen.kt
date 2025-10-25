package com.example.quanlychitieu.ui.Views.UpdateKhoanChi

import Header
import android.util.Log
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.windowInsetsPadding
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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.emoji2.text.EmojiCompat
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.quanlychitieu.Components.CusTomTextField
import com.example.quanlychitieu.Components.CustomButton
import com.example.quanlychitieu.Components.CustomDatePicker
import com.example.quanlychitieu.Components.DotLoading
import com.example.quanlychitieu.Components.EmojiPickerGrid
import com.example.quanlychitieu.Utils.formatMillisToDB
import com.example.quanlychitieu.Utils.parseDateToMillis
import com.example.quanlychitieu.ViewModels.KhoanChiViewModel
import com.example.quanlychitieu.domain.model.KhoanChiModel
import com.example.quanlychitieu.ui.Views.AddKhoanChi.components.ColorPickerRow
import com.example.quanlychitieu.ui.Views.AddKhoanChi.components.EmojiRow
import com.example.quanlychitieu.ui.components.CustomSnackbar
import com.example.quanlychitieu.ui.components.SnackbarType
import com.example.quanlychitieu.ui.state.UiState
import com.example.quanlychitieu.ui.theme.BackgroundColor
import com.example.quanlychitieu.ui.theme.Dimens.SpaceMedium
import formatCurrency
import kotlinx.coroutines.delay

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UpdateKhoanChiScreen(
    navController: NavController,
    id_khoanChi: Int,
    userId: Int,
    khoanChiViewModel: KhoanChiViewModel = hiltViewModel()
) {

    val khoanChiState = khoanChiViewModel.getKhoanChiByIdState
    val khoanchiUpdateState = khoanChiViewModel.updateKhoanChiState

    // Gọi API khi mở màn hình
    LaunchedEffect(id_khoanChi) {
        khoanChiViewModel.getKhoanChiById(id_khoanChi)
    }

    // 🧠 Tạo state trước
    var sotien by remember { mutableStateOf(0L) }
    var tenKhoanChiInput by remember { mutableStateOf("") }
    var selectedColor by remember { mutableStateOf("") }
    var emojiInput by remember { mutableStateOf("") }
    var ngayBatDau by remember { mutableStateOf<Long?>(null) }
    var ngayKetThuc by remember { mutableStateOf<Long?>(null) }

    val suggestedEmojis = listOf("🍔", "☕", "🛒", "🎁", "✈️")
    var showEmojiDialog by remember { mutableStateOf(false) }
    val colorOptions = listOf("red", "blue", "green", "yellow")

    var snackbarVisible by remember { mutableStateOf(false) }
    var snackbarType by remember { mutableStateOf(SnackbarType.SUCCESS) }
    var snackbarMessage by remember { mutableStateOf("") }

    // 🪄 Khi API trả về Success, gán giá trị cho các biến UI
    LaunchedEffect(khoanChiState) {
        if (khoanChiState is UiState.Success) {
            val khoanchi = khoanChiState.data
            sotien = khoanchi.so_tien_du_kien
            tenKhoanChiInput = khoanchi.ten_khoanchi
            selectedColor = khoanchi.mausac ?: ""
            emojiInput = khoanchi.emoji ?: ""
            ngayBatDau = parseDateToMillis(khoanchi.ngay_batdau)
            ngayKetThuc = parseDateToMillis(khoanchi.ngay_ketthuc)
        }
    }

    Scaffold(
        containerColor = BackgroundColor,
        topBar = {
            Header(
                navController,
                Modifier.windowInsetsPadding(WindowInsets.statusBars),
                title = "Cập nhật khoản chi"
            )
        },
        contentWindowInsets = WindowInsets(0, 0, 0, 0)
    ) { innerPadding ->
        when (khoanChiState) {
            is UiState.Loading -> {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    DotLoading()
                }
            }

            is UiState.Success -> {

                Box(
                    modifier = Modifier
                        .padding(innerPadding)
                        .fillMaxSize()
                ){
                    Column(
                        modifier = Modifier
                            .fillMaxSize(),
                        verticalArrangement = Arrangement.spacedBy(SpaceMedium)
                    ) {
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
                            keyboardOptions = KeyboardOptions(
                                keyboardType = KeyboardType.Text,
                                imeAction = ImeAction.Next
                            ),
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

                        EmojiRow(
                            listEmoji = suggestedEmojis,
                            onClickEmoji = { emoji -> emojiInput = emoji },
                            onClickMore = { showEmojiDialog = true }
                        )

                        ColorPickerRow(
                            colorOptions = colorOptions,
                            selectedColor = selectedColor,
                            onColorSelected = { selectedColor = it }
                        )

                        CustomButton(
                            title = "Lưu khoản chi",
                            onClick = {
                                val khoanchinew = KhoanChiModel(
                                    id = id_khoanChi,
                                    ten_khoanchi = tenKhoanChiInput,
                                    id_nguoidung = userId,
                                    mausac = selectedColor,
                                    ngay_batdau = formatMillisToDB(ngayBatDau),
                                    ngay_ketthuc = formatMillisToDB(ngayKetThuc),
                                    so_tien_du_kien = sotien,
                                    emoji = emojiInput
                                )
                                khoanChiViewModel.updateKhoanChi(id_khoanchi = id_khoanChi , khoanchi = khoanchinew)
                            }
                        )

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

                    AnimatedVisibility(
                        visible = snackbarVisible,
                        modifier = Modifier
                            .align(Alignment.BottomCenter)
                            .padding(bottom = 16.dp),
                        enter = slideInVertically { it } + fadeIn(),
                        exit = slideOutVertically { it } + fadeOut()
                    ) {
                        CustomSnackbar(
                            message = snackbarMessage,
                            type = snackbarType
                        )
                    }

                    // ✅ Tự động ẩn snackbar sau vài giây
                    LaunchedEffect(snackbarVisible) {
                        if (snackbarVisible) {
                            delay(3000)
                            snackbarVisible = false
                        }
                    }

                    // ✅ Lắng nghe UI state khi tạo khoản chi
                    LaunchedEffect(khoanchiUpdateState) {
                        when (khoanchiUpdateState) {
                            is UiState.Success -> {
                                snackbarMessage = "Cập nhật khoản chi thành công"
                                snackbarType = SnackbarType.SUCCESS
                                snackbarVisible = true

                                delay(1000)
                                navController.popBackStack()
                                khoanChiViewModel.resetUpdateState()
                            }

                            is UiState.Error -> {
                                val errorMessage = (khoanchiUpdateState as UiState.Error).message
                                snackbarMessage = errorMessage
                                snackbarType = SnackbarType.ERROR
                                snackbarVisible = true

                                delay(3000)
                                snackbarVisible = false
                                khoanChiViewModel.resetUpdateState()
                            }

                            else -> Unit
                        }
                    }
                }
            }

            is UiState.Error -> {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Text("Lỗi: ${khoanChiState.message}")
                }
            }
        }
    }
}


@Composable
@Preview
fun UpdateKhoanChiScreenPreview(){

}