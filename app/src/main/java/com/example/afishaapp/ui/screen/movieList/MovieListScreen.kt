package com.example.afishaapp.ui.screen.movieList

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
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
import com.example.afishaapp.app.navigation.MovieRoute
import com.example.afishaapp.ui.screen.bottomSheet.filter.BaseFilterBottomSheet
import com.example.afishaapp.ui.widget.endlessLazy.EndlessLazyVerticalGrid
import com.example.afishaapp.ui.widget.listItem.MovieCard
import com.example.afishaapp.ui.widget.shimmer.card.ShimmerMovieCard
import com.example.afishaapp.ui.widget.text.TitleTopBar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MovieListScreen(
    viewModel: MovieListViewModel,
    navController: NavController,
    topTitle: String,
    location: String
) {
    viewModel.updateLocationSlug(location)
    viewModel.getMovies()

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { TitleTopBar(topTitle) },
                navigationIcon = {
                    IconButton(
                        onClick = { navController.popBackStack() }
                    ) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Default.ArrowBack,
                            contentDescription = stringResource(R.string.arrow_back_description)
                        )
                    }
                },
                actions = {
                    IconButton(
                        onClick = {  }
                    ) {
                        Icon(
                            imageVector = Icons.Default.DateRange,
                            contentDescription = stringResource(R.string.arrow_back_description)
                        )
                    }

                    IconButton(
                        onClick = { viewModel.updateFilterStateBottomSheet(true) }
                    ) {
                        Icon(
                            painter = painterResource(R.drawable.ic_sort),
                            contentDescription = stringResource(R.string.arrow_back_description)
                        )
                    }
                }
            )
        }
    ) { innerPadding ->
        if (viewModel.movies.isEmpty()) {
            ShimmerMovieList(innerPadding)
        }
        else {
            EndlessLazyVerticalGrid(
                columns = GridCells.Fixed(2),
                modifier = Modifier.padding(innerPadding),
                contentPadding = PaddingValues(horizontal = 10.dp, vertical = 10.dp),
                verticalArrangement = Arrangement.spacedBy(30.dp),
                horizontalArrangement = Arrangement.spacedBy(10.dp),
                items = viewModel.movies,
                loadMore = {
                    viewModel.loadMoreMovies()
                },
                itemContent = { movie ->
                    MovieCard(
                        movie = movie,
                        onClick = {
                            navController.navigate(
                                MovieRoute(movie.id)
                            )
                        }
                    )
                }
            )
        }

        if (viewModel.filterStateBottomSheet) {
            BaseFilterBottomSheet(
                currentFilter = viewModel.currentFilter,
                onDismissRequest = { viewModel.updateFilterStateBottomSheet(false) },
                onClick = {
                    viewModel.updateCurrentFilter(it)
                    viewModel.updateFilterStateBottomSheet(false)
                }
            )
        }
    }
}

@Composable
private fun ShimmerMovieList(padding: PaddingValues) {
    LazyVerticalGrid(
        modifier = Modifier.padding(padding),
        columns = GridCells.Fixed(2),
        contentPadding = PaddingValues(horizontal = 10.dp, vertical = 10.dp),
        verticalArrangement = Arrangement.spacedBy(30.dp),
        horizontalArrangement = Arrangement.spacedBy(10.dp),
    ) {
        items(4) {
            ShimmerMovieCard()
        }
    }
}