package com.example.quanlychitieu.ui.Views.home.components

import HomeTotalMoney
import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.quanlychitieu.Utils.listKhoanChiConst.listTaiKhoan
import com.example.quanlychitieu.domain.model.TaiKhoanModel
import com.example.quanlychitieu.ui.components.CardTaikhoanPhu
import com.example.quanlychitieu.ui.theme.Dimens.PaddingBody
import kotlin.math.abs

@Composable
fun CardTaiKhoanRow(
    modifier: Modifier = Modifier,
    listTaiKhoan: List<TaiKhoanModel>,
    tongTienDuKien :Int = 0
) {
    // Lấy tài khoản chính nếu có, nếu không thì null
    val taiKhoanChinh: TaiKhoanModel? = listTaiKhoan.firstOrNull { it.loai_taikhoan == 1 }


    LazyRow(
        modifier = modifier
            .padding(horizontal = PaddingBody)
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
    ) {
        // Chỉ hiển thị HomeTotalMoney nếu taiKhoanChinh không null
        taiKhoanChinh?.let { tkChinh ->
            item {
                HomeTotalMoney(
                    modifier = Modifier
                        .graphicsLayer {
                            scaleX = 1f
                            scaleY = 1f
                        },
                    taikhoan = tkChinh,
                    tongTienDuKien = tongTienDuKien
                )
            }
        }

        // Hiển thị các tài khoản phụ
        items(listTaiKhoan.let { it.filter { it.loai_taikhoan == 0 } }) { item ->
            CardTaikhoanPhu(modifier = Modifier, item)
        }
    }
}




@Composable
@Preview
fun CardTaiKhoanRowPreviw(){

//    CardTaiKhoanRow(listTaiKhoan = listTaiKhoan)
}