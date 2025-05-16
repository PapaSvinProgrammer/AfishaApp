package com.example.afishaapp.ui.screen.search

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.afishaapp.R
import com.example.afishaapp.app.navigation.EventListRoute
import com.example.afishaapp.app.navigation.EventRoute
import com.example.afishaapp.app.navigation.PlaceRoute
import com.example.afishaapp.app.utils.CategoryNode
import com.example.afishaapp.app.utils.categoryList
import com.example.afishaapp.data.module.search.ResultItem
import com.example.afishaapp.ui.screen.dialog.SearchFilterDialog
import com.example.afishaapp.ui.screen.home.EventList
import com.example.afishaapp.ui.screen.main.bottomBarVisibilityState
import com.example.afishaapp.ui.theme.DefaultPadding
import com.example.afishaapp.ui.widget.chip.AboutChipInfo
import com.example.afishaapp.ui.widget.material.SearchLayout
import com.example.afishaapp.ui.widget.row.SelectRow

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

        Spacer(modifier = Modifier.height(DefaultPadding))

        Column(
            modifier = Modifier.verticalScroll(rememberScrollState())
        ) {
            Spacer(modifier = Modifier.height(20.dp))

            SelectRow(
                text = stringResource(R.string.future_event),
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                onClick = { title ->
                    navController.navigate(
                        EventListRoute(
                            title = title,
                            categorySlug = "",
                            location = viewModel.currentLocation
                        )
                    )
                }
            )

            EventList(
                events = viewModel.events,
                onClick = { navController.navigate(EventRoute(it)) }
            )

            Text(
                modifier = Modifier.padding(DefaultPadding),
                text = stringResource(R.string.categories),
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp
            )

            GenerateCategoryLayout(
                onClick = {
                    navController.navigate(
                        EventListRoute(
                            title = it.title,
                            categorySlug = it.slug,
                            location = viewModel.currentLocation
                        )
                    )
                }
            )

            Spacer(modifier = Modifier.height(DefaultPadding))
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
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
private fun GenerateCategoryLayout(onClick: (CategoryNode) -> Unit) {
    FlowRow(
        maxItemsInEachRow = 3,
        horizontalArrangement = Arrangement.spacedBy(10.dp),
        verticalArrangement = Arrangement.spacedBy(10.dp),
        modifier = Modifier.padding(horizontal = 10.dp)
    ) {
        categoryList.forEach {
            AboutChipInfo(
                modifier = Modifier
                    .weight(1f)
                    .clickable { onClick(it) },
                titleSize = 14.sp,
                title = it.title,
                icon = {
                    Icon(
                        painter = painterResource(it.icon),
                        contentDescription = null,
                        modifier = Modifier.size(40.dp)
                    )
                    Spacer(modifier = Modifier.height(5.dp))
                }
            )
        }
    }
}

private fun navigateToDetailScreen(item: ResultItem, navController: NavController) {
    when (item.ctype) {
        "event" -> navController.navigate(EventRoute(item.id))
        "place" -> navController.navigate(PlaceRoute(item.id))
    }
}