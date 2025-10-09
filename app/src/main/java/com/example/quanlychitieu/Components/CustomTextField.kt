package com.example.quanlychitieu.Components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Key
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.quanlychitieu.ui.theme.Dimens.PadingExtraSmall
import com.example.quanlychitieu.ui.theme.Dimens.RadiusFull
import com.example.quanlychitieu.ui.theme.Dimens.SpaceLarge
import com.example.quanlychitieu.ui.theme.QuanLyChiTieuTheme


@Composable
fun CusTomTextField(
    modifier: Modifier = Modifier,
    value: String,
    onValueChange: (String) -> Unit,
    leadingIcon: (@Composable (() -> Unit))? = null,
    placeholder: String,
) {
    Box(
        modifier = modifier
            .shadow(
                elevation = 11.dp,
                shape = RoundedCornerShape(RadiusFull),
                clip = false
            )

            .background(
                color = Color.White,
                shape = RoundedCornerShape(RadiusFull)
            )

            .padding(PadingExtraSmall)
    ) {
        OutlinedTextField(
            value = value,
            onValueChange = onValueChange,
            leadingIcon = leadingIcon,
            modifier = Modifier.fillMaxWidth(),
            placeholder = {
                Text(
                    placeholder,
                    color = Color.Black // chữ mờ nhẹ
                )
            },
            shape = RoundedCornerShape(RadiusFull),
            colors = TextFieldDefaults.colors(
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                disabledIndicatorColor = Color.Transparent,
                errorIndicatorColor = Color.Transparent,
                focusedContainerColor = Color.Transparent,
                unfocusedContainerColor = Color.Transparent,
            )
        )
    }
}



@Composable
fun PasswordTextField(
    modifier: Modifier = Modifier,
    value: String,
    onValueChange: (String) -> Unit
) {
    // trạng thái ẩn/hiện mật khẩu
    var passwordVisible by remember { mutableStateOf(false) }

    val gradientBrush = Brush.verticalGradient(
        colors = listOf(Color(0xFF4CAF50), Color(0xFF2196F3))
    )
    Box(
        modifier = modifier
            .shadow(
                elevation = 8.dp,
                shape = RoundedCornerShape(RadiusFull),
                clip = false
            )

            .padding(PadingExtraSmall)
    ){

        OutlinedTextField(
            value = value,
            onValueChange = onValueChange,

            modifier = Modifier.fillMaxWidth(),
            placeholder = {
                Text(
                    "Mật khẩu"
                )
            },
            visualTransformation = if (passwordVisible) {
                VisualTransformation.None
            } else {
                PasswordVisualTransformation()
            },

            leadingIcon = {
                Icon(
                    imageVector = Icons.Default.Key,
                    contentDescription = null
                )
            },

            trailingIcon = {
                val image = if (passwordVisible)
                    Icons.Default.Visibility
                else
                    Icons.Filled.VisibilityOff

                IconButton(onClick = { passwordVisible = !passwordVisible }) {
                    Icon(imageVector = image, contentDescription = null)
                }
            },

            shape = RoundedCornerShape(RadiusFull),
            colors = TextFieldDefaults.colors(
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                disabledIndicatorColor = Color.Transparent,
                errorIndicatorColor = Color.Transparent,
                focusedContainerColor = Color.White,
                unfocusedContainerColor = Color.White,
            )
        )
    }

}



@Preview(showBackground = true)
@Composable
fun CustomTextFieldPreview() {
    QuanLyChiTieuTheme {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(SpaceLarge),

        ) {
            CusTomTextField(
                value = "",
                onValueChange = {},
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Default.Search,
                        contentDescription = null
                    )
                },
                placeholder = "Tìm kiếm"
            )
            CusTomTextField(
                value = "",
                onValueChange = {},
                placeholder = "Email",
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Default.Email,
                        contentDescription = null
                    )
                },
            )

            PasswordTextField(
                modifier = Modifier,
                value = "",
                onValueChange = {}
            )


        }
    }
}




