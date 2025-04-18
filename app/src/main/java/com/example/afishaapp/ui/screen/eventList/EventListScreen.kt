package com.example.afishaapp.ui.screen.eventList


import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.afishaapp.R
import com.example.afishaapp.app.navigation.EventRoute
import com.example.afishaapp.ui.screen.bottomSheet.CategoryEventBottomSheet
import com.example.afishaapp.ui.theme.DefaultPadding
import com.example.afishaapp.ui.widget.endlessLazy.EndlessLazyColumn
import com.example.afishaapp.ui.widget.card.EventCard
import com.example.afishaapp.ui.widget.shimmer.card.ShimmerFillEventCard
import com.example.afishaapp.ui.widget.text.TitleTopBar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EventListScreen(
    navController: NavController,
    viewModel: EventListViewModel,
    topTitle: String,
    categorySlug: String,
    location: String
) {
    if (viewModel.isStartCategories) {
        viewModel.updateCurrentCategory(categorySlug)
        viewModel.startCategorySuccess()
    }

    viewModel.getCategories()
    viewModel.getEvents(location)

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    TitleTopBar(topTitle)
                },
                navigationIcon = {
                    IconButton(
                        onClick = { navController.popBackStack() }
                    ) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Default.ArrowBack,
                            contentDescription = stringResource(R.string.ic_arrow_back_content_description)
                        )
                    }
                },
                actions = {
                    IconButton(
                        onClick = {  }
                    ) {
                        Icon(
                            imageVector = Icons.Default.DateRange,
                            contentDescription = stringResource(R.string.ic_arrow_back_content_description)
                        )
                    }

                    IconButton(
                        onClick = { viewModel.updateCategoryStateBottomSheet(true) }
                    ) {
                        Icon(
                            painter = painterResource(R.drawable.ic_sort),
                            contentDescription = stringResource(R.string.ic_arrow_back_content_description)
                        )
                    }
                }
            )
        }
    ) { innerPadding ->
        if (viewModel.events.isEmpty()) {
            ShimmerEventList(innerPadding)
        }
        else {
            EndlessLazyColumn(
                contentPadding = PaddingValues(
                    horizontal = DefaultPadding,
                    vertical = 10.dp
                ),
                verticalArrangement = Arrangement.spacedBy(40.dp),
                modifier = Modifier.padding(innerPadding),
                items = viewModel.events,
                loadMore = {
                    viewModel.loadMore(location)
                },
                itemContent = { event ->
                    EventCard(
                        event = event,
                        fill = true,
                        onClick = {
                            navController.navigate(
                                EventRoute(event.id)
                            )
                        }
                    )
                }
            )
        }

        if (viewModel.categoryStateBottomSheet) {
            CategoryEventBottomSheet(
                data = viewModel.categories,
                currentCategory = viewModel.currentCategory,
                onDismiss = { viewModel.updateCategoryStateBottomSheet(false) },
                onClick = {
                    viewModel.updateCurrentCategory(it.slug)
                    viewModel.updateCategoryStateBottomSheet(false)
                }
            )
        }
    }
}

@Composable
private fun ShimmerEventList(padding: PaddingValues) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                start = DefaultPadding,
                end = DefaultPadding,
                top = padding.calculateTopPadding(),
                bottom = padding.calculateBottomPadding()
            ),
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        Spacer(modifier = Modifier.height(10.dp))

        repeat(4) {
            ShimmerFillEventCard()
        }
    }
}