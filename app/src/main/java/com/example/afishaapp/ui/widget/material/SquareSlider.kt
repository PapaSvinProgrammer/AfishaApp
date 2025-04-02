package com.example.afishaapp.ui.widget.material

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Slider
import androidx.compose.material3.SliderDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SquareSlider(
    value: Float,
    onValueChange: (Float) -> Unit,
    steps: Int = 0,
    valueRange: ClosedFloatingPointRange<Float> = 0f..1f
) {
    Slider(
        value = value,
        onValueChange = onValueChange,
        steps = steps,
        valueRange = valueRange,
        thumb = {
            Box(
                modifier = Modifier
                    .size(30.dp)
                    .background(
                        color = MaterialTheme.colorScheme.primary,
                        shape = CutCornerShape(20.dp)
                    )
            )
        },
        track = {
            SliderDefaults.Track(
                sliderState = it,
                thumbTrackGapSize = 2.dp,
                enabled = true
            )
        }
    )
}