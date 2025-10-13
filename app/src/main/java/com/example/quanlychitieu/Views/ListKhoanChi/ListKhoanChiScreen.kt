package com.example.quanlychitieu.Views.ListKhoanChi

import Header
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.example.quanlychitieu.Components.CustomFAB
import com.example.quanlychitieu.Const.listKhoanChiConst.listKhoanChi
import com.example.quanlychitieu.ui.theme.BackgroundColor

@Composable
fun ListKhoanChiScreen(
    navController: NavController
){
    Scaffold (
        containerColor = BackgroundColor,
        topBar = {
            Header(navController, Modifier.windowInsetsPadding(WindowInsets.statusBars), title = "Danh sách khoản chi")
        },
        floatingActionButton = {
            CustomFAB(onClick = {navController.navigate(Screen.AddKhoanChi.route)})
        },
        contentWindowInsets = WindowInsets(0, 0, 0, 0)
    ){innerPadding ->
        LazyColumn(
            modifier = Modifier.padding(innerPadding)
        ){
            item {
                ListKhoanChiColumn(
                    modifier = Modifier,
                    listKhoanChi = listKhoanChi
                )
            }
        }
    }
}