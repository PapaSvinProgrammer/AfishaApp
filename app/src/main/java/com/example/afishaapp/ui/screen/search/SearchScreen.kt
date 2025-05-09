package com.example.afishaapp.ui.screen.search

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.isTraversalGroup
import androidx.compose.ui.semantics.semantics
import androidx.navigation.NavController
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.afishaapp.ui.screen.dialog.SearchFilterDialog
import com.example.afishaapp.ui.screen.main.bottomBarVisibilityState
import com.example.afishaapp.ui.widget.material.SearchLayout

@Composable
fun SearchScreen(
    navController: NavController,
    viewModel: SearchViewModel,
    padding: PaddingValues
) {
    viewModel.getCurrentPlace()
    val searchHistoryLazyPagingItems = viewModel.resultHistory.collectAsLazyPagingItems()

    LaunchedEffect(viewModel.expanded) {
        bottomBarVisibilityState.value = !viewModel.expanded

        if (!viewModel.expanded) {
            viewModel.clearResultItems()
            viewModel.updateQuery("")
        }
    }

    Column(
        modifier = Modifier.padding(bottom = padding.calculateBottomPadding())
    ) {
        SearchLayout(
            query = viewModel.query,
            expanded = viewModel.expanded,
            searchResult = viewModel.resultItems,
            historyResult = searchHistoryLazyPagingItems,
            onQueryChange = {
                viewModel.updateQuery(it)
                viewModel.search(it)
            },
            onExpandedChange = { viewModel.updateExpanded(it) },
            onClick = {
                viewModel.addStringInHistory(it.title)
            },
            onLoadMore = { viewModel.loadMoreItems() },
            onClickSettings = { viewModel.updateSearchDialogState(true) }
        )
    }

    if (viewModel.searchDialogState) {
        SearchFilterDialog(
            onConfirmClick = {
                Log.d("RRRR", it.toString())
                viewModel.updateSearchDialogState(false)
            },
            onDismissClick = { viewModel.updateSearchDialogState(false) }
        )
    }
}