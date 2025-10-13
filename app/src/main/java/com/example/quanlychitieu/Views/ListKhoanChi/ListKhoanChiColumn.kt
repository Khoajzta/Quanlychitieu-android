package com.example.quanlychitieu.Views.ListKhoanChi

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.quanlychitieu.Components.CardKhoanChiDetail
import com.example.quanlychitieu.models.KhoanChiModel
import com.example.quanlychitieu.ui.theme.Dimens.PaddingBody

@Composable
fun ListKhoanChiColumn(
    listKhoanChi: List<KhoanChiModel>,
    modifier: Modifier = Modifier
){
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = PaddingBody),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        for (i in listKhoanChi.indices) {
            CardKhoanChiDetail(
                item = listKhoanChi[i],
                modifier = Modifier
            )
        }
    }
}