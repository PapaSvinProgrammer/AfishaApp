package com.example.afishaapp.ui.screen.ticket

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Search
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.afishaapp.R
import com.example.afishaapp.app.navigation.DetailTicketRoute
import com.example.afishaapp.ui.screen.bottomSheet.filter.TicketFilterBottomSheet
import com.example.afishaapp.ui.screen.main.bottomBarVisibilityState
import com.example.afishaapp.ui.theme.DefaultPadding
import com.example.afishaapp.ui.widget.listItem.TicketCard
import com.example.afishaapp.ui.widget.material.SearchViewScreen
import com.example.afishaapp.ui.widget.text.TitleTopBar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TicketScreen(
    navController: NavController,
    paddingValues: PaddingValues = PaddingValues(0.dp),
    viewModel: TicketViewModel
) {
    viewModel.getAllTickets()

    LaunchedEffect(Unit) {
        viewModel.updateTopBarVisibilityState(true)
    }

    LaunchedEffect(viewModel.expanded) {
        bottomBarVisibilityState.value = !viewModel.expanded
    }

    Scaffold(
        topBar = {
            AnimatedVisibility(
                visible = viewModel.topBarVisibilityState,
                enter = slideInVertically(),
                exit = slideOutVertically()
            ) {
                CenterAlignedTopAppBar(
                    title = { TitleTopBar(stringResource(R.string.my_ticket_text)) },
                    actions = {
                        IconButton(
                            onClick = { viewModel.updateFilterBottomSheetState(true) }
                        ) {
                            Icon(
                                painter = painterResource(R.drawable.ic_sort),
                                contentDescription = stringResource(R.string.search_title_text)
                            )
                        }

                        IconButton(
                            onClick = { viewModel.updateExpanded(true) }
                        ) {
                            Icon(
                                imageVector = Icons.Rounded.Search,
                                contentDescription = stringResource(R.string.search_title_text)
                            )
                        }
                    }
                )
            }
        }
    ) { innerPadding ->
        if (viewModel.tickets.isEmpty()) {
            EmptyTicketListContent(
                innerPadding = innerPadding,
                paddingValues = paddingValues
            )
        }
        else {
            MainContent(
                innerPadding = innerPadding,
                paddingValues = paddingValues,
                viewModel = viewModel,
                navController = navController
            )
        }
    }

    SearchViewScreen(
        query = viewModel.query,
        expanded = viewModel.expanded,
        onQueryChange = {
            viewModel.updateQuery(it)
            viewModel.search(it)
        },
        onExpandedChange = { viewModel.updateExpanded(it) },
        searchResult = viewModel.searchResult,
        onClick = {  }
    )

    if (viewModel.filterBottomSheetState) {
        TicketFilterBottomSheet(
            currentFilter = viewModel.currentFilterType,
            onDismiss = {
                viewModel.updateFilterBottomSheetState(false)
            },
            onClick = {
                viewModel.updateCurrentFilterType(it)
                viewModel.getAllTickets()
                viewModel.updateFilterBottomSheetState(false)
            }
        )
    }
}

@Composable
private fun MainContent(
    innerPadding: PaddingValues,
    paddingValues: PaddingValues,
    viewModel: TicketViewModel,
    navController: NavController
) {
    Column(
        modifier = Modifier.padding(
            bottom = paddingValues.calculateBottomPadding()
        )
    ) {
        Spacer(modifier = Modifier.height(innerPadding.calculateTopPadding()))

        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(5.dp),
            modifier = Modifier.padding(horizontal = 10.dp)
        ) {
            items(viewModel.tickets) { event ->
                TicketCard(
                    ticket = event,
                    onClick = {
                        viewModel.updateTopBarVisibilityState(false)
                        navController.navigate(DetailTicketRoute(event.eventId))
                    }
                )
            }
        }
    }
}

@Composable
private fun EmptyTicketListContent(
    innerPadding: PaddingValues,
    paddingValues: PaddingValues
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(
                top = innerPadding.calculateTopPadding(),
                bottom = paddingValues.calculateBottomPadding(),
                start = DefaultPadding,
                end = DefaultPadding
            ),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Icon(
                painter = painterResource(R.drawable.ic_ticket_fill),
                contentDescription = null,
                modifier = Modifier.size(200.dp)
            )

            Text(
                text = stringResource(R.string.ticket_empty_title),
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp
            )

            Spacer(modifier = Modifier.height(DefaultPadding))

            Text(
                text = stringResource(R.string.ticket_empty_description),
                fontSize = 15.sp,
                textAlign = TextAlign.Center
            )
        }
    }
}