package com.example.afishaapp.ui.screen.movie

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.SuggestionChip
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil3.compose.AsyncImage
import coil3.request.ImageRequest
import coil3.request.crossfade
import com.example.afishaapp.R
import com.example.afishaapp.app.navigation.AboutMovieRoute
import com.example.afishaapp.app.utils.convertData.ConvertCountTitle
import com.example.afishaapp.app.utils.convertData.ConvertInfo
import com.example.afishaapp.app.utils.convertData.ConvertDate
import com.example.afishaapp.di.viewModel.ViewModelFactory
import com.example.afishaapp.ui.screen.movieShowBottomSheet.MovieShowBottomSheet
import com.example.afishaapp.ui.theme.DefaultPadding
import com.example.afishaapp.ui.widget.chip.ChipInfo
import com.example.afishaapp.ui.widget.chip.ChipRating
import com.example.afishaapp.ui.widget.collapsingTopBar.CollapsedTopBar
import com.example.afishaapp.ui.widget.collapsingTopBar.ExpandedTopBar
import com.example.afishaapp.ui.widget.row.ImagesRow
import com.example.afishaapp.ui.widget.shimmer.screen.ShimmerEvent
import com.example.afishaapp.ui.widget.shimmer.screen.ShimmerExpandedToolbar
import com.example.afishaapp.ui.widget.text.DefaultDetailDescription
import com.example.afishaapp.ui.widget.text.EventDescriptionText
import com.example.afishaapp.ui.widget.text.TitleTopBar

@RequiresApi(Build.VERSION_CODES.UPSIDE_DOWN_CAKE)
@Composable
fun MovieScreen(
    navController: NavController,
    viewModel: MovieViewModel,
    viewModelFactory: ViewModelFactory,
    movieId: Int
) {
    viewModel.getMovie(movieId)
    viewModel.parseInfo()

    val collapsedListState = rememberLazyListState()
    val isCollapsed: Boolean by remember {
        derivedStateOf {
            collapsedListState.firstVisibleItemIndex > 0
        }
    }

    val color = if (isCollapsed)
            Color.Black
        else
            Color.White

    if (viewModel.movie != null) {
        viewModel.findLikeMovie()
    }

    Box(
        modifier = Modifier.navigationBarsPadding()
    ) {
        CollapsedTopBar(
            isCollapsed = isCollapsed,
            title = {
                TitleTopBar(viewModel.movie?.title.toString())
            },
            navigationIcon = {
                IconButton(
                    onClick = { navController.popBackStack() }
                ) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Default.ArrowBack,
                        contentDescription = stringResource(R.string.arrow_back_description),
                        tint = color
                    )
                }
            },
            actions = {
                if (viewModel.movie != null) {
                    IconButton(
                        onClick = {
                            handleFavorite(viewModel)
                        }
                    ) {
                        Icon(
                            imageVector = if (viewModel.favoriteState)
                                Icons.Default.Favorite
                            else
                                Icons.Default.FavoriteBorder,
                            contentDescription = stringResource(R.string.favorite_text),
                            tint = Color.Red
                        )
                    }

                    IconButton(
                        onClick = {  }
                    ) {
                        Icon(
                            painter = painterResource(R.drawable.ic_share),
                            contentDescription = stringResource(R.string.share),
                            tint = color
                        )
                    }
                }
            }
        )

        LazyColumn(
            state = collapsedListState
        ) {
            item {
                if (viewModel.movie == null) {
                    ShimmerExpandedToolbar()
                }

                viewModel.movie?.let {
                    ExpandedTopBar(
                        expandedTopBarHeight = 520.dp
                    ) {
                        AsyncImage(
                            model = ImageRequest.Builder(LocalContext.current)
                                .data(it.poster?.thumbnails?.lowImage)
                                .crossfade(true)
                                .build(),
                            contentDescription = null,
                            contentScale = ContentScale.Crop,
                            modifier = Modifier
                                .fillMaxSize()
                                .blur(
                                    radiusX = 500.dp,
                                    radiusY = 500.dp
                                )
                        )

                        AsyncImage(
                            model = it.poster?.thumbnails?.highImage,
                            contentDescription = null,
                            contentScale = ContentScale.Crop,
                            modifier = Modifier
                                .size(180.dp, 250.dp)
                                .align(Alignment.Center)
                                .clip(RoundedCornerShape(10.dp))
                        )

                        Column(
                            modifier = Modifier
                                .align(Alignment.BottomCenter)
                                .padding(bottom = 10.dp),
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.spacedBy(10.dp)
                        ) {
                            Text(
                                text = viewModel.movie?.title.toString(),
                                fontSize = 24.sp,
                                fontWeight = FontWeight.Bold,
                                color = Color.White,
                                maxLines = 1,
                                overflow = TextOverflow.Ellipsis
                            )

                            Button(
                                modifier = Modifier
                                    .fillParentMaxWidth()
                                    .padding(horizontal = 10.dp),
                                onClick = { viewModel.updateShowsBottomState(true) }
                            ) {
                                Text(
                                    text = stringResource(R.string.schedule),
                                    fontSize = 16.sp
                                )
                            }
                        }
                    }
                }
            }

            item {
                if (viewModel.movie == null) {
                    ShimmerEvent()
                }

                viewModel.movie?.let {
                    InfoRow(viewModel)
                    GenresRow(viewModel)
                    EventDescriptionText(
                        title = viewModel.parseMovieDescription,
                        bodyText = viewModel.parseMovieBodyText
                    ) {
                        navController.navigate(
                            AboutMovieRoute(movieId)
                        )
                    }

                    ImagesRow(it.images)
                    FilmCrew(viewModel)

                    Column(
                        modifier = Modifier.padding(horizontal = DefaultPadding)
                    ) {
                        DefaultDetailDescription(
                            title = stringResource(R.string.cast),
                            subtitle = it.stars
                        )
                    }
                }
            }
        }
    }

    if (viewModel.showsBottomState) {
        MovieShowBottomSheet(
            onDismiss = { viewModel.updateShowsBottomState(false) },
            onClick = {  },
            viewModelFactory = viewModelFactory,
            movieId = movieId
        )
    }
}

