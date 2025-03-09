package com.example.afishaapp.ui.screen.movie

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.gestures.snapping.SnapPosition
import androidx.compose.foundation.gestures.snapping.rememberSnapFlingBehavior
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil3.compose.AsyncImage
import com.example.afishaapp.R
import com.example.afishaapp.app.support.ConvertCountTitle
import com.example.afishaapp.app.support.ConvertInfo
import com.example.afishaapp.app.support.ConvertDate
import com.example.afishaapp.ui.screen.bottomSheet.DateBottomSheet
import com.example.afishaapp.ui.screen.bottomSheet.MovieShowBottomSheet
import com.example.afishaapp.ui.theme.DefaultPadding
import com.example.afishaapp.ui.widget.card.ImageCard
import com.example.afishaapp.ui.widget.chip.ChipInfo
import com.example.afishaapp.ui.widget.chip.ChipRating
import com.example.afishaapp.ui.widget.collapsingTopBar.CollapsedTopBar
import com.example.afishaapp.ui.widget.collapsingTopBar.ExpandedTopBar
import com.example.afishaapp.ui.widget.row.SelectRow
import com.example.afishaapp.ui.widget.text.EventDescriptionText
import com.example.afishaapp.ui.widget.text.TitleTopBar

@RequiresApi(Build.VERSION_CODES.UPSIDE_DOWN_CAKE)
@Composable
fun MovieScreen(
    navController: NavController,
    viewModel: MovieViewModel,
    movieId: Int
) {
    viewModel.getLocationSlug()
    viewModel.getMovie(movieId)

    val imageListState = rememberLazyListState()
    val imageFlingBehavior = rememberSnapFlingBehavior(
        lazyListState = imageListState,
        snapPosition = SnapPosition.Start
    )

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
                        contentDescription = stringResource(R.string.ic_arrow_back_content_description),
                        tint = color
                    )
                }
            },
            actions = {
                IconButton(
                    onClick = {
                        viewModel.updateFavoriteState(
                            favoriteState = !viewModel.favoriteState
                        )
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
        )

        LazyColumn(
            state = collapsedListState
        ) {
            item {
                ExpandedTopBar(
                    expandedTopBarHeight = 550.dp
                ) {
                    viewModel.movie?.let {
                        AsyncImage(
                            model = it.poster.image,
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
                            model = it.poster.image,
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
                                .padding(0.dp, 0.dp, 0.dp, 10.dp),
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
                                    .padding(20.dp, 0.dp),
                                onClick = {
                                    viewModel.updateShowsBottomState(true)
                                    viewModel.getMovieShows(movieId, viewModel.currentLocationSlug)
                                }
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
                InfoRow(viewModel)

                GenresRow(viewModel)

                EventDescriptionText(
                    title = viewModel.movie?.description.toString(),
                    bodyText = viewModel.movie?.bodyText.toString()
                )

                SelectRow(
                    text = stringResource(R.string.images),
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp
                ) {

                }

                LazyRow(
                    contentPadding = PaddingValues(DefaultPadding, 0.dp),
                    horizontalArrangement = Arrangement.spacedBy(10.dp),
                    state = imageListState,
                    flingBehavior = imageFlingBehavior
                ) {
                    viewModel.movie?.let {
                        items(it.images) { image ->
                            ImageCard(image.thumbnails.highImage)
                        }
                    }
                }
            }
        }
    }

    if (viewModel.showsBottomState) {
        val text = if (viewModel.selectTime == 0)
            stringResource(R.string.today)
        else
            ConvertDate.addDaysToCurrentDate(viewModel.selectTime)

        MovieShowBottomSheet(
            data = viewModel.shows,
            currentTime = text,
            selectTime = {
                viewModel.updateTimeBottomState(true)
                viewModel.updateShowsBottomState(false)
            },
            onDismiss = { viewModel.updateShowsBottomState(false) }
        )
    }

    if (viewModel.timeBottomState) {
        DateBottomSheet(
            currentTime = viewModel.selectTime,
            onDismissRequest = { viewModel.updateTimeBottomState(false) },
            onClick = {
                viewModel.updateSelectTime(it)
                viewModel.updateTimeBottomState(false)
                viewModel.updateShowsBottomState(true)
            }
        )
    }
}

@Composable
private fun InfoRow(viewModel: MovieViewModel) {
    Row(
        modifier = Modifier
            .horizontalScroll(rememberScrollState())
            .padding(0.dp, 20.dp, 0.dp, 0.dp),
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
                        .padding(0.dp, 0.dp, 5.dp, 0.dp)
                        .size(36.dp)
                        .align(Alignment.CenterVertically),
                    shape = CircleShape
                )
            },
            modifier = Modifier.padding(DefaultPadding, 0.dp, 0.dp, 0.dp)
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
            modifier = Modifier.padding(0.dp, 0.dp, DefaultPadding, 0.dp)
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
                    label = { Text(text = genre.name) }
                )
            }
        }
    }
}