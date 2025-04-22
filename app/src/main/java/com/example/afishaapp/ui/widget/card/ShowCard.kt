package com.example.afishaapp.ui.widget.card

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.afishaapp.R
import com.example.afishaapp.app.support.ConvertDate
import com.example.afishaapp.app.support.ConvertInfo
import com.example.afishaapp.data.module.movieShow.PriceTime
import com.example.afishaapp.data.module.movieShow.Show

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun ShowCard(
    show: Show,
    onClick: () -> Unit
) {
    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        Text(
            text = ConvertInfo.convertTitle(show.place.title),
            fontSize = 17.sp,
            fontWeight = FontWeight.Bold
        )

        Text(
            text = show.place.address,
            fontSize = 14.sp
        )

        if (show.place.subway.isNotEmpty()) {
            SubwayRow(show.place.subway)
        }

        FlowRow(
            modifier = Modifier.padding(top = 15.dp),
            horizontalArrangement = Arrangement.spacedBy(5.dp),
            verticalArrangement = Arrangement.spacedBy(5.dp)
        ) {
            show.priceTime.forEach {
                TimeAndPriceCard(
                    priceTime = it,
                    onClick = onClick
                )
            }
        }
    }
}

@Composable
private fun TimeAndPriceCard(
    priceTime: PriceTime,
    onClick: () -> Unit
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.clickable(onClick = onClick)
    ) {
        Box(
            modifier = Modifier
                .clip(RoundedCornerShape(20.dp))
                .background(MaterialTheme.colorScheme.primary)
        ) {
            Text(
                text = ConvertDate.convertShowTime(priceTime.time.toLong()),
                fontSize = 15.sp,
                color = Color.White,
                fontWeight = FontWeight.Medium,
                modifier = Modifier
                    .padding(20.dp, 5.dp)
                    .align(Alignment.Center)
            )
        }

        Text(
            text = priceTime.price,
            fontSize = 13.sp
        )
    }
}

@Composable
private fun SubwayRow(text: String) {
    Row(
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            painter = painterResource(R.drawable.ic_metro_msk),
            contentDescription = null,
            tint = Color.Unspecified,
            modifier = Modifier
                .padding(end = 5.dp)
                .size(20.dp)
        )

        Text(
            text = text,
            fontSize = 13.sp,
            color = Color.Gray
        )
    }
}