package com.example.quanlychitieu.Views.ListKhoanChi

import Header
import Screen
import android.util.Log
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.quanlychitieu.Components.CardKhoanChiDetail
import com.example.quanlychitieu.Components.CustomFAB
import com.example.quanlychitieu.Components.DotLoading
import com.example.quanlychitieu.ViewModels.KhoanChiViewModel
import com.example.quanlychitieu.domain.model.KhoanChiModel
import com.example.quanlychitieu.ui.components.CustomSnackbar
import com.example.quanlychitieu.ui.components.SnackbarType
import com.example.quanlychitieu.ui.state.UiState
import com.example.quanlychitieu.ui.theme.BackgroundColor
import com.example.quanlychitieu.ui.theme.Dimens.PaddingBody
import com.example.quanlychitieu.ui.theme.Dimens.SpaceMedium
import kotlinx.coroutines.delay

@Composable
fun ListKhoanChiScreen(
    navController: NavController,
    userId: Int,
    khoanChiViewModel: KhoanChiViewModel = hiltViewModel(),
){
    val khoanChiuiState by khoanChiViewModel.uiState.collectAsState()
    val deleteState = khoanChiViewModel.deleteKhoanChiState

    LaunchedEffect(userId) {
        if (userId > 0) {
            while (true) {
                khoanChiViewModel.loadKhoanChi(userId)
                delay(15 * 60 * 1000L)
            }
        }
    }

    val khoanChiList = when (khoanChiuiState) {
        is UiState.Success -> (khoanChiuiState as UiState.Success<List<KhoanChiModel>>).data
        else -> emptyList()
    }

    var khoanChiToDelete by remember { mutableStateOf<KhoanChiModel?>(null) }

    var snackbarVisible by remember { mutableStateOf(false) }
    var snackbarType by remember { mutableStateOf(SnackbarType.SUCCESS) }
    var snackbarMessage by remember { mutableStateOf("") }

    // 🧭 Dialog xác nhận xóa
    if (khoanChiToDelete != null) {
        AlertDialog(
            onDismissRequest = { khoanChiToDelete = null },
            title = { Text(text = "Xác nhận xóa") },
            text = {
                Text(
                    text = "Bạn có chắc muốn xóa khoản chi '${khoanChiToDelete?.ten_khoanchi ?: ""}' " +
                            "và tất cả các chi tiêu trong đó không?"
                )
            },
            confirmButton = {
                TextButton(
                    onClick = {
                        khoanChiToDelete?.let {
                            khoanChiViewModel.deleteKhoanChi(it.id)
                        }
                        khoanChiToDelete = null
                    }
                ) {
                    Text("Đồng ý")
                }
            },
            dismissButton = {
                TextButton(onClick = { khoanChiToDelete = null }) {
                    Text("Hủy")
                }
            }
        )
    }

    // 🧭 Lắng nghe trạng thái sau khi xóa
    LaunchedEffect(deleteState) {
        when (deleteState) {
            is UiState.Success -> {
                snackbarMessage = "Xóa khoản chi thành công"
                snackbarType = SnackbarType.SUCCESS
                snackbarVisible = true
                khoanChiViewModel.resetDeleteState()
            }

            is UiState.Error -> {
                snackbarMessage = deleteState.message
                snackbarType = SnackbarType.ERROR
                snackbarVisible = true
                khoanChiViewModel.resetDeleteState()
            }

            else -> Unit
        }
    }

    Scaffold(
        containerColor = BackgroundColor,
        topBar = {
            Header(
                navController,
                Modifier.windowInsetsPadding(WindowInsets.statusBars),
                title = "Danh sách khoản chi",
                userId
            )
        },
        floatingActionButton = {
            CustomFAB(onClick = { navController.navigate(Screen.AddKhoanChi.createRoute(userId)) })
        },
        contentWindowInsets = WindowInsets(0, 0, 0, 0)
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            when (khoanChiuiState) {
                is UiState.Success -> {
                    LazyColumn(
                        modifier = Modifier.fillMaxSize().padding(horizontal = PaddingBody),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.spacedBy(SpaceMedium)
                    ) {
                        items(khoanChiList){khoanchi->
                            CardKhoanChiDetail(
                                item = khoanchi,
                                modifier = Modifier,
                                onDetailClick = {
                                    navController.navigate(
                                        Screen.KhoanChiDetail.createRoute(
                                            id_khoanChi = khoanchi.id,
                                            userId = userId
                                        )
                                    )
                                },
                                onEdit = {
                                    navController.navigate(
                                        Screen.UpdateKhoanChi.createRoute(
                                            userId,
                                            id_khoanchi = khoanchi.id
                                        )
                                    )
                                },
                                onDelete = {
                                    khoanChiToDelete = khoanchi
                                }
                            )
                        }

                        item {
                            Spacer(modifier = Modifier.height(100.dp))
                        }
                    }
                }

                is UiState.Loading -> {
                    DotLoading()
                }

                else -> {
                    Log.d("Error", "Error")
                }
            }

            AnimatedVisibility(
                visible = snackbarVisible,
                modifier = Modifier
                    .align(Alignment.TopCenter)
                    .padding(bottom = 16.dp),
                enter = slideInVertically { -it } + fadeIn(),
                exit = slideOutVertically { -it } + fadeOut()
            ) {
                CustomSnackbar(
                    message = snackbarMessage,
                    type = snackbarType
                )
            }

            // ⏳ Tự ẩn Snackbar + gọi lại dữ liệu an toàn
            LaunchedEffect(snackbarVisible) {
                if (snackbarVisible) {
                    if (userId > 0) {
                        khoanChiViewModel.loadKhoanChi(userId)
                    }
                    delay(3000)
                    snackbarVisible = false
                }
            }
        }
    }

}