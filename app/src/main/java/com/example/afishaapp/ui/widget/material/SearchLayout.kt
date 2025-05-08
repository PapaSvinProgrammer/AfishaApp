package com.example.afishaapp.ui.widget.material

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ListItem
import androidx.compose.material3.SearchBar
import androidx.compose.material3.SearchBarDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import com.example.afishaapp.R
import com.example.afishaapp.data.module.search.ResultItem

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchLayout(
    query: String,
    expanded: Boolean,
    onQueryChange: (String) -> Unit,
    onExpandedChange: (Boolean) -> Unit,
    onClick: (ResultItem) -> Unit,
    searchResult: List<ResultItem>,
    historyResult: List<String>
) {
    Box(modifier = Modifier.fillMaxSize()) {
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
                    historyList = historyResult,
                    onQueryChange = onQueryChange
                )
            }
            else {
                SearchResultLayout(
                    searchResult = searchResult,
                    onClick = onClick
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
private fun HistoryLayout(historyList: List<String>, onQueryChange: (String) -> Unit) {
    historyList.forEach {
        ListItem(
            modifier = Modifier.clickable { onQueryChange(it) },
            headlineContent = { Text(it) },
            leadingContent = {
                Icon(
                    painter = painterResource(R.drawable.ic_history),
                    contentDescription = null
                )
            }
        )
    }
}

@Composable
private fun SearchResultLayout(
    searchResult: List<ResultItem>,
    onClick: (ResultItem) -> Unit
) {
    LazyColumn {
        items(searchResult) {
            ListItem(
                modifier = Modifier.clickable { onClick(it) },
                headlineContent = { Text(text = it.title) },
                leadingContent = {
                    Icon(
                        imageVector = Icons.Default.Search,
                        contentDescription = null
                    )
                }
            )
        }
    }
}