package com.example.afishaapp.ui.screen.search

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.input.TextFieldState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.example.afishaapp.ui.widget.material.SearchLayout

@Composable
fun SearchScreen(
    navController: NavController,
    viewModel: SearchViewModel,
    padding: PaddingValues
) {
    val textFieldState = TextFieldState()

    Column(
        modifier = Modifier.padding(padding)
    ) {
        SearchLayout(
            query = viewModel.query,
            expanded = viewModel.expanded,
            searchResults = listOf(),
            historyResults = listOf(),
            onQueryChange = { viewModel.query = it },
            onExpandedChange = { viewModel.expanded = it}
        )
    }
}