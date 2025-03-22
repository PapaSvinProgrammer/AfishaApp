package com.example.afishaapp.ui.screen.home

import androidx.compose.foundation.gestures.snapping.SnapPosition
import androidx.compose.foundation.gestures.snapping.rememberSnapFlingBehavior
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.afishaapp.R
import com.example.afishaapp.app.navigation.AccountRoute
import com.example.afishaapp.app.navigation.EventListRoute
import com.example.afishaapp.app.navigation.EventRoute
import com.example.afishaapp.app.navigation.MovieListRoute
import com.example.afishaapp.app.navigation.MovieRoute
import com.example.afishaapp.domain.module.EventCategory
import com.example.afishaapp.ui.screen.bottomSheet.CategoryEventBottomSheet
import com.example.afishaapp.ui.screen.bottomSheet.CityBottomSheet
import com.example.afishaapp.ui.theme.DefaultPadding
import com.example.afishaapp.ui.widget.card.EventCard
import com.example.afishaapp.ui.widget.card.MovieCard
import com.example.afishaapp.ui.widget.chip.SelectDropDown
import com.example.afishaapp.ui.widget.row.SelectRow
import com.example.afishaapp.ui.widget.shimmer.card.ShimmerEventCard
import com.example.afishaapp.ui.widget.shimmer.card.ShimmerMovieCard

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    navController: NavController,
    viewModel: HomeViewModel,
    innerPadding: PaddingValues
) {
    viewModel.getEvents()
    viewModel.getCity()
    viewModel.getDefaultCity()
    viewModel.getLocationSlug()
    viewModel.getMovies()
    viewModel.getCategories()

    val eventListState = rememberLazyListState()
    val eventFlingBehavior = rememberSnapFlingBehavior(eventListState, SnapPosition.Start)

    val movieListState = rememberLazyListState()
    val movieFlingBehavior = rememberSnapFlingBehavior(movieListState, SnapPosition.Start)

    val concertListState = rememberLazyListState()
    val concertFlingBehavior = rememberSnapFlingBehavior(concertListState, SnapPosition.Start)

    val exhibitionListState = rememberLazyListState()
    val exhibitionFlingBehavior = rememberSnapFlingBehavior(exhibitionListState, SnapPosition.Start)

    Scaffold(
        topBar = {
            TopAppBar(
                navigationIcon = {
                    SelectDropDown(viewModel.locationName) {
                        viewModel.updateCityState(true)
                    }
                },
                actions = {
                    IconButton(onClick = { navController.navigate(AccountRoute) }) {
                        Icon(
                            painter = painterResource(R.drawable.ic_account),
                            contentDescription = stringResource(R.string.ic_account_content_description)
                        )
                    }
                },
                title = { Text(text = "") }
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .padding(
                    top = padding.calculateTopPadding(),
                    bottom = innerPadding.calculateBottomPadding()
                )
                .verticalScroll(rememberScrollState())
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .horizontalScroll(rememberScrollState()),
                horizontalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                FilledTonalButton(
                    modifier = Modifier.padding(start = DefaultPadding),
                    shape = RoundedCornerShape(10.dp),
                    onClick = { }
                ) {
                    Text(text = stringResource(R.string.date))
                }

                FilledTonalButton(
                    onClick = { viewModel.updateCategoryState(true) },
                    shape = RoundedCornerShape(10.dp),
                    modifier = Modifier.padding(end = 10.dp),
                ) {
                    Text(
                        text = viewModel.currentCategory.name,
                        maxLines = 1
                    )

                    Icon(
                        imageVector = Icons.Default.KeyboardArrowDown,
                        contentDescription = null
                    )
                }
            }

            SelectRow(
                text = stringResource(R.string.future_event),
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold
            ) { title ->
                navController.navigate(
                    EventListRoute(
                        title = title,
                        categorySlug = viewModel.currentCategory.slug,
                        location = viewModel.currentLocationSlug
                    )
                )
            }

            LazyRow(
                contentPadding = PaddingValues(horizontal = DefaultPadding),
                horizontalArrangement = Arrangement.spacedBy(10.dp),
                state = eventListState,
                flingBehavior = eventFlingBehavior
            ) {
                if (viewModel.eventResponse == null) {
                    items(3) {
                        ShimmerEventCard()
                    }
                }

                viewModel.eventResponse?.let {
                    items(it.results) { event ->
                        EventCard(
                            event = event
                        ) {
                            navController.navigate(
                                EventRoute(event.id)
                            )
                        }
                    }
                }
            }

            SelectRow(
                text = stringResource(R.string.expected_in_cinema),
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                paddingValues = PaddingValues(vertical =  5.dp)
            ) { title ->
                navController.navigate(
                    MovieListRoute(
                        title = title,
                        location = viewModel.currentLocationSlug
                    )
                )
            }

            LazyRow(
                contentPadding = PaddingValues(horizontal = DefaultPadding),
                horizontalArrangement = Arrangement.spacedBy(10.dp),
                state = movieListState,
                flingBehavior = movieFlingBehavior
            ) {
                if (viewModel.movieResponse == null) {
                    items(3) {
                        ShimmerMovieCard()
                    }
                }

                viewModel.movieResponse?.let {
                    items(it.results) { movie ->
                        MovieCard(
                            movie = movie,
                            onClick = {
                                navController.navigate(
                                    MovieRoute(movie.id)
                                )
                            }
                        )
                    }
                }
            }

            SelectRow(
                text = stringResource(R.string.concerts),
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                paddingValues = PaddingValues(top = 15.dp)
            ) { title ->
                navController.navigate(
                    EventListRoute(
                        title = title,
                        location = viewModel.currentLocationSlug,
                        categorySlug = EventCategory.CONCERT.slug
                    )
                )
            }

            LazyRow(
                contentPadding = PaddingValues(horizontal = DefaultPadding),
                horizontalArrangement = Arrangement.spacedBy(10.dp),
                state = concertListState,
                flingBehavior = concertFlingBehavior
            ) {
                if (viewModel.eventConcert == null) {
                    items(3) {
                        ShimmerEventCard()
                    }
                }

                viewModel.eventConcert?.let {
                    items(it.results) { event ->
                        EventCard(
                            event = event,
                            onClick = {
                                navController.navigate(
                                    EventRoute(event.id)
                                )
                            }
                        )
                    }
                }
            }

            SelectRow(
                text = stringResource(R.string.exhibitions),
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                paddingValues = PaddingValues(top = 15.dp)
            ) { title ->
                navController.navigate(
                    EventListRoute(
                        title = title,
                        location = viewModel.currentLocationSlug,
                        categorySlug = EventCategory.EXHIBITION.slug
                    )
                )
            }

            LazyRow(
                modifier = Modifier.padding(bottom = 20.dp),
                contentPadding = PaddingValues(horizontal = DefaultPadding),
                horizontalArrangement = Arrangement.spacedBy(10.dp),
                state = exhibitionListState,
                flingBehavior = exhibitionFlingBehavior
            ) {
                if (viewModel.eventExhibition == null) {
                    items(3) {
                        ShimmerEventCard()
                    }
                }

                viewModel.eventExhibition?.let {
                    items(it.results) { event ->
                        EventCard(
                            event = event,
                            onClick = {
                                navController.navigate(
                                    EventRoute(event.id)
                                )
                            }
                        )
                    }
                }
            }
        }

        if (viewModel.cityBottomSheetState) {
            CityBottomSheet(
                data = viewModel.city,
                currentCity = viewModel.locationName,
                dismissRequest = { viewModel.updateCityState(false) },
                onClick = {
                    viewModel.updateDefaultCity(it)
                    viewModel.updateCityState(false)
                }
            )
        }

        if (viewModel.categoryBottomSheetState) {
            CategoryEventBottomSheet(
                data = viewModel.categoryEvent,
                currentCategory = viewModel.currentCategory.slug,
                onDismiss = { viewModel.updateCategoryState(false) },
                onClick = {
                    viewModel.updateCurrentCategory(it)
                    viewModel.updateCategoryState(false)
                }
            )
        }
    }
}