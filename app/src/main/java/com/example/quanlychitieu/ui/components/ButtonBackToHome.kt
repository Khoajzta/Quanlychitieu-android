package com.example.quanlychitieu.Components

import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@Composable
fun ButtonBackToHome(
    navController: NavController,
    userId : Int
){
    IconButton(onClick = {
        navController.navigate(Screen.Home.createRoute(userId)) {
            popUpTo(0)
            launchSingleTop = true
        }
    }) {
        Icon(
            imageVector = Icons.Outlined.Home,
            contentDescription = "Home",
            tint = Color.Black,
            modifier = Modifier.size(22.dp)
        )
    }
}
