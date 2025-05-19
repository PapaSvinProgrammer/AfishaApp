package com.example.afishaapp.ui.screen.event

import  androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
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
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SuggestionChip
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
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
import com.example.afishaapp.app.navigation.CommentListEventRoute
import com.example.afishaapp.app.navigation.FormPaymentRoute
import com.example.afishaapp.app.navigation.MapRoute
import com.example.afishaapp.app.utils.convertData.ConvertCountTitle
import com.example.afishaapp.app.utils.convertData.ConvertInfo
import com.example.afishaapp.app.utils.convertData.ConvertDate
import com.example.afishaapp.data.module.event.Event
import com.example.afishaapp.data.module.place.Place
import com.example.afishaapp.ui.theme.DefaultPadding
import com.example.afishaapp.ui.widget.chip.ChipInfo
import com.example.afishaapp.ui.widget.row.SelectRow
import com.example.afishaapp.ui.widget.listItem.MapImageCard
import com.example.afishaapp.ui.widget.collapsingTopBar.CollapsedTopBar
import com.example.afishaapp.ui.widget.collapsingTopBar.ExpandedTopBar
import com.example.afishaapp.ui.widget.row.CommentsRow
import com.example.afishaapp.ui.widget.row.ImagesRow
import com.example.afishaapp.ui.widget.row.SubwayRow
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
    val derivedEventId by remember { derivedStateOf { eventId } }

    viewModel.getEventInfo(derivedEventId)
    viewModel.getComments(derivedEventId)
    viewModel.parseEventInfo()
    viewModel.getImageUrl()

    val listState = rememberLazyListState()
    val isCollapsed: Boolean by remember {
        derivedStateOf {
            listState.firstVisibleItemIndex > 0
        }
    }

    LaunchedEffect(viewModel.event) {
        viewModel.event?.let {
            viewModel.findLikeEvent()
        }
    }

    Box {
        val color = if (isCollapsed)
            MaterialTheme.colorScheme.onSurface
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
                if (viewModel.event != null) {
                    IconButton(
                        onClick = {
                            handleLike(viewModel)
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
                                fontWeight = FontWeight.Bold,
                                fontSize = 19.sp,
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

                            Spacer(modifier = Modifier.height(10.dp))

                            Button(
                                modifier = Modifier.fillMaxWidth(),
                                onClick = {
                                    navController.navigate(FormPaymentRoute(eventId))
                                }
                            ) {
                                Text(text = "Записаться")
                            }
                        }
                    }
                }
            }

            item {
                if (viewModel.event == null) {
                    ShimmerEvent()
                }

                viewModel.event?.let { event ->
                    InfoRow(
                        event = event,
                        onClick = {
                            navController.navigate(
                                CommentListEventRoute(
                                    name = viewModel.event?.shortTitle.toString(),
                                    id = viewModel.event?.id ?: -1
                                )
                            )
                        }
                    )

                    TagsRow(event.tags)

                    EventDescriptionText(
                        title = viewModel.parseEventDescription,
                        bodyText = viewModel.parseEventBodyText
                    ) {
                        navController.navigate(
                            AboutEventRoute(derivedEventId)
                        )
                    }

                    CommentsRow(
                        comments = viewModel.comments,
                        onClick = {
                            navController.navigate(
                                CommentListEventRoute(
                                    name = event.shortTitle,
                                    id = event.id
                                )
                            )
                        }
                    )

                    Spacer(modifier = Modifier.height(10.dp))

                    PlaceRow(
                        place = viewModel.event?.place,
                        navController = navController,
                        imageMap = viewModel.imageMap
                    )

                    Spacer(modifier = Modifier.height(10.dp))

                    ImagesRow(event.images)
                }
            }
        }
    }
}

private fun handleLike(viewModel: EventViewModel) {
    viewModel.updateFavoriteState(
        state = !viewModel.favoriteState
    )

    when (viewModel.favoriteState) {
        true -> viewModel.insertLikeEvent()
        false -> viewModel.deleteLikeEvent()
    }
}

@Composable
private fun PlaceRow(place: Place?, navController: NavController, imageMap: String) {
    if (place == null) {
        return
    }

    SelectRow(
        text = ConvertInfo.convertTitle(place.title),
        fontSize = 18.sp,
        fontWeight = FontWeight.Bold,
        icon = null
    ) {
        navigateToMap(navController, place)
    }

    Box(
        modifier = Modifier
            .padding(
                start = DefaultPadding,
                end = DefaultPadding
            )
            .clickable(
                interactionSource = remember { MutableInteractionSource() },
                indication = null,
                onClick = {
                    navigateToMap(navController, place)
                }
            )
    ) {
        MapImageCard(imageMap)
    }

    Text(
        text = place.address,
        fontWeight = FontWeight.Bold,
        fontSize = 16.sp,
        modifier = Modifier
            .padding(
                start = DefaultPadding,
                end = DefaultPadding,
                top = 15.dp
            )
    )

    Row {
        Spacer(modifier = Modifier.width(DefaultPadding))
        SubwayRow(place)
    }
}

@Composable
private fun InfoRow(
    event: Event,
    onClick: () -> Unit
) {
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
                    onClick.invoke()
                },
            title = ConvertCountTitle.convertCommentsCount(event.commentsCount),
            subtitle = ConvertCountTitle.convertLikeCount(event.favoritesCount),
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
            subtitle = ConvertInfo.convertAgeRestriction(event.ageRestriction)
        )

        ChipInfo(
            modifier = Modifier.padding(end = DefaultPadding),
            title = stringResource(R.string.duration),
            subtitle = ConvertDate.convertListDateRange(event.dates)
        )
    }
}

@Composable
private fun TagsRow(tags: List<String>) {
    LazyRow(
        contentPadding = PaddingValues(DefaultPadding, 8.dp, DefaultPadding, 15.dp),
        horizontalArrangement = Arrangement.spacedBy(5.dp)
    ) {
        items(tags) { tag ->
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

private fun navigateToMap(navController: NavController, place: Place) {
    navController.navigate(
        MapRoute(
            lat = place.coordinates?.lat ?: 0.0,
            lon = place.coordinates?.lon ?: 0.0,
            placeId = place.id
        )
    )
}