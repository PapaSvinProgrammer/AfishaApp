package com.example.afishaapp.ui.widget.shimmer.card

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.example.afishaapp.ui.widget.shimmer.shimmerEffect

@Composable
fun ShimmerEventCard() {
    Column(
        modifier = Modifier.wrapContentSize()
    ) {
        Box(
            modifier = Modifier
                .size(width = 260.dp, height = 180.dp)
                .clip(RoundedCornerShape(10.dp))
                .shimmerEffect()
        )

        Spacer(modifier = Modifier.height(10.dp))

        Box(
            modifier = Modifier
                .width(200.dp)
                .height(12.dp)
                .clip(RoundedCornerShape(5.dp))
                .shimmerEffect()
        )

        Spacer(modifier = Modifier.height(5.dp))

        Box(
            modifier = Modifier
                .width(140.dp)
                .height(12.dp)
                .clip(RoundedCornerShape(5.dp))
                .shimmerEffect()
        )

        Spacer(modifier = Modifier.height(5.dp))

        Box(
            modifier = Modifier
                .width(100.dp)
                .height(12.dp)
                .clip(RoundedCornerShape(5.dp))
                .shimmerEffect()
        )
    }
}