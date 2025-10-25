package com.example.quanlychitieu.ui.Views.home.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.quanlychitieu.Components.CardThuNhap
import com.example.quanlychitieu.domain.model.ChiTieuModel
import com.example.quanlychitieu.ui.components.CardChiTieu
import com.example.quanlychitieu.ui.theme.Dimens.PaddingBody

@Composable
fun HomeChiTieuColumn(
    listChiTieu :List<ChiTieuModel>
){
    Column(
        modifier = Modifier.wrapContentHeight(),
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        Text(
            text = "Chi tiêu gần đây",
            fontWeight = FontWeight.Bold,
            color = Color.Black,
            fontSize = 15.sp
        )
        for(item in listChiTieu){
            CardChiTieu(chitieu = item)
        }
    }
}