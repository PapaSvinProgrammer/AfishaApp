package com.example.afishaapp.ui.widget.shimmer.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.afishaapp.ui.widget.shimmer.text.ShimmerFillText
import com.example.afishaapp.ui.widget.shimmer.chip.ShimmerAboutChip
import com.example.afishaapp.ui.widget.shimmer.text.ShimmerText

@Composable
fun ShimmerAboutEvent() {
    Spacer(modifier = Modifier.height(10.dp))

    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(20.dp)
    ) {
        Box(
            modifier = Modifier.weight(1f)
        ) {
            ShimmerAboutChip()
        }

        Box(
            modifier = Modifier.weight(1f)
        ) {
            ShimmerAboutChip()
        }
    }

    Spacer(modifier = Modifier.height(40.dp))

    repeat(15) {
        ShimmerFillText()
        Spacer(modifier = Modifier.height(5.dp))
    }

    repeat(6) {
        Spacer(modifier = Modifier.height(15.dp))
        ShimmerText(width = 150.dp)
        Spacer(modifier = Modifier.height(5.dp))
        ShimmerText(width = 80.dp)
    }
}