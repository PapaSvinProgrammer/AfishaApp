package com.example.afishaapp.ui.widget.material

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.SearchBar
import androidx.compose.material3.SearchBarDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.example.afishaapp.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchLayout(
    query: String,
    expanded: Boolean,
    onQueryChange: (String) -> Unit,
    onExpandedChange: (Boolean) -> Unit,
    searchResults: List<String>,
    historyResults: List<String>
) {
    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        SearchBar(
            modifier = Modifier.align(Alignment.TopCenter),
            inputField = {
                SearchBarDefaults.InputField(
                    query = query,
                    onQueryChange = { onQueryChange(it) },
                    onSearch = { onExpandedChange(false) },
                    expanded = expanded,
                    onExpandedChange = { onExpandedChange(it) },
                    leadingIcon = {
                        LeadingIcon(
                            expanded = expanded,
                            onExpandedChange = { onExpandedChange(it) }
                        )
                    },
                    trailingIcon = {
                        TrailingIcon(
                            query = query,
                            expanded = expanded,
                            onQueryChange = { onQueryChange(it) },
                            onExpandedChange = { onExpandedChange(it) }
                        )
                    },
                    placeholder = { Text(stringResource(R.string.search_title)) }
                )
            },
            expanded = expanded,
            onExpandedChange = { onExpandedChange(it) }
        ) {

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
        return
    }

    IconButton(
        onClick = { if (query.isEmpty()) onExpandedChange(false) else onQueryChange("") }
    ) {
        Icon(
            imageVector = Icons.Default.Close,
            contentDescription = null
        )
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
