package com.example.quanlychitieu.feature.Views.login

import TextColumn
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.quanlychitieu.R

@Composable
fun LoginScreen(
    onLoginSuccess: ()-> Unit = {}
) {

    var listColor = listOf(Color(0xFF73B5E1),
        Color(0xFF753a88))

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                Brush.verticalGradient(
                    colors = listColor
                )
            )
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {

            Image(
                painter = painterResource(R.drawable.ic_logo),
                contentDescription = null,
                modifier = Modifier.size(120.dp),
                contentScale = ContentScale.Fit
            )

            TextColumn()

            Spacer(modifier = Modifier.height(50.dp))

            ButtonLoginGoogle(modifier = Modifier, onClick = onLoginSuccess)

            Spacer(modifier = Modifier.height(50.dp))
        }
    }
}

@Preview
@Composable
fun PreviewLoginScreen() {
    LoginScreen()
}
