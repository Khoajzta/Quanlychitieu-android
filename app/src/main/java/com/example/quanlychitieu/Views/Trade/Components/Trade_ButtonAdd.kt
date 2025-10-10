package com.example.quanlychitieu.Views.Trade.Components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.quanlychitieu.ui.theme.Dimens.PaddingBody
import com.example.quanlychitieu.ui.theme.Dimens.RadiusLarge
import com.example.quanlychitieu.ui.theme.PrimaryColor

@Composable
fun TradeButtonAdd(
    modifier: Modifier = Modifier,
    onClick: () -> Unit = {}
){
    Button(
        onClick = onClick,
        modifier = modifier
            .fillMaxWidth()
            .padding(PaddingBody),
        colors = ButtonDefaults.buttonColors(
            containerColor = PrimaryColor
        ),
        elevation = ButtonDefaults.buttonElevation(12.dp),
        shape = RoundedCornerShape(RadiusLarge)

    ) {
        Text(
            text = "Thêm giao dịch",
            color = Color.White,
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold
        )
    }
}

@Composable
@Preview
fun AddPreview(){
    TradeButtonAdd(modifier = Modifier)
}
