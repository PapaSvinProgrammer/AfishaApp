package com.example.afishaapp.ui.widget.shimmer

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.afishaapp.ui.widget.shimmer.card.ShimmerCommentCard

@Composable
fun ShimmerCommentList(padding: PaddingValues) {
    Column(
        modifier = Modifier.padding(padding)
    ) {
        repeat(4) {
            ShimmerCommentCard()
        }
    }
}