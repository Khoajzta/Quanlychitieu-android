package com.example.quanlychitieu.Views.AddKhoanChi

import EmojiPickerBottomSheet
import Header
import android.text.format.DateUtils.formatDateRange
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
import androidx.compose.material.icons.filled.AddCircle
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
import androidx.compose.ui.unit.sp
import androidx.emoji2.text.EmojiCompat
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.quanlychitieu.Components.CusTomTextField
import com.example.quanlychitieu.Components.CustomButton
import com.example.quanlychitieu.Components.CustomDatePicker
import com.example.quanlychitieu.Components.EmojiPickerGrid
import com.example.quanlychitieu.Utils.formatMillisToDB
import com.example.quanlychitieu.ViewModels.KhoanChiViewModel
import com.example.quanlychitieu.domain.model.KhoanChiModel
import com.example.quanlychitieu.ui.Views.AddKhoanChi.components.ColorPickerRow
import com.example.quanlychitieu.ui.Views.AddKhoanChi.components.EmojiRow
import com.example.quanlychitieu.ui.components.CustomSnackbar
import com.example.quanlychitieu.ui.components.SnackbarType
import com.example.quanlychitieu.ui.state.UiState
import com.example.quanlychitieu.ui.theme.BackgroundColor
import com.example.quanlychitieu.ui.theme.Dimens.PaddingBody
import com.example.quanlychitieu.ui.theme.Dimens.SpaceMedium
import formatCurrency
import kotlinx.coroutines.delay
import unifiedToEmoji
import java.util.Calendar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddKhoanChiScreen(
    navController: NavController,
    userId: Int,
    khoanchiViewModel: KhoanChiViewModel = hiltViewModel()
) {
    var sotien by remember { mutableStateOf(0L) }
    var tenKhoanChiInput by remember { mutableStateOf("") }
    var selectedColor by remember { mutableStateOf("blue") }

    val suggestedEmojis = listOf("🍔", "☕", "🛒", "🎁", "✈️")
    var emojiInput by remember { mutableStateOf(suggestedEmojis.first()) }

    var showEmojiDialog by remember { mutableStateOf(false) }

    var snackbarVisible by remember { mutableStateOf(false) }
    var snackbarType by remember { mutableStateOf(SnackbarType.SUCCESS) }
    var snackbarMessage by remember { mutableStateOf("") }

    val createKhoanChiUiState = khoanchiViewModel.createKhoanChiState
    val colorOptions = listOf("red", "blue", "green", "yellow")

    // ✅ Lấy tháng hiện tại
    val currentDate = remember { Calendar.getInstance() }

    val firstDayOfMonth = remember {
        Calendar.getInstance().apply {
            set(Calendar.DAY_OF_MONTH, 1)
            set(Calendar.HOUR_OF_DAY, 0)
            set(Calendar.MINUTE, 0)
            set(Calendar.SECOND, 0)
            set(Calendar.MILLISECOND, 0)
        }.timeInMillis
    }

    val lastDayOfMonth = remember {
        Calendar.getInstance().apply {
            set(Calendar.DAY_OF_MONTH, getActualMaximum(Calendar.DAY_OF_MONTH))
            set(Calendar.HOUR_OF_DAY, 23)
            set(Calendar.MINUTE, 59)
            set(Calendar.SECOND, 59)
            set(Calendar.MILLISECOND, 999)
        }.timeInMillis
    }

    Log.d("firstDayOfMonth", "firstDayOfMonth: $firstDayOfMonth")
    Log.d("lastDayOfMonth", "lastDayOfMonth: $lastDayOfMonth")


    Scaffold(
        containerColor = BackgroundColor,
        topBar = {
            Header(
                navController,
                Modifier.windowInsetsPadding(WindowInsets.statusBars),
                title = "Thêm khoản chi",
                userId = userId
            )
        },
        contentWindowInsets = WindowInsets(0, 0, 0, 0)
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
                .padding(horizontal = PaddingBody)
        ) {
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.spacedBy(SpaceMedium)
            ) {
                // Nhập tên khoản chi
                CusTomTextField(
                    value = tenKhoanChiInput,
                    onValueChange = { tenKhoanChiInput = it },
                    leadingIcon = {
                        Text(
                            text = EmojiCompat.get().process(emojiInput).toString(),
                            fontSize = 20.sp,
                        )
                    },
                    placeholder = "Tên khoản chi",
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Text,
                        imeAction = ImeAction.Next
                    ),
                    modifier = Modifier.fillMaxWidth()
                )

                // ✅ Hiển thị khoảng thời gian tự động
                

                // Nhập số tiền
                CusTomTextField(
                    value = if (sotien == 0L) "" else formatCurrency(sotien),
                    onValueChange = { newValue ->
                        val digits = newValue.filter { it.isDigit() }
                        sotien = if (digits.isNotEmpty()) digits.toLong() else 0L
                    },
                    leadingIcon = {
                        Text(
                            text = "💵",
                            fontSize = 20.sp,
                        )
                    },
                    placeholder = "Số tiền dự kiến",
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Number,
                        imeAction = ImeAction.Done
                    ),
                    modifier = Modifier.fillMaxWidth()
                )

                EmojiRow(
                    listEmoji = suggestedEmojis,
                    onClickEmoji = { emojiInput = it },
                    onClickMore = { showEmojiDialog = true }
                )

                ColorPickerRow(
                    colorOptions = colorOptions,
                    selectedColor = selectedColor,
                    onColorSelected = { selectedColor = it }
                )

                // Nút thêm khoản chi
                CustomButton(
                    modifier = Modifier.fillMaxWidth(),
                    title = "Thêm khoản chi",
                    icon = Icons.Default.AddCircle,
                    onClick = {
                        if (tenKhoanChiInput.isNotBlank() && sotien > 0) {
                            val khoanchinew = KhoanChiModel(
                                id = 0,
                                ten_khoanchi = tenKhoanChiInput,
                                id_nguoidung = userId,
                                mausac = selectedColor,
                                ngay_batdau = formatMillisToDB(firstDayOfMonth),
                                ngay_ketthuc = formatMillisToDB(lastDayOfMonth),
                                so_tien_du_kien = sotien,
                                emoji = emojiInput
                            )
                            khoanchiViewModel.createKhoanChi(khoanchinew)
                        } else {
                            snackbarMessage = "Vui lòng nhập đầy đủ thông tin"
                            snackbarType = SnackbarType.ERROR
                            snackbarVisible = true
                        }
                    }
                )

                EmojiPickerBottomSheet(
                    show = showEmojiDialog,
                    onDismiss = { showEmojiDialog = false },
                    onEmojiSelected = {
                        emojiInput = it
                        showEmojiDialog = false
                    }
                )
            }

            AnimatedVisibility(
                visible = snackbarVisible,
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .padding(bottom = 16.dp),
                enter = slideInVertically { it } + fadeIn(),
                exit = slideOutVertically { it } + fadeOut()
            ) {
                CustomSnackbar(message = snackbarMessage, type = snackbarType)
            }

            LaunchedEffect(snackbarVisible) {
                if (snackbarVisible) {
                    delay(3000)
                    snackbarVisible = false
                }
            }

            LaunchedEffect(createKhoanChiUiState) {
                when (createKhoanChiUiState) {
                    is UiState.Success -> {
                        snackbarMessage = "Tạo khoản chi thành công"
                        snackbarType = SnackbarType.SUCCESS
                        snackbarVisible = true

                        delay(1000)
                        navController.popBackStack()
                        khoanchiViewModel.resetCreateKhoanChiState()
                    }

                    is UiState.Error -> {
                        val errorMessage = (createKhoanChiUiState as UiState.Error).message
                        snackbarMessage = errorMessage
                        snackbarType = SnackbarType.ERROR
                        snackbarVisible = true

                        delay(3000)
                        snackbarVisible = false
                        khoanchiViewModel.resetCreateKhoanChiState()
                    }

                    else -> Unit
                }
            }
        }
    }
}


@Composable
@Preview
fun AddKhoanChiPreview(){
    var navController = rememberNavController()
    AddKhoanChiScreen(navController,1)
}
