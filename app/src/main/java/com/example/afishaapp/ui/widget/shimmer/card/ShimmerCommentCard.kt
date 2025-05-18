package com.example.afishaapp.ui.widget.shimmer.card

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.example.afishaapp.ui.widget.shimmer.shimmerEffect
import com.example.afishaapp.ui.widget.shimmer.text.ShimmerFillText
import com.example.afishaapp.ui.widget.shimmer.text.ShimmerText

@Composable
fun ShimmerCommentCard() {
    ElevatedCard(
        elevation = CardDefaults.cardElevation(6.dp),
        colors = CardDefaults.cardColors(MaterialTheme.colorScheme.surfaceContainer),
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(10.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box(
                    modifier = Modifier
                        .size(48.dp)
                        .clip(CircleShape)
                        .shimmerEffect()
                )

                Column(
                    modifier = Modifier.padding(start = 10.dp)
                ) {
                    ShimmerText(width = 180.dp)
                    Spacer(modifier = Modifier.height(5.dp))
                    ShimmerText()
                }
            }

            Spacer(modifier = Modifier.height(15.dp))

            repeat(5) {
                ShimmerFillText()
                Spacer(modifier = Modifier.height(5.dp))
            }
        }
    }
}