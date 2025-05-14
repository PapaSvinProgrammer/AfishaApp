package com.example.afishaapp.ui.screen.search

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.afishaapp.app.navigation.EventRoute
import com.example.afishaapp.app.navigation.PlaceRoute
import com.example.afishaapp.data.module.search.ResultItem
import com.example.afishaapp.ui.screen.dialog.SearchFilterDialog
import com.example.afishaapp.ui.screen.home.EventList
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

    LaunchedEffect(viewModel.currentLocation) {
        viewModel.getDefaultEvents(viewModel.currentLocation)
    }

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
                navigateToDetailScreen(it, navController)
                viewModel.addStringInHistory(it.title)
            },
            onLoadMore = { viewModel.loadMoreItems() },
            onClickSettings = { viewModel.updateSearchDialogState(true) }
        )

        Spacer(modifier = Modifier.height(20.dp))

        EventList(
            events = viewModel.events,
            onClick = { navController.navigate(EventRoute(it)) }
        )
    }

    if (viewModel.searchDialogState) {
        SearchFilterDialog(
            onConfirmClick = {
                //TODO
                viewModel.updateSearchDialogState(false)
            },
            onDismissClick = { viewModel.updateSearchDialogState(false) }
        )
    }
}

private fun navigateToDetailScreen(item: ResultItem, navController: NavController) {
    when (item.ctype) {
        "event" -> navController.navigate(EventRoute(item.id))
        "place" -> navController.navigate(PlaceRoute(item.id))
    }
}