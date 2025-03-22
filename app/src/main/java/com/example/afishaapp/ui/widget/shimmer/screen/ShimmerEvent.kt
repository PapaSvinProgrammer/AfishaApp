package com.example.afishaapp.ui.widget.shimmer.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.afishaapp.ui.theme.DefaultPadding
import com.example.afishaapp.ui.widget.shimmer.text.ShimmerFillText
import com.example.afishaapp.ui.widget.shimmer.chip.ShimmerChip

@Composable
fun ShimmerEvent() {
    Spacer(modifier = Modifier.height(20.dp))

    Row(
        horizontalArrangement = Arrangement.spacedBy(10.dp),
        modifier = Modifier.padding(horizontal = DefaultPadding)
    ) {
        repeat(6) {
            ShimmerChip(height = 50.dp)
        }
    }

    Spacer(modifier = Modifier.height(20.dp))

    Row(
        horizontalArrangement = Arrangement.spacedBy(10.dp),
        modifier = Modifier.padding(horizontal = DefaultPadding)
    ) {
        repeat(6) {
            ShimmerChip()
        }
    }

    Spacer(modifier = Modifier.height(25.dp))

    Column(
        modifier = Modifier.padding(horizontal = DefaultPadding)
    ) {
        repeat(6) {
            ShimmerFillText()
            Spacer(modifier = Modifier.height(5.dp))
        }
    }
}