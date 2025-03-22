package com.example.afishaapp.ui.widget.shimmer.text

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.example.afishaapp.ui.widget.shimmer.shimmerEffect

@Composable
fun ShimmerFillText() {
    Box(
        modifier = Modifier
            .clip(RoundedCornerShape(5.dp))
            .height(13.dp)
            .fillMaxWidth()
            .shimmerEffect()
    )
}