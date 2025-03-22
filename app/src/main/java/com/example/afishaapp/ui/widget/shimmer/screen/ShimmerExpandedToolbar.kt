package com.example.afishaapp.ui.widget.shimmer.screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.afishaapp.ui.widget.shimmer.shimmerEffect

@Composable
fun ShimmerExpandedToolbar() {
    Box(
        modifier = Modifier
            .height(500.dp)
            .fillMaxWidth()
            .shimmerEffect()
    )
}