package com.example.afishaapp.ui.screen.search

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.example.afishaapp.ui.screen.main.bottomBarVisibilityState
import com.example.afishaapp.ui.widget.material.SearchLayout

@Composable
fun SearchScreen(
    navController: NavController,
    viewModel: SearchViewModel,
    padding: PaddingValues
) {
    viewModel.getCurrentPlace()
    viewModel.getHistory()

    LaunchedEffect(viewModel.expanded) {
        bottomBarVisibilityState.value = !viewModel.expanded

        if (!viewModel.expanded) {
            viewModel.clearResultItems()
        }
    }

    Column(
        modifier = Modifier.padding(padding)
    ) {
        SearchLayout(
            query = viewModel.query,
            expanded = viewModel.expanded,
            searchResult = viewModel.resultItems,
            historyResult = viewModel.resultHistory,
            onQueryChange = {
                viewModel.query = it
                viewModel.search(it)
            },
            onExpandedChange = { viewModel.expanded = it},
            onClick = {
                viewModel.addStringInHistory(it.title)
            }
        )
    }
}