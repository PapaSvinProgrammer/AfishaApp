package com.example.afishaapp.ui.screen.event

import  androidx.compose.foundation.clickable
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SuggestionChip
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil3.compose.AsyncImage
import com.example.afishaapp.R
import com.example.afishaapp.app.navigation.AboutEventRoute
import com.example.afishaapp.app.navigation.CommentListRoute
import com.example.afishaapp.app.support.ConvertCountTitle
import com.example.afishaapp.app.support.ConvertInfo
import com.example.afishaapp.app.support.ConvertDate
import com.example.afishaapp.domain.module.EventCategory
import com.example.afishaapp.ui.theme.DefaultPadding
import com.example.afishaapp.ui.widget.chip.ChipInfo
import com.example.afishaapp.ui.widget.row.SelectRow
import com.example.afishaapp.ui.widget.card.CommentCard
import com.example.afishaapp.ui.widget.card.ImageCard
import com.example.afishaapp.ui.widget.collapsingTopBar.CollapsedTopBar
import com.example.afishaapp.ui.widget.collapsingTopBar.ExpandedTopBar
import com.example.afishaapp.ui.widget.shimmer.screen.ShimmerEvent
import com.example.afishaapp.ui.widget.shimmer.screen.ShimmerExpandedToolbar
import com.example.afishaapp.ui.widget.text.EventDescriptionText
import com.example.afishaapp.ui.widget.text.TitleTopBar

@Composable
fun EventScreen(
    navController: NavController,
    viewModel: EventViewModel,
    eventId: Int
) {
    val derivedEventId by remember {
        derivedStateOf { eventId }
    }

    viewModel.getEventInfo(derivedEventId)
    viewModel.getComments(derivedEventId)
    viewModel.parseEventInfo()

    val listState = rememberLazyListState()
    val isCollapsed: Boolean by remember {
        derivedStateOf {
            listState.firstVisibleItemIndex > 0
        }
    }

    val imageListState = rememberLazyListState()
    val imageSnapBehavior = rememberSnapFlingBehavior(imageListState, SnapPosition.Start)

    val commentLazyState = rememberLazyListState()
    val commentSnapBehavior = rememberSnapFlingBehavior(commentLazyState,SnapPosition.Start)

    Box {
        val color = if (isCollapsed)
            Color.Black
        else
            Color.White

        CollapsedTopBar(
            isCollapsed = isCollapsed,
            title = {
                TitleTopBar(viewModel.event?.shortTitle.toString())
            },
            navigationIcon = {
                IconButton(
                    onClick = { navController.popBackStack() }
                ) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Default.ArrowBack,
                        contentDescription = null,
                        tint = color
                    )
                }
            },
            actions = {
                IconButton(
                    onClick = {
                        viewModel.updateFavoriteState(
                            state = !viewModel.favoriteState
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
            state = listState,
            modifier = Modifier.navigationBarsPadding()
        ) {
            item {
                if (viewModel.event == null) {
                    ShimmerExpandedToolbar()
                }

                viewModel.event?.let {
                    ExpandedTopBar {
                        AsyncImage(
                            model = it.images[0].image,
                            contentDescription = null,
                            contentScale = ContentScale.Crop,
                            modifier = Modifier.fillMaxSize()
                        )

                        Column(
                            modifier = Modifier.padding(20.dp)
                        ) {
                            Text(
                                text = viewModel.event?.shortTitle.toString(),
                                fontWeight = FontWeight.Medium,
                                fontSize = 18.sp,
                                color = Color.White
                            )

                            it.place?.let { place ->
                                Text(
                                    text = place.title,
                                    fontSize = 15.sp,
                                    color = Color.White
                                )
                            }

                            Text(
                                text = ConvertDate.convertStartDate(it.dates),
                                fontSize = 15.sp,
                                color = Color.White
                            )
                        }
                    }
                }
            }

            item {
                if (viewModel.event == null) {
                    ShimmerEvent()
                }

                viewModel.event?.let { event ->
                    InfoRow(viewModel, navController)

                    TagsRow(viewModel)

                    EventDescriptionText(
                        title = viewModel.parseEventDescription,
                        bodyText = viewModel.parseEventBodyText
                    ) {
                        navController.navigate(
                            AboutEventRoute(derivedEventId)
                        )
                    }

                    if (event.commentsCount > 0) {
                        SelectRow(
                            text = ConvertCountTitle.convertCommentsCount(event.commentsCount),
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Bold
                        ) {
                            navController.navigate(
                                CommentListRoute(
                                    name = event.shortTitle,
                                    type = EventCategory.EVENT,
                                    id = event.id
                                )
                            )
                        }

                        LazyRow(
                            contentPadding = PaddingValues(horizontal = DefaultPadding),
                            horizontalArrangement = Arrangement.spacedBy(10.dp),
                            state = commentLazyState,
                            flingBehavior = commentSnapBehavior
                        ) {
                            items(viewModel.comments) { comment ->
                                CommentCard(comment)
                            }
                        }
                    }

                    SelectRow(
                        text = stringResource(R.string.address),
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        icon = null
                    ) {

                    }

                    SelectRow(
                        text = stringResource(R.string.images),
                        icon = null,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold
                    ) {

                    }

                    LazyRow(
                        contentPadding = PaddingValues(horizontal = DefaultPadding),
                        horizontalArrangement = Arrangement.spacedBy(10.dp),
                        state = imageListState,
                        flingBehavior = imageSnapBehavior
                    ) {
                        items(event.images) { imageItem ->
                            ImageCard(imageItem.thumbnails.highImage)
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun InfoRow(viewModel: EventViewModel, navController: NavController) {
    Row(
        modifier = Modifier
            .horizontalScroll(rememberScrollState())
            .padding(top = 20.dp),
        horizontalArrangement = Arrangement.spacedBy(5.dp)
    ) {
        ChipInfo(
            modifier = Modifier
                .padding(start = DefaultPadding)
                .clip(RoundedCornerShape(10.dp))
                .clickable {
                    navController.navigate(
                        CommentListRoute(
                            name = viewModel.event?.shortTitle.toString(),
                            type = EventCategory.EVENT,
                            id = viewModel.event?.id ?: -1
                        )
                    )
                },
            title = ConvertCountTitle.convertCommentsCount(viewModel.event?.commentsCount ?: 0),
            subtitle = ConvertCountTitle.convertLikeCount(viewModel.event?.favoritesCount ?: 0),
            icon = {
                Icon(
                    painter = painterResource(R.drawable.ic_comment),
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.primary,
                    modifier = Modifier
                        .padding(end = 10.dp)
                        .size(28.dp),
                )
            }
        )

        ChipInfo(
            title = stringResource(R.string.age),
            subtitle = ConvertInfo.convertAgeRestriction(viewModel.event?.ageRestriction.toString())
        )

        ChipInfo(
            modifier = Modifier.padding(end = DefaultPadding),
            title = stringResource(R.string.duration),
            subtitle = ConvertDate.convertListDateRange(viewModel.event?.dates ?: listOf())
        )
    }
}

@Composable
private fun TagsRow(viewModel: EventViewModel) {
    LazyRow(
        contentPadding = PaddingValues(DefaultPadding, 8.dp, DefaultPadding, 15.dp),
        horizontalArrangement = Arrangement.spacedBy(5.dp)
    ) {
        viewModel.event?.let {
            items(it.tags) { tag ->
                SuggestionChip(
                    label = {
                        Text(
                            text = ConvertInfo.convertTitle(tag)
                        )
                    },
                    onClick = {  }
                )
            }
        }
    }
}