private fun handleFavorite(viewModel: MovieViewModel) {
    viewModel.updateFavoriteState(
        favoriteState = !viewModel.favoriteState
    )

    when (viewModel.favoriteState) {
        true -> viewModel.addLikeMovie()
        false -> viewModel.deleteLikeMovie()
    }
}

@Composable
private fun InfoRow(viewModel: MovieViewModel) {
    Row(
        modifier = Modifier
            .horizontalScroll(rememberScrollState())
            .padding(top = 20.dp),
        horizontalArrangement = Arrangement.spacedBy(5.dp),
    ) {
        ChipInfo(
            title = ConvertCountTitle.convertCommentsCount(
                count = viewModel.movie?.commentsCount ?: 0
            ),
            subtitle = ConvertCountTitle.convertLikeCount(
                count = viewModel.movie?.favoritesCount ?: 0
            ),
            icon = {
                ChipRating(
                    rating = viewModel.movie?.imdbRating.toString(),
                    modifier = Modifier
                        .padding(end = 5.dp)
                        .size(36.dp)
                        .align(Alignment.CenterVertically),
                    shape = CircleShape
                )
            },
            modifier = Modifier.padding(start = DefaultPadding)
        )

        ChipInfo(
            title = stringResource(R.string.age),
            subtitle = ConvertInfo.convertAgeRestriction(viewModel.movie?.ageRestriction.toString())
        )

        ChipInfo(
            title = stringResource(R.string.year),
            subtitle = viewModel.movie?.year.toString()
        )

        ChipInfo(
            title = stringResource(R.string.duration),
            subtitle = ConvertDate.convertDurationMinutes(
                time = viewModel.movie?.runningTime ?: 0
            )
        )

        ChipInfo(
            title = stringResource(R.string.country),
            subtitle = viewModel.movie?.country.toString(),
            modifier = Modifier.padding(end = DefaultPadding)
        )
    }
}

@Composable
private fun GenresRow(viewModel: MovieViewModel) {
    LazyRow(
        contentPadding = PaddingValues(DefaultPadding, 8.dp, DefaultPadding, 15.dp),
        horizontalArrangement = Arrangement.spacedBy(5.dp)
    ) {
        viewModel.movie?.let {
            items(it.genres) { genre ->
                SuggestionChip(
                    onClick = { },
                    label = {
                        Text(
                            text = ConvertInfo.convertTitle(genre.name)
                        )
                    }
                )
            }
        }
    }
}

@Composable
private fun FilmCrew(viewModel: MovieViewModel) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(
                top = 20.dp,
                start = DefaultPadding,
                end = DefaultPadding
            ),
        horizontalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        viewModel.movie?.let {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
                    .weight(1f)
            ) {
                Text(
                    text = stringResource(R.string.director),
                    fontWeight = FontWeight.Bold,
                    fontSize = 15.sp
                )

                Text(
                    text = it.director,
                    fontSize = 14.sp
                )

                Text(
                    text = stringResource(R.string.writer),
                    fontWeight = FontWeight.Bold,
                    fontSize = 15.sp,
                    modifier = Modifier.padding(top = 10.dp)
                )

                Text(
                    text = it.writer,
                    fontSize = 14.sp
                )
            }

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
                    .weight(1f)
            ) {
                Text(
                    text = stringResource(R.string.rating_MPAA),
                    fontWeight = FontWeight.Bold,
                    fontSize = 15.sp
                )

                Text(
                    text = it.mpaaRating.ifEmpty { stringResource(R.string.no_info) },
                    fontSize = 14.sp
                )

                Text(
                    text = stringResource(R.string.rating_IMDB),
                    fontWeight = FontWeight.Bold,
                    fontSize = 15.sp,
                    modifier = Modifier.padding(top = 10.dp)
                )

                Text(
                    text = it.imdbRating.toString(),
                    fontSize = 14.sp
                )
            }
        }
    }
}