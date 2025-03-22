package com.example.afishaapp.ui.widget.shimmer.card

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.example.afishaapp.ui.widget.shimmer.shimmerEffect

@Composable
fun ShimmerImageCard() {
    Box(
        modifier = Modifier
            .clip(RoundedCornerShape(15.dp))
            .width(270.dp)
            .height(180.dp)
            .shimmerEffect()
    )
}