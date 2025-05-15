package com.example.afishaapp.ui.widget.chip

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.afishaapp.ui.theme.DefaultPadding
import com.example.afishaapp.ui.theme.LightGray

@Composable
fun AboutChipInfo(
    modifier: Modifier = Modifier,
    title: String,
    subTitle: String = "",
    titleSize: TextUnit = 17.sp,
    subtitleSize: TextUnit = 15.sp,
    icon: @Composable () -> Unit = { }
) {
    Box(
        modifier = modifier
            .clip(RoundedCornerShape(10.dp))
            .fillMaxWidth()
            .background(LightGray)
    ) {
        Column(
            modifier = Modifier
                .padding(vertical = DefaultPadding)
                .align(Alignment.Center),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            icon()

            Text(
                text = title,
                fontWeight = FontWeight.Bold,
                fontSize = titleSize,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 5.dp),
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )

            if (subTitle.isNotEmpty()) {
                Text(
                    text = subTitle,
                    fontSize = subtitleSize,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.fillMaxWidth()
                )
            }
        }
    }
}