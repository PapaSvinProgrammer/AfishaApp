package com.example.afishaapp.ui.widget.text

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.sp

@Composable
fun SubtitleTopBar(text: String) {
    Text(
        text = text,
        fontSize = 13.sp,
        overflow = TextOverflow.Ellipsis,
        maxLines = 1,
        lineHeight = 8.sp
    )
}