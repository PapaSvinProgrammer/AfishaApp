package com.example.afishaapp.ui.widget

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Preview(showBackground = true)
@Composable
fun ChipInfo(
    modifier: Modifier = Modifier,
    title: String = "Title",
    subtitle: String = "Subtitle"
) {
    Box(
        modifier = modifier
    ) {
        Column(
            modifier = Modifier
                .clip(RoundedCornerShape(10.dp))
                .background(Color(0xffe9ecef))
                .padding(10.dp, 5.dp)
                .wrapContentSize()
        ) {
            Text(
                text = title,
                color = Color.Black,
                fontWeight = FontWeight.Medium,
                fontSize = 13.sp,
                maxLines = 1
            )

            Text(
                text = subtitle,
                color = Color.Gray,
                fontWeight = FontWeight.Medium,
                fontSize = 12.sp,
                maxLines = 1
            )
        }
    }
}