package com.example.afishaapp.ui.screen.place

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.rememberScrollState
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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
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
import com.example.afishaapp.app.navigation.CommentListPlaceRoute
import com.example.afishaapp.app.navigation.MapRoute
import com.example.afishaapp.app.utils.convertData.ConvertCountTitle
import com.example.afishaapp.app.utils.convertData.ConvertInfo
import com.example.afishaapp.app.utils.ParseHtml
import com.example.afishaapp.data.module.place.Place
import com.example.afishaapp.ui.theme.DefaultPadding
import com.example.afishaapp.ui.widget.chip.ChipInfo
import com.example.afishaapp.ui.widget.collapsingTopBar.CollapsedTopBar
import com.example.afishaapp.ui.widget.collapsingTopBar.ExpandedTopBar
import com.example.afishaapp.ui.widget.row.CommentsRow
import com.example.afishaapp.ui.widget.row.ImagesRow
import com.example.afishaapp.ui.widget.shimmer.screen.ShimmerEvent
import com.example.afishaapp.ui.widget.shimmer.screen.ShimmerExpandedToolbar

@Composable
fun PlaceScreen(
    navController: NavController,
    viewModel: PlaceViewModel,
    placeId: Int
) {
    viewModel.getPlace(placeId)
    viewModel.getComments(placeId)

    val listState = rememberLazyListState()
    val isCollapsed by remember { derivedStateOf { listState.firstVisibleItemIndex > 0 } }

    if (viewModel.place != null) {
        viewModel.findLikePlace()
    }

    Box {
        val color = if (!isCollapsed)
            Color.White
        else
            MaterialTheme.colorScheme.onSurface

        CollapsedTopBar(
            isCollapsed = isCollapsed,
            title = {
                Text(
                    text = viewModel.place?.title ?: "",
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
            },
            navigationIcon = {
                IconButton(onClick = { navController.popBackStack() }) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Default.ArrowBack,
                        tint = color,
                        contentDescription = stringResource(R.string.arrow_back_description)
                    )
                }
            },
            actions = {
                if (viewModel.place != null) {
                    val favoriteIcon = if (viewModel.favoriteState)
                        Icons.Default.Favorite
                    else
                        Icons.Default.FavoriteBorder

                    IconButton(
                        onClick = {
                            viewModel.place?.let {
                                navController.navigate(
                                    MapRoute(
                                        placeId = placeId,
                                        lat = it.coordinates?.lat ?: 0.0,
                                        lon = it.coordinates?.lon ?: 0.0
                                    )
                                )
                            }
                        }
                    ) {
                        Icon(
                            painter = painterResource(R.drawable.ic_map),
                            tint = color,
                            contentDescription = stringResource(R.string.favorite_text)
                        )
                    }

                    IconButton(
                        onClick = {
                            handleFavorite(viewModel)
                        }
                    ) {
                        Icon(
                            imageVector = favoriteIcon,
                            tint = Color.Red,
                            contentDescription = stringResource(R.string.favorite_text)
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
                if (viewModel.place == null) {
                    ShimmerExpandedToolbar()
                }

                viewModel.place?.let {
                    ExpandedTopBar {
                        it.images?.let {
                            AsyncImage(
                                model = it[0].image,
                                contentDescription = null,
                                contentScale = ContentScale.Crop,
                                modifier = Modifier.fillMaxSize()
                            )
                        }

                        Text(
                            text = it.title,
                            fontWeight = FontWeight.Bold,
                            fontSize = 19.sp,
                            color = Color.White,
                            modifier = Modifier.padding(
                                horizontal = DefaultPadding,
                                vertical = 20.dp
                            )
                        )
                    }
                }
            }

            item {
                if (viewModel.place == null) {
                    ShimmerEvent()
                }

                viewModel.place?.let {
                    InfoRow(
                        place = it,
                        onClick = {
                            navController.navigate(
                                CommentListPlaceRoute(id = placeId, name = it.title)
                            )
                        }
                    )

                    Spacer(modifier = Modifier.height(10.dp))
                    TagsRow(it.tags ?: listOf())

                    Spacer(modifier = Modifier.height(20.dp))
                    SmoothEventDescription(
                        title = ParseHtml.getText(it.description),
                        text = ParseHtml.getText(it.bodyText)
                    )

                    CommentsRow(viewModel.comments) {
                        navController.navigate(
                            CommentListPlaceRoute(
                                id = placeId,
                                name = it.title
                            )
                        )
                    }

                    Spacer(modifier = Modifier.height(10.dp))

                    it.images?.let { images ->
                        ImagesRow(images)
                    }
                }
            }
        }
    }
}

fun handleFavorite(viewModel: PlaceViewModel) {
    viewModel.updateFavoriteState(!viewModel.favoriteState)

    when (viewModel.favoriteState) {
        true -> viewModel.addLikePlace()
        false -> viewModel.deleteLikePlace()
    }
}

@Composable
private fun SmoothEventDescription(
    title: String,
    text: String
) {
    var isExpanded by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .padding(horizontal = DefaultPadding)
            .fillMaxWidth()
            .animateContentSize()
            .clickable(
                interactionSource = remember { MutableInteractionSource() },
                indication = null,
                onClick = { isExpanded = !isExpanded }
            )
    ) {
        Text(
            text = ConvertInfo.convertTitle(title),
            fontWeight = FontWeight.Bold,
            fontSize = 15.sp,
            maxLines = 3,
            overflow = TextOverflow.Ellipsis,
            modifier = Modifier.padding(bottom = 5.dp)
        )

        Text(
            text = text,
            fontSize = 14.sp,
            maxLines = if (isExpanded) 100 else 4,
            overflow = TextOverflow.Ellipsis
        )

        if (!isExpanded) {
            Text(
                text = stringResource(R.string.read_more),
                fontSize = 15.sp,
                fontWeight = FontWeight.Medium,
                color = Color.Gray
            )
        }
    }
}

@Composable
private fun InfoRow(place: Place, onClick: () -> Unit) {
    Row(
        modifier = Modifier
            .horizontalScroll(rememberScrollState())
            .padding(
                start = DefaultPadding,
                end = DefaultPadding,
                top = 20.dp
            ),
        horizontalArrangement = Arrangement.spacedBy(5.dp)
    ) {
        ChipInfo(
            title = ConvertCountTitle.convertLikeCount(place.favoritesCount),
            subtitle = ConvertCountTitle.convertCommentsCount(place.commentsCount),
            icon = {
                Icon(
                    painter = painterResource(R.drawable.ic_comment),
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.primary,
                    modifier = Modifier
                        .padding(end = 10.dp)
                        .size(28.dp),
                )
            },
            modifier = Modifier.clickable { onClick.invoke() }
        )

        ChipInfo(
            title = stringResource(R.string.address),
            subtitle = place.address
        )

        ChipInfo(
            title = stringResource(R.string.phone),
            subtitle = place.phone
        )
    }
}

@Composable
private fun TagsRow(tags: List<String>) {
    LazyRow(
        contentPadding = PaddingValues(horizontal = DefaultPadding),
        horizontalArrangement = Arrangement.spacedBy(5.dp)
    ) {
        items(tags) {
            SuggestionChip(
                label = {
                    Text(text = it)
                },
                onClick = {}
            )
        }
    }
}