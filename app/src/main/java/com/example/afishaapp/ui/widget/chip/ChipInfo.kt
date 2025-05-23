package com.example.afishaapp.ui.widget.chip

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Preview(showBackground = true)
@Composable
fun ChipInfo(
    modifier: Modifier = Modifier,
    title: String = "Title",
    subtitle: String = "Subtitle",
    titleSize: TextUnit = 13.sp,
    subtitleSize: TextUnit = 12.sp,
    innerPadding: PaddingValues = PaddingValues(10.dp),
    icon: @Composable () -> Unit = {  }
) {
    Box(modifier = modifier) {
        Row(
            modifier = Modifier
                .clip(RoundedCornerShape(10.dp))
                .background(MaterialTheme.colorScheme.surfaceContainer)
                .padding(innerPadding)
                .wrapContentSize(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            icon()

            Column(
                verticalArrangement = Arrangement.Center,
                modifier = Modifier.wrapContentSize()
            ) {
                Text(
                    text = title,
                    color = MaterialTheme.colorScheme.onSurface,
                    fontWeight = FontWeight.Medium,
                    fontSize = titleSize,
                    maxLines = 1,
                    lineHeight = 10.sp,
                    modifier = Modifier.padding(bottom = 5.dp)
                )

                Text(
                    text = subtitle,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    fontWeight = FontWeight.Medium,
                    fontSize = subtitleSize,
                    maxLines = 1,
                    lineHeight = 9.sp
                )
            }
        }
    }
}