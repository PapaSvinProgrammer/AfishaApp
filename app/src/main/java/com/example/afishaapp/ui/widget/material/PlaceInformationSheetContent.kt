package com.example.afishaapp.ui.widget.material

import androidx.compose.foundation.gestures.snapping.SnapPosition
import androidx.compose.foundation.gestures.snapping.rememberSnapFlingBehavior
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.afishaapp.R
import com.example.afishaapp.app.utils.ConvertInfo
import com.example.afishaapp.data.module.image.ImageItem
import com.example.afishaapp.data.module.place.Place
import com.example.afishaapp.ui.theme.DefaultPadding
import com.example.afishaapp.ui.widget.card.ImageCard
import com.example.afishaapp.ui.widget.row.ControlButtonsRow
import com.example.afishaapp.ui.widget.row.MetroRow
import com.yandex.mapkit.geometry.Point

@Composable
fun PlaceInformationSheetContent(
    pagerState: PagerState,
    list: List<Place>,
    mapButtonClick: (Place) -> Unit,
    moreButtonClick: (Place) -> Unit,
    moveToPoint: (Point) -> Unit
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        if (list.size > 1) {
            Spacer(modifier = Modifier.height(10.dp))
            Row(
                modifier = Modifier.padding(horizontal = DefaultPadding)
            ) {
                HorizontalCirclePagerIndicator(pagerState)
            }
        }

        HorizontalPager(
            state = pagerState,
            modifier = Modifier.fillMaxWidth()
        ) { page ->
            PlaceInformationPagerContent(
                place = list[page],
                moveToPoint = { moveToPoint.invoke(it) }
            )
        }

        ControlButtonsRow(
            mapButtonClick = { mapButtonClick.invoke(list[pagerState.currentPage]) },
            moreButtonClick = { moreButtonClick.invoke(list[pagerState.currentPage]) }
        )
    }
}

@Composable
fun PlaceInformationPagerContent(
    place: Place,
    moveToPoint: (Point) -> Unit
) {
    Column {
        Spacer(modifier = Modifier.height(10.dp))

        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = DefaultPadding),
            text = ConvertInfo.convertTitle(place.title),
            textAlign = TextAlign.Start,
            fontSize = 17.sp,
            fontWeight = FontWeight.Bold
        )

        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    top = 10.dp,
                    start = DefaultPadding,
                    end = DefaultPadding
                ),
            text = stringResource(R.string.address_param, place.address),
            textAlign = TextAlign.Start,
            fontSize = 15.sp
        )

        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = DefaultPadding),
            text = stringResource(R.string.number_param, place.phone),
            textAlign = TextAlign.Start,
            fontSize = 15.sp
        )

        MetroRow(place)
        ImagesRow(place.images)

        OutlinedButton(
            modifier = Modifier.padding(
                start = DefaultPadding,
                top = 10.dp
            ),
            onClick = {
                val point = Point(place.coordinates.lat, place.coordinates.lon)
                moveToPoint.invoke(point)
            }
        ) {
            Icon(
                painter = painterResource(R.drawable.ic_pin_into_circle),
                contentDescription = null,
                tint = Color.Black
            )

            Text(
                text = "Показать на карте",
                color = Color.Black,
                modifier = Modifier.padding(start = 10.dp)
            )
        }
    }
}

@Composable
private fun ImagesRow(images: List<ImageItem>?) {
    val listState = rememberLazyListState()
    val flingBehavior = rememberSnapFlingBehavior(listState, SnapPosition.Start)

    if (!images.isNullOrEmpty()) {
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    vertical = 20.dp,
                    horizontal = DefaultPadding
                ),
            text = stringResource(R.string.images),
            textAlign = TextAlign.Start,
            fontWeight = FontWeight.Bold,
            fontSize = 17.sp
        )

        LazyRow(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(10.dp),
            contentPadding = PaddingValues(horizontal = DefaultPadding),
            state = listState,
            flingBehavior = flingBehavior
        ) {
            items(images) {
                ImageCard(it.thumbnails.highImage)
            }
        }
    }
}