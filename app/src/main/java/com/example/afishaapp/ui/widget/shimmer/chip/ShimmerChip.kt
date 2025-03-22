package com.example.afishaapp.ui.widget.shimmer.chip

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.afishaapp.ui.widget.shimmer.shimmerEffect

@Composable
fun ShimmerChip(
    height: Dp = 30.dp,
    width: Dp = 100.dp
) {
    Box(
        modifier = Modifier
            .clip(RoundedCornerShape(5.dp))
            .size(height = height, width = width)
            .shimmerEffect()
    )
}