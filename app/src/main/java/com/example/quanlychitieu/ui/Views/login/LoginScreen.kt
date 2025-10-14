package com.example.quanlychitieu.Views.login

import TextColumn
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.quanlychitieu.R
import com.example.quanlychitieu.Views.login.components.ButtonLoginGoogle
import com.example.quanlychitieu.ui.ViewModels.NguoiDungViewModel
import com.example.quanlychitieu.ui.auth.rememberGoogleSignIn

@Composable
fun LoginScreen(
    navController: NavController,
    viewModel: NguoiDungViewModel = hiltViewModel(),
    onLoginSuccess: ()-> Unit = {}
) {

    val isLoading by viewModel.isLoading.collectAsState()

    var listColor = listOf(Color(0xFF73B5E1),
        Color(0xFF753a88))

    val onGoogleLoginClick = rememberGoogleSignIn(viewModel, navController)



    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                Brush.verticalGradient(
                    colors = listColor
                )
            )
    ) {

        if (isLoading) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.Black.copy(alpha = 0.4f)),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator(color = Color.White)
            }
        }
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

            ButtonLoginGoogle(modifier = Modifier, onClick = onGoogleLoginClick)

            Spacer(modifier = Modifier.height(50.dp))
        }
    }
}

@Preview
@Composable
fun PreviewLoginScreen() {
}
