package com.example.afishaapp.ui.screen.dialog

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.ListItem
import androidx.compose.material3.ListItemDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.itemKey
import com.example.afishaapp.R
import com.example.afishaapp.app.utils.convertData.ConvertInfo
import com.example.afishaapp.data.room.searchHistory.SearchHistoryEntity

@Composable
fun HistoryResultDialog(
    historyResult: LazyPagingItems<SearchHistoryEntity>,
    onDismiss: () -> Unit
) {
    AlertDialog(
        title = {
            Text(text = stringResource(R.string.search_history))
        },
        text = {
            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(300.dp)
            ) {
                items(
                    count = historyResult.itemCount,
                    key = historyResult.itemKey { it.id },
                    itemContent = { index ->
                        val entity = historyResult[index]

                        entity?.let {
                            ListItem(
                                headlineContent = {
                                    Text(text = ConvertInfo.convertTitle(it.query))
                                },
                                colors = ListItemDefaults.colors(
                                    containerColor = MaterialTheme.colorScheme.surfaceContainerHigh
                                )
                            )

                            HorizontalDivider(
                                modifier = Modifier.padding(start = 15.dp),
                                color = Color.Gray
                            )
                        }
                    }
                )
            }
        },
        confirmButton = {
            TextButton(onClick = onDismiss) {
                Text(text = stringResource(R.string.ready))
            }
        },
        dismissButton = {},
        onDismissRequest = onDismiss
    )
}