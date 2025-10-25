package com.example.quanlychitieu.ui.Views.NganSach.components

import Screen
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.History
import androidx.compose.material.icons.filled.SyncAlt
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.quanlychitieu.Components.CustomButton

@Composable
fun ChucNangRow(
    modifier: Modifier = Modifier,
    navController: NavController,
    userId:Int,
){
    LazyRow(
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(5.dp)
    ) {
        item {
            CustomButton(
                title = "Chuyển tiền",
                onClick = {
                    navController.navigate(Screen.ChuyenTien.createRoute(userId))
                },
                icon = Icons.Default.SyncAlt
            )
        }
        item {
            CustomButton(
                title = "Lịch sử chuyển tiền",
                onClick = {

                },
                icon = Icons.Default.History
            )
        }

        item {
            CustomButton(
                title = "Tạo tài khoản mới",
                onClick = {
                    navController.navigate(Screen.AddTaiKhoan.createRoute(userId))
                },
                icon = Icons.Default.AddCircle
            )
        }
    }
}