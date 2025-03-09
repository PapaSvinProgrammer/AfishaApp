package com.example.afishaapp.ui.widget.collapsingTopBar

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

val COLLAPSED_TOP_BAR_HEIGHT = 56.dp
val EXPANDED_TOP_BAR_HEIGHT = 500.dp

@Composable
fun ExpandedTopBar(
    expandedTopBarHeight: Dp = EXPANDED_TOP_BAR_HEIGHT,
    content: @Composable () -> Unit
) {
    Box(
        contentAlignment = Alignment.BottomStart,
        modifier = Modifier
            .fillMaxWidth()
            .height(expandedTopBarHeight - COLLAPSED_TOP_BAR_HEIGHT)
    ) {
        content()
    }
}