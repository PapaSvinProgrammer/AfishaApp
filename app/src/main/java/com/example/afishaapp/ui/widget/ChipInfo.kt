package com.example.afishaapp.ui.widget

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.afishaapp.ui.theme.LightGray

@Preview(showBackground = true)
@Composable
fun ChipInfo(
    modifier: Modifier = Modifier,
    title: String = "Title",
    subtitle: String = "Subtitle",
    icon: @Composable () -> Unit = {  }
) {
    Box(
        modifier = modifier
    ) {
        Row(
            modifier = Modifier
                .clip(RoundedCornerShape(10.dp))
                .background(LightGray)
                .padding(10.dp, 5.dp)
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
}