package com.example.afishaapp.ui.widget.material

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ListItem
import androidx.compose.material3.ListItemDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SearchBar
import androidx.compose.material3.SearchBarDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.itemContentType
import androidx.paging.compose.itemKey
import com.example.afishaapp.R
import com.example.afishaapp.app.utils.convertData.ConvertInfo
import com.example.afishaapp.data.module.search.ResultItem
import com.example.afishaapp.data.room.searchHistory.SearchHistoryEntity
import com.example.afishaapp.ui.widget.endlessLazy.EndlessLazyColumn

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchLayout(
    query: String,
    expanded: Boolean,
    onQueryChange: (String) -> Unit,
    onExpandedChange: (Boolean) -> Unit,
    onClick: (ResultItem) -> Unit,
    onLoadMore: () -> Unit,
    searchResult: List<ResultItem>,
    historyResult: LazyPagingItems<SearchHistoryEntity>
) {
    Box(modifier = Modifier.fillMaxWidth()) {
        SearchBar(
            modifier = Modifier.align(Alignment.TopCenter),
            inputField = {
                SearchBarDefaults.InputField(
                    query = query,
                    onQueryChange = onQueryChange,
                    onSearch = { onExpandedChange(false) },
                    expanded = expanded,
                    onExpandedChange = onExpandedChange,
                    leadingIcon = {
                        LeadingIcon(
                            expanded = expanded,
                            onExpandedChange = onExpandedChange
                        )
                    },
                    trailingIcon = {
                        TrailingIcon(
                            query = query,
                            expanded = expanded,
                            onQueryChange = onQueryChange,
                            onExpandedChange = onExpandedChange
                        )
                    },
                    placeholder = { Text(stringResource(R.string.search_title)) }
                )
            },
            expanded = expanded,
            onExpandedChange = onExpandedChange
        ) {
            if (query.isEmpty()) {
                HistoryLayout(
                    lazyPagingItems = historyResult,
                    onQueryChange = onQueryChange
                )
            }
            else {
                SearchResultLayout(
                    searchResult = searchResult,
                    onClick = onClick,
                    onLoadMore = onLoadMore
                )
            }
        }
    }
}

@Composable
private fun TrailingIcon(
    query: String,
    expanded: Boolean,
    onQueryChange: (String) -> Unit,
    onExpandedChange: (Boolean) -> Unit
) {
    if (!expanded) {
        IconButton(onClick = { }) {
            Icon(
                imageVector = Icons.Default.MoreVert,
                contentDescription = null
            )
        }
    }
    else {
        IconButton(
            onClick = { if (query.isEmpty()) onExpandedChange(false) else onQueryChange("") }
        ) {
            Icon(
                imageVector = Icons.Default.Close,
                contentDescription = null
            )
        }
    }
}

@Composable
private fun LeadingIcon(
    expanded: Boolean,
    onExpandedChange: (Boolean) -> Unit
) {
    val icon = if (expanded) Icons.AutoMirrored.Default.ArrowBack else Icons.Default.Search

    IconButton(onClick = { onExpandedChange(!expanded) }) {
        Icon(
            imageVector = icon,
            contentDescription = null
        )
    }
}

@Composable
private fun HistoryLayout(
    lazyPagingItems: LazyPagingItems<SearchHistoryEntity>,
    onQueryChange: (String) -> Unit
) {
    LazyColumn(modifier = Modifier.fillMaxWidth()) {
        items(
            count = lazyPagingItems.itemCount,
            key = lazyPagingItems.itemKey { it.id },
            contentType = lazyPagingItems.itemContentType { "Search item" },
            itemContent = { index ->
                val entity = lazyPagingItems[index]

                entity?.let {
                    ListItem(
                        modifier = Modifier.clickable { onQueryChange(it.query) },
                        headlineContent = { Text(text = ConvertInfo.convertTitle(it.query)) },
                        colors = ListItemDefaults.colors(
                            containerColor = MaterialTheme.colorScheme.surfaceContainerHigh
                        ),
                        leadingContent = {
                            Icon(
                                painter = painterResource(R.drawable.ic_history),
                                contentDescription = null
                            )
                        }
                    )
                    HorizontalDivider(
                        modifier = Modifier.padding(start = 54.dp),
                        color = Color.Gray
                    )
                }
            }
        )
    }
}

@Composable
private fun SearchResultLayout(
    searchResult: List<ResultItem>,
    onClick: (ResultItem) -> Unit,
    onLoadMore: () -> Unit
) {
    EndlessLazyColumn(
        items = searchResult,
        loadMore = onLoadMore
    ) {
        ListItem(
            modifier = Modifier.clickable { onClick(it) },
            headlineContent = { Text(text = ConvertInfo.convertTitle(it.title)) },
            colors = ListItemDefaults.colors(
                containerColor = MaterialTheme.colorScheme.surfaceContainerHigh
            ),
            leadingContent = {
                Icon(
                    imageVector = Icons.Default.Search,
                    contentDescription = null
                )
            },
            trailingContent = {
                Icon(
                    imageVector = Icons.AutoMirrored.Default.KeyboardArrowRight,
                    contentDescription = null
                )
            }
        )

        HorizontalDivider(
            modifier = Modifier.padding(start = 54.dp),
            color = Color.Gray
        )
    }
}