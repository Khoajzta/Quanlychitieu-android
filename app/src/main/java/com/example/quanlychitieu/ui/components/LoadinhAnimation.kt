package com.example.quanlychitieu.Components

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun DotLoading() {
    val dots = listOf(0, 1, 2)
    val scale = remember { Animatable(0.5f) }

    LaunchedEffect(Unit) {
        while (true) {
            scale.animateTo(1f, animationSpec = tween(300))
            scale.animateTo(0.5f, animationSpec = tween(300))
        }
    }

    Row(horizontalArrangement = Arrangement.spacedBy(6.dp)) {
        dots.forEach {
            Box(
                modifier = Modifier
                    .size((20 * scale.value).dp)
                    .background(Color.Gray, CircleShape)
            )
        }
    }
}
