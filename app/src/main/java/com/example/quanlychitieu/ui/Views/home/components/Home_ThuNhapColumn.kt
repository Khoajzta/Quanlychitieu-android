package com.example.quanlychitieu.ui.Views.home.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.quanlychitieu.Components.CardThuNhap
import com.example.quanlychitieu.domain.model.ThuNhapModel
import com.example.quanlychitieu.ui.theme.Dimens.PaddingBody
import com.example.quanlychitieu.ui.theme.PrimaryColor

@Composable
fun HomeThuNhapColumn(
    navController: NavController,
    userId : Int,
    listThuNhap : List<ThuNhapModel>
){
    Column(
        modifier = Modifier.wrapContentHeight(),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Thu nhập gần đây",
                fontWeight = FontWeight.Bold,
                color = Color.Black,
                fontSize = 15.sp
            )

            TextButton(
                onClick = {
                    navController.navigate(Screen.ListThuNhapTheoThang.createRoute(userId))
                }
            ) {
                Text(
                    text = "Xem tất cả",
                    fontWeight = FontWeight.Bold,
                    color = PrimaryColor,
                    fontSize = 15.sp
                )
            }
        }

        for(item in listThuNhap){
            CardThuNhap(thuNhap = item)
        }
    }
}