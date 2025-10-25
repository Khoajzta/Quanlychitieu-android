package com.example.quanlychitieu.ui.Views.ChuyenTien

import Header
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
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AttachMoney
import androidx.compose.material.icons.filled.EditNote
import androidx.compose.material.icons.filled.SyncAlt
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
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
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.quanlychitieu.Components.CusTomTextField
import com.example.quanlychitieu.Components.CustomButton
import com.example.quanlychitieu.Components.CustomDropdown
import com.example.quanlychitieu.Components.DotLoading
import com.example.quanlychitieu.data.remote.dto.ChuyenTienRequest
import com.example.quanlychitieu.domain.model.TaiKhoanModel
import com.example.quanlychitieu.ui.ViewModels.TaiKhoanViewModel
import com.example.quanlychitieu.ui.components.CustomSnackbar
import com.example.quanlychitieu.ui.components.SnackbarType
import com.example.quanlychitieu.ui.state.UiState
import com.example.quanlychitieu.ui.theme.BackgroundColor
import com.example.quanlychitieu.ui.theme.Dimens.PaddingBody
import com.example.quanlychitieu.ui.theme.Dimens.SpaceMedium
import formatCurrency
import kotlinx.coroutines.delay

@Composable
fun ChuyenTienScreen(
    navController: NavController,
    userId:Int,
    taiKhoanViewModel: TaiKhoanViewModel = hiltViewModel()
){

    val taikhoanUiState by taiKhoanViewModel.uiState.collectAsState()
    val chuyenTienState by taiKhoanViewModel.chuyentienTaiKhoanState.collectAsState()

    var snackbarVisible by remember { mutableStateOf(false) }
    var snackbarType by remember { mutableStateOf(SnackbarType.SUCCESS) }
    var snackbarMessage by remember { mutableStateOf("") }

    LaunchedEffect(userId) {
        taiKhoanViewModel.loadTaiKhoans(userId)
    }

    LaunchedEffect(chuyenTienState) {
        if(chuyenTienState is UiState.Success){
            navController.popBackStack()
        }
    }

    var listTaiKhoan = when(taikhoanUiState){
        is UiState.Success -> (taikhoanUiState as UiState.Success<List<TaiKhoanModel>>).data
        else -> emptyList()

    }

    var selectedFromTaiKhoan by remember { mutableStateOf(listTaiKhoan.firstOrNull()) }
    var selectedToTaiKhoan by remember { mutableStateOf(listTaiKhoan.firstOrNull()) }
    var sotien by remember { mutableStateOf(0L) }
    var ghichu by remember { mutableStateOf("") }


    Scaffold(
        containerColor = BackgroundColor,
        topBar = {
            Header(
                navController = navController,
                modifier = Modifier.windowInsetsPadding(WindowInsets.statusBars),
                title = "Chuyển tiền",
                userId = userId
            )
        },
        contentWindowInsets = WindowInsets(0, 0, 0, 0)
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            if(taikhoanUiState is UiState.Success){
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(horizontal = PaddingBody),
                    verticalArrangement = Arrangement.spacedBy(SpaceMedium),
                ) {
                    CustomDropdown(
                        items = listTaiKhoan,
                        selectedItem = selectedFromTaiKhoan,
                        itemLabel = { it.ten_taikhoan },
                        onSelect = { selectedFromTaiKhoan = it },
                        placeholder = "Chọn tài khoản chuyển"
                    )

                    CustomDropdown(
                        items = listTaiKhoan,
                        selectedItem = selectedToTaiKhoan,
                        itemLabel = { it.ten_taikhoan },
                        onSelect = { selectedToTaiKhoan = it },

                        placeholder = "Chọn tài khoản nhận"
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

                    CusTomTextField(
                        value = ghichu,
                        onValueChange = { ghichu = it },
                        placeholder = "Nhập ghi chú",
                        leadingIcon = {
                            Icon(Icons.Default.EditNote, contentDescription = null, tint = Color.Gray)
                        },
                        keyboardOptions = KeyboardOptions(
                            keyboardType = KeyboardType.Text,
                            imeAction = ImeAction.Done
                        ),
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(200.dp)
                    )

                    CustomButton(
                        modifier = Modifier.fillMaxWidth(),
                        title = "Chuyển tiền",
                        onClick = {
                            when {
                                // 1️⃣ Kiểm tra dữ liệu cơ bản
                                sotien <= 0L -> {
                                    snackbarType = SnackbarType.INFO
                                    snackbarMessage = "Số tiền phải lớn hơn 0"
                                    snackbarVisible = true
                                }

                                ghichu.isNullOrBlank() -> {
                                    snackbarType = SnackbarType.INFO
                                    snackbarMessage = "Vui lòng nhập ghi chú"
                                    snackbarVisible = true
                                }

                                selectedFromTaiKhoan == null || selectedToTaiKhoan == null -> {
                                    snackbarType = SnackbarType.INFO
                                    snackbarMessage = "Vui lòng chọn đầy đủ tài khoản nguồn và đích"
                                    snackbarVisible = true
                                }

                                // 2️⃣ Không được chuyển cùng một tài khoản
                                selectedFromTaiKhoan == selectedToTaiKhoan -> {
                                    snackbarType = SnackbarType.ERROR
                                    snackbarMessage = "Không thể chuyển khi trùng 1 tài khoản"
                                    snackbarVisible = true
                                }

                                // 3️⃣ Kiểm tra số dư
                                selectedFromTaiKhoan!!.so_du < sotien -> {
                                    snackbarType = SnackbarType.ERROR
                                    snackbarMessage = "Không đủ số dư trong tài khoản"
                                    snackbarVisible = true
                                }

                                // 4️⃣ Nếu mọi thứ hợp lệ
                                else -> {
                                    val chuyenTienRequest = ChuyenTienRequest(
                                        fromId = selectedFromTaiKhoan!!.id,
                                        toId = selectedToTaiKhoan!!.id,
                                        amount = sotien,
                                        id_nguoidung = userId,
                                        ghi_chu = ghichu.trim()
                                    )

                                    taiKhoanViewModel.chuyenTien(chuyenTienRequest)

                                    snackbarType = SnackbarType.SUCCESS
                                    snackbarMessage = "Chuyển tiền thành công"
                                    snackbarVisible = true
                                }
                            }
                        },
                        icon = Icons.Filled.SyncAlt,
                    )
                }
            }else{
                DotLoading()
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

            LaunchedEffect(snackbarVisible) {
                if (snackbarVisible) {
                    delay(3000)
                    snackbarVisible = false
                }
            }

        }
    }

}

@Composable
@Preview
fun ChuyenTienScreenPreview(){

}