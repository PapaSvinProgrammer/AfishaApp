package com.example.afishaapp.ui.screen.ticket

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Search
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.afishaapp.R
import com.example.afishaapp.app.navigation.DetailTicketRoute
import com.example.afishaapp.ui.screen.bottomSheet.filter.TicketFilterBottomSheet
import com.example.afishaapp.ui.widget.listItem.TicketCard
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

                        IconButton(onClick = { }) {
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