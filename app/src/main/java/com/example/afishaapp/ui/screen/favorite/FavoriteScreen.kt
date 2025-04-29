package com.example.afishaapp.ui.screen.favorite

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.ContentTransform
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.SegmentedButton
import androidx.compose.material3.SegmentedButtonDefaults
import androidx.compose.material3.SingleChoiceSegmentedButtonRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringArrayResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.afishaapp.R
import com.example.afishaapp.app.navigation.EventRoute
import com.example.afishaapp.app.navigation.MovieRoute
import com.example.afishaapp.app.navigation.PlaceRoute
import com.example.afishaapp.app.utils.SlideState
import com.example.afishaapp.data.module.event.Event
import com.example.afishaapp.data.module.movie.Movie
import com.example.afishaapp.data.module.place.Place
import com.example.afishaapp.ui.theme.DefaultPadding
import com.example.afishaapp.ui.widget.card.EventCard
import com.example.afishaapp.ui.widget.card.MovieCard
import com.example.afishaapp.ui.widget.card.PlaceCard
import com.example.afishaapp.ui.widget.endlessLazy.EndlessLazyColumn
import com.example.afishaapp.ui.widget.endlessLazy.EndlessLazyVerticalGrid
import com.example.afishaapp.ui.widget.text.TitleTopBar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FavoriteScreen(
    viewModel: FavoriteViewModel,
    navController: NavController,
    innerPadding: PaddingValues
) {
    val segmentedButtonsList = stringArrayResource(R.array.ticket_segmented_button)

    viewModel.getEvents()
    viewModel.getMovies()
    viewModel.getPlaces()

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.padding(
            bottom = innerPadding.calculateBottomPadding(),
            start = DefaultPadding,
            end = DefaultPadding
        )
    ) {
        CenterAlignedTopAppBar(
            title = {
                TitleTopBar(stringResource(R.string.favorite_text))
            },
            actions = {
                IconButton(onClick = {}) {
                    Icon(
                        painter = painterResource(R.drawable.ic_sort),
                        contentDescription = stringResource(R.string.filter)
                    )
                }
            }
        )

        SingleChoiceSegmentedButtonRow(
            modifier = Modifier.padding(vertical = 10.dp)
        ) {
            segmentedButtonsList.forEachIndexed { index, label ->
                SegmentedButton(
                    shape = SegmentedButtonDefaults.itemShape(
                        index = index,
                        count = segmentedButtonsList.size,
                        baseShape = RoundedCornerShape(8.dp)
                    ),
                    onClick = {
                        if (index > viewModel.indexScreen) {
                           viewModel.updateSlideState(SlideState.LEFT)
                        }
                        else {
                            viewModel.updateSlideState(SlideState.RIGHT)
                        }

                        viewModel.updateIndexScreen(index)
                    },
                    selected = index == viewModel.indexScreen,
                    label = { Text(text = label) },
                    icon = {}
                )
            }
        }

        AnimatedContent(
            targetState = viewModel.indexScreen,
            transitionSpec = {
                if (viewModel.slideState == SlideState.LEFT) {
                    ContentTransform(
                        targetContentEnter = slideIntoContainer(
                            towards = AnimatedContentTransitionScope.SlideDirection.Start,
                            animationSpec = tween(300)
                        ) + fadeIn(),
                        initialContentExit = slideOutOfContainer(
                            towards = AnimatedContentTransitionScope.SlideDirection.Start,
                            animationSpec = tween(300)
                        ) + fadeOut()
                    )
                }
                else {
                    ContentTransform(
                        targetContentEnter = slideIntoContainer(
                            towards = AnimatedContentTransitionScope.SlideDirection.End,
                            animationSpec = tween(300)
                        ) + fadeIn(),
                        initialContentExit = slideOutOfContainer(
                            towards = AnimatedContentTransitionScope.SlideDirection.End,
                            animationSpec = tween(300)
                        ) + fadeOut()
                    )
                }
            }
        ) { index ->
            when (index) {
                0 -> FavoriteEventsSubScreen(
                    events = viewModel.events,
                    onClick = { navController.navigate(EventRoute(it.id)) }
                )
                1 -> FavoriteMoviesSubScreen(
                    movies = viewModel.movies,
                    onClick = { navController.navigate(MovieRoute(it.id)) }
                )
                2 -> FavoritePlacesSubScreen(
                    places = viewModel.places,
                    onClick = { navController.navigate(PlaceRoute(it.id)) }
                )
            }
        }
    }
}

@Composable
fun FavoriteEventsSubScreen(events: List<Event>, onClick: (Event) -> Unit) {
    EndlessLazyColumn(
        items = events,
        verticalArrangement = Arrangement.spacedBy(40.dp),
        loadMore = {}
    ) { event ->
        EventCard(
            event = event,
            fill = true
        ) { onClick.invoke(event) }
    }
}

@Composable
fun FavoriteMoviesSubScreen(movies: List<Movie>, onClick: (Movie) -> Unit) {
    EndlessLazyVerticalGrid(
        columns = GridCells.Fixed(2),
        items = movies,
        verticalArrangement = Arrangement.spacedBy(30.dp),
        horizontalArrangement = Arrangement.spacedBy(10.dp),
        loadMore = {},
        itemContent = { movie ->
            MovieCard(movie = movie) { onClick.invoke(movie) }
        }
    )
}

@Composable
fun FavoritePlacesSubScreen(places: List<Place>, onClick: (Place) -> Unit) {
    EndlessLazyColumn(
        items = places,
        verticalArrangement = Arrangement.spacedBy(40.dp),
        loadMore = {}
    ) {
        PlaceCard(
            place = it,
            fill = true
        ) { onClick.invoke(it) }
    }
}