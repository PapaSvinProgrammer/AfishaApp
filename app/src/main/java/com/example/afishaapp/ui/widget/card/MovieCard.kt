package com.example.afishaapp.ui.widget.card

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.material3.ripple
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import com.example.afishaapp.data.module.movie.Movie
import com.example.afishaapp.ui.theme.Green

@Composable
fun MovieCard(
    modifier: Modifier = Modifier,
    movie: Movie,
    onClick: (Movie) -> Unit
) {
    Column(
        modifier = modifier
            .width(180.dp)
            .wrapContentHeight()
            .clip(RoundedCornerShape(10.dp))
            .clickable(
                interactionSource = remember { MutableInteractionSource() },
                indication = ripple(),
                onClick = {
                    onClick.invoke(movie)
                }
            )
    ) {
        Box {
            AsyncImage(
                model = movie.poster.image,
                contentDescription = null,
                modifier = Modifier
                    .height(250.dp)
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(15.dp)),
                contentScale = ContentScale.Crop
            )

            Box(
                modifier = Modifier
                    .wrapContentSize()
                    .padding(5.dp)
                    .clip(RoundedCornerShape(10.dp))
                    .background(Green)
            ) {
                Text(
                    text = movie.imdbRating.toString(),
                    fontWeight = FontWeight.Bold,
                    color = Color.White,
                    modifier = Modifier.padding(5.dp, 0.dp),
                    fontSize = 14.sp
                )
            }
        }

        Text(
            text = movie.title,
            fontSize = 15.sp,
            fontWeight = FontWeight.Medium,
            color = Color.Black,
            modifier = Modifier.padding(0.dp, 10.dp, 0.dp, 0.dp),
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )

        Text(
            text = "${movie.year} год",
            fontSize = 14.sp,
            fontWeight = FontWeight.Normal,
            color = Color.Gray
        )
    }
}