package com.example.afishaapp.ui.widget

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyGridState
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun <T: Any> EndlessLazyVerticalGrid(
    modifier: Modifier = Modifier,
    columns: GridCells,
    contentPadding: PaddingValues = PaddingValues(0.dp),
    verticalArrangement: Arrangement.Vertical = Arrangement.Top,
    horizontalArrangement: Arrangement.Horizontal = Arrangement.Start,
    listState: LazyGridState = rememberLazyGridState(),
    itemContent: @Composable (T) -> Unit,
    items: List<T>,
    loadMore: () -> Unit
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

    LazyVerticalGrid(
        columns = columns,
        modifier = modifier,
        contentPadding = contentPadding,
        verticalArrangement = verticalArrangement,
        horizontalArrangement = horizontalArrangement
    ) {
        items(items) { item ->
            itemContent.invoke(item)
        }
    }
}

fun LazyGridState.reachBottom(buffer: Int = 1): Boolean {
    val lastVisibilityItem = this.layoutInfo.visibleItemsInfo.lastOrNull()

    if (lastVisibilityItem?.index != 0) {
        if (lastVisibilityItem?.index == this.layoutInfo.totalItemsCount - buffer) {
            return true
        }
    }

    return false
}