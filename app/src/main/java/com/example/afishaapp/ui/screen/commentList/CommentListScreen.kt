package com.example.afishaapp.ui.screen.commentList

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import com.example.afishaapp.R
import com.example.afishaapp.domain.module.EventCategory
import com.example.afishaapp.ui.screen.bottomSheet.DirectedFilterBottomSheet
import com.example.afishaapp.ui.widget.card.CommentCardFill
import com.example.afishaapp.ui.widget.endlessLazy.EndlessLazyColumn
import com.example.afishaapp.ui.widget.text.SubtitleTopBar
import com.example.afishaapp.ui.widget.text.TitleTopBar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CommentListScreen(
    navController: NavController,
    viewModel: CommentListViewModel,
    objectId: Int,
    objectName: String,
    type: EventCategory
) {
    viewModel.getComments(
        id = objectId,
        type = type
    )

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        TitleTopBar(stringResource(R.string.reviews_on))
                        SubtitleTopBar(objectName)
                    }
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
                        onClick = {
                            viewModel.updateDirectedFilterState(true)
                        }
                    ) {
                        Icon(
                            painter = painterResource(R.drawable.ic_filter),
                            contentDescription = stringResource(R.string.filter)
                        )
                    }
                }
            )
        }
    ) { innerPadding ->
        EndlessLazyColumn(
            modifier = Modifier.padding(innerPadding),
            items = viewModel.comments,
            loadMore = {
                viewModel.loadMoreComments(
                    id = objectId,
                    type = type
                )
            }
        ) { comment ->
            CommentCardFill(comment)
        }

        if (viewModel.directedFilterState) {
            DirectedFilterBottomSheet(
                currentFilter = viewModel.filter,
                onDismiss = { viewModel.updateDirectedFilterState(false) }
            ) {
                viewModel.updateFilter(it)
                viewModel.updateDirectedFilterState(false)
            }
        }
    }
}