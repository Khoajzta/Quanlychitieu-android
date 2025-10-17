package com.example.quanlychitieu.ui.Views.KhoanChiDetail.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.quanlychitieu.domain.model.ChiTieuModel
import com.example.quanlychitieu.ui.components.CardChiTieu
import com.example.quanlychitieu.ui.theme.Dimens.PaddingBody
import com.example.quanlychitieu.ui.theme.Dimens.SpaceMedium

@Composable
fun ListChiTieuColumn(
    listChiTieu: List<ChiTieuModel>,
    modifier: Modifier = Modifier
){
    Column(
        modifier = modifier
            .padding(horizontal = PaddingBody),
        verticalArrangement = Arrangement.spacedBy(SpaceMedium)
    ) {
        for (i in listChiTieu.indices) {
            CardChiTieu(
                chitieu = listChiTieu[i],
                modifier = Modifier
            )
        }
    }
}