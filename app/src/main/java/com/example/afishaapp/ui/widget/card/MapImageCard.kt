package com.example.afishaapp.ui.widget.card

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage

@Composable
fun MapImageCard(
    imageUrl: String
) {
    AsyncImage(
        model = imageUrl,
        modifier = Modifier
            .clip(RoundedCornerShape(15.dp))
            .fillMaxWidth()
            .height(200.dp),
        contentDescription = null,
        contentScale = ContentScale.Crop
    )
}