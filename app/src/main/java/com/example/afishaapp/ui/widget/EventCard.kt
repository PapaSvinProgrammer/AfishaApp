package com.example.afishaapp.ui.widget

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import com.example.afishaapp.R
import com.example.afishaapp.app.support.ConvertData
import com.example.afishaapp.data.module.event.Event

@Composable
fun EventCard(
    event: Event
) {
    Column(
        modifier = Modifier
            .width(270.dp)
            .padding(10.dp, 0.dp, 0.dp, 0.dp)
    ) {
        AsyncImage(
            model = event.images[0].image,
            contentDescription = "",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(270.dp, 150.dp)
                .clip(RoundedCornerShape(15.dp)),
            error = painterResource(R.drawable.ic_error)
        )

        Text(
            text = ConvertData.convertTitle(event.shortTitle),
            fontSize = 15.sp,
            fontWeight = FontWeight.Medium,
            color = Color.Black,
            modifier = Modifier.padding(0.dp, 10.dp, 0.dp, 0.dp),
            maxLines = 2,
            overflow = TextOverflow.Ellipsis
        )

        Text(
            text = ConvertData.convertDatesRange(event.dates),
            fontSize = 14.sp,
            fontWeight = FontWeight.Normal,
            color = Color.Gray
        )

        Text(
            text = ConvertData.convertPrice(event.price),
            fontSize = 15.sp,
            fontWeight = FontWeight.Medium,
            color = Color.Black,
            modifier = Modifier.padding(0.dp, 5.dp, 0.dp, 0.dp)
        )
    }
}