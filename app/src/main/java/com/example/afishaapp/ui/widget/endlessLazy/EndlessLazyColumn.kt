package com.example.afishaapp.ui.widget.endlessLazy

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp

@Composable
fun <T: Any> EndlessLazyColumn(
    modifier: Modifier = Modifier,
    listState: LazyListState = rememberLazyListState(),
    contentPadding: PaddingValues = PaddingValues(0.dp),
    verticalArrangement: Arrangement.Vertical = Arrangement.Top,
    horizontalDivider: Boolean = false,
    items: List<T>,
    loadMore: () -> Unit,
    itemContent: @Composable (T) -> Unit,
) {
    val reachBottom: Boolean by remember {
        derivedStateOf {
            listState.reachBottom()
        }
    }

    LaunchedEffect(reachBottom) {
        if (reachBottom) {
            loadMore.invoke()
        }
    }

    LazyColumn(
        modifier = modifier,
        state = listState,
        contentPadding = contentPadding,
        verticalArrangement = verticalArrangement
    ) {
        itemsIndexed(items) { index, item ->
            itemContent.invoke(item)

            if (horizontalDivider && index != items.size) {
                HorizontalDivider(
                    thickness = 2.dp,
                    modifier = Modifier
                        .padding(10.dp, 0.dp)
                        .clip(RoundedCornerShape(5.dp))
                )
            }
        }
    }
}

fun LazyListState.reachBottom(buffer: Int = 1): Boolean {
    val lastVisibleItem = this.layoutInfo.visibleItemsInfo.lastOrNull()
    return lastVisibleItem?.index != 0 && lastVisibleItem?.index == this.layoutInfo.totalItemsCount - buffer
}