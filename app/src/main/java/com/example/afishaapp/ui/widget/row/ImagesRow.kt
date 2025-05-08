package com.example.afishaapp.ui.widget.row

import androidx.compose.foundation.gestures.snapping.SnapPosition
import androidx.compose.foundation.gestures.snapping.rememberSnapFlingBehavior
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.afishaapp.R
import com.example.afishaapp.data.module.image.ImageItem
import com.example.afishaapp.ui.theme.DefaultPadding
import com.example.afishaapp.ui.widget.listItem.ImageCard

@Composable
fun ImagesRow(
    list: List<ImageItem>
) {
    val imageListState = rememberLazyListState()
    val imageSnapBehavior = rememberSnapFlingBehavior(imageListState, SnapPosition.Start)

    SelectRow(
        text = stringResource(R.string.images),
        icon = null,
        fontSize = 18.sp,
        fontWeight = FontWeight.Bold
    ) {

    }

    LazyRow(
        contentPadding = PaddingValues(horizontal = DefaultPadding),
        horizontalArrangement = Arrangement.spacedBy(10.dp),
        state = imageListState,
        flingBehavior = imageSnapBehavior
    ) {
        items(list) { imageItem ->
            ImageCard(imageItem.thumbnails.highImage)
        }
    }
}