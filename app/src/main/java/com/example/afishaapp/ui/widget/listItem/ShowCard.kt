package com.example.afishaapp.ui.widget.listItem

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.afishaapp.app.utils.convertData.ConvertDate
import com.example.afishaapp.app.utils.convertData.ConvertInfo
import com.example.afishaapp.data.module.movieShow.PriceTime
import com.example.afishaapp.data.module.movieShow.Show
import com.example.afishaapp.ui.widget.row.SubwayRow

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
            SubwayRow(
                location = show.place.location,
                subway = show.place.subway
            )
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
                color = MaterialTheme.colorScheme.onPrimary,
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