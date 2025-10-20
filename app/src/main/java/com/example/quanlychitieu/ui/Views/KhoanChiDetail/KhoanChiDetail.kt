package com.example.quanlychitieu.ui.Views.KhoanChiDetail

import Header
import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.quanlychitieu.Components.DotLoading
import com.example.quanlychitieu.domain.model.ChiTieuModel
import com.example.quanlychitieu.ui.ViewModels.ChiTieuViewModel
import com.example.quanlychitieu.ui.Views.KhoanChiDetail.components.ListChiTieuColumn
import com.example.quanlychitieu.ui.state.UiState
import com.example.quanlychitieu.ui.theme.BackgroundColor
import kotlinx.coroutines.delay

@Composable
fun KhoanChiDetailScreen(
    navController: NavHostController,
    id_khoanChi :Int,
    userId: Int,
    chiTieuViewModel: ChiTieuViewModel = hiltViewModel()
){
    val chiTieuState by chiTieuViewModel.uiState.collectAsState()

    LaunchedEffect(id_khoanChi, userId) {
        if (userId > 0) {
            while (true) {
                chiTieuViewModel.getChiTieuTheoKhoanChiCuaUser(id_khoanChi, userId)
                delay(15 * 60 * 1000L)
            }
        }
    }

    val chhitieuList = when (chiTieuState) {
        is UiState.Success -> (chiTieuState as UiState.Success<List<ChiTieuModel>>).data
        else -> emptyList()
    }

    Scaffold(
        containerColor = BackgroundColor,
        topBar = {
            Header(
                navController,
                Modifier.windowInsetsPadding(WindowInsets.statusBars),
                title = "Danh sách chi tiêu",
                userId
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
            when (chiTieuState) {
                is UiState.Success -> {
                    if (chhitieuList.isEmpty()) {
                        Text(
                            text = "Chưa có chi tiêu nào",
                            color = Color.Red,
                            fontSize = 20.sp
                        )
                    } else {
                        LazyColumn(
                            modifier = Modifier.fillMaxSize(),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            item {
                                ListChiTieuColumn(
                                    chhitieuList,
                                    modifier = Modifier
                                )
                            }
                        }
                    }
                }

                is UiState.Loading -> DotLoading()
                else -> Log.d("Error", "Error")
            }
        }
    }
}


@Composable
@Preview
fun KhoanChiDetailPreview(){

